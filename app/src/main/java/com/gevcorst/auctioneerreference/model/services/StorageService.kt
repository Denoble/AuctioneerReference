package com.gevcorst.auctioneerreference.model.services

import com.gevcorst.auctioneerreference.model.Item
import com.gevcorst.auctioneerreference.ui.signup.ContactUiState
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.perf.ktx.trace
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface StorageService {
    val tasks: Flow<List<Item>>

    suspend fun getItem(itemId: String,type:String): Item?

    suspend fun save(item: Item,type:String): String
    suspend fun update(item: Item, type:String)
    suspend fun delete(itemId: String,type:String)

    suspend fun createContact(contact: ContactUiState)
    suspend fun editContact(contact: ContactUiState)
    suspend fun deleteContact()
    //suspend fun deleteAllForUser(userId: String)
}
class StorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: AccountService
) : StorageService {
    override val tasks: Flow<List<Item>>
        get() = emptyFlow()

    override suspend fun getItem(itemId: String, type: String): Item? =
        itemCollection(auth.currentUserId, type).document(itemId).get().await().toObject()

    override suspend fun save(item: Item, type: String): String =
        trace(SAVE_ITEM_TRACE) {
            itemCollection(auth.currentUserId, type).add(item).await().id
        }

    override suspend fun update(item: Item, type: String): Unit =
        trace(UPDATE_ITEM_TRACE) {
            itemCollection(auth.currentUserId, type).document(item.id).set(item).await()
        }

    override suspend fun delete(itemId: String, type: String) {
        itemCollection(auth.currentUserId, type).document(itemId).delete().await()
    }

    override suspend fun createContact(contact: ContactUiState) {
        TODO("Not yet implemented")
    }

    override suspend fun editContact(contact: ContactUiState) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteContact() {
        TODO("Not yet implemented")
    }

    private fun contactCollection(uid: String): CollectionReference = firestore.collection(
        USER_COLLECTION
    ).document(uid).collection(CONTACT_COLLECTON)

    private fun itemCollection(uid: String, type: String) =
        firestore.collection(ITEM_COLLECTION).document(uid)
            .collection(type)

    companion object {
        private const val USER_COLLECTION = "users"
        private const val ITEM_COLLECTION = "items"
        private const val SAVE_ITEM_TRACE = "saveItem"
        private const val UPDATE_ITEM_TRACE = "updateItem"
        private const val CONTACT_COLLECTON = "userContact"
        private const val SAVE_CONTACT = "saveContact"
        private const val EDIT_CONTACT = "editContact"
        private const val DELETE_CONTACT = "deleteContact"

    }
}
