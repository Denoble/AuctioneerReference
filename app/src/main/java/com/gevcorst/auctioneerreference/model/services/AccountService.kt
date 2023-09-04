package com.gevcorst.auctioneerreference.model.services

import android.util.Log
import com.gevcorst.auctioneerreference.model.User
import com.gevcorst.auctioneerreference.model.UserDataStore
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface AccountService {
    val currentUserId: String
    val hasUser: Boolean
    val currentUser: Flow<User>
    suspend fun authenticate(email: String, password: String)
    suspend fun sendRecoveryEmail(email: String)
    suspend fun createAnonymousAccount()
    suspend fun linkAccount(email: String, password: String)
    suspend fun createAccount(email: String,password: String)
    suspend fun deleteAccount()
    suspend fun signOut()
}
class AccountServiceImpl @Inject constructor(
    private var auth: FirebaseAuth,
    val dataStore: UserDataStore
) : AccountService {
    var user = User(isLoggedIn = false)
    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()
    override val hasUser: Boolean
        get() = auth.currentUser != null
    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth ->
                this.trySend(auth.currentUser?.let {
                    User(it.uid, true)
                } ?: User())
            }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }


    override suspend fun authenticate(email: String, password: String) {
        Log.d("Authenticate !!", "We got here ! ")
        CoroutineScope(Dispatchers.Default).launch {
            val deferred = async {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val id = auth.currentUser?.uid ?: ""
                        Log.d(
                            "Authenticate !!",
                            "${id} "
                        )
                    } else {
                        Log.d(
                            "AUTH3", "Authenticate "
                                    + it.exception?.stackTraceToString()
                        )
                    }
                }
            }
            deferred.await()
            currentUser.collect {
                if (it.isLoggedIn) {
                    dataStore.saveUserId(it.id)
                } else {
                    Log.d(
                        "AUTH3", "Is loggedin is false ${it.id}  "
                    )
                }
            }
        }
    }

    override suspend fun sendRecoveryEmail(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }

    override suspend fun createAnonymousAccount() {
        try {
            auth.signInAnonymously().await()
        } catch (e: Exception) {
            Log.d(javaClass.simpleName, "We got here " + e.stackTraceToString())
        }
    }

    override suspend fun linkAccount(email: String, password: String) {
        val credential = EmailAuthProvider.getCredential(email, password)

        auth.currentUser?.linkWithCredential(credential)?.await()
        Log.d(
            javaClass.simpleName + " AccountServiceImpl",
            "Account link success!"
        )

    }

    override suspend fun createAccount(email: String, password: String) {
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val id = auth.currentUser?.uid ?: ""
                        //user.value = User(id, isLoggedIn = false)
                    }
                }
        } catch (e: Exception) {
            Log.d(
                javaClass.simpleName + " AccountServiceImpl",
                e.stackTraceToString()
            )
        }
    }

    override suspend fun deleteAccount() {
        auth.currentUser?.delete()?.addOnCompleteListener {
            if (it.isSuccessful) {
                // user.value = User(isLoggedIn = false)
            }
        }
    }

    override suspend fun signOut() {
        withContext(Dispatchers.Default) {
            async {
                dataStore.deleteUserId().collect {
                    Log.i("Deleted", it)
                }
            }.await()
            // user.value = User()
            auth.signOut()
        }
    }
}