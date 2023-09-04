package com.gevcorst.auctioneerreference.model

import android.content.Context
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : UserDataStore {

    override fun userId(): Flow<String> {
        return dataStore.data
            .catch {
                emit(emptyPreferences())
            }
            .map { preference ->
                preference[USER_ID_KEY] ?: ""
            }
    }

    override suspend fun saveUserId(id: String) {
        dataStore.edit { preference ->
            preference[USER_ID_KEY] = id
        }
    }

    override suspend fun deleteUserId(): Flow<String> {
        return flow {
            dataStore.edit { preference ->
                preference.remove(USER_ID_KEY)
                emit("Operation Success!")
            }
        }
    }

    companion object {
        val USER_ID_KEY = stringPreferencesKey("USER_ID")
    }
}

interface UserDataStore {
    fun userId(): Flow<String>
    suspend fun saveUserId(name: String)
    suspend fun deleteUserId(): Flow<String>
}
