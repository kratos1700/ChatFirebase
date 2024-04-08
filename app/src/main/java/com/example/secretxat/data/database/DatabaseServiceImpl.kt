package com.example.secretxat.data.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.secretxat.domain.DatabaseService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject




class DatabaseServiceImpl @Inject constructor(private val context: Context) : DatabaseService {

    companion object {
        private val USER_NAME = stringPreferencesKey("username")
    }

    private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "user"
    )


    override suspend fun saveNickName(nickName: String) {
        // Save the user name
        context.userPreferencesDataStore.edit { preferences ->
            preferences[USER_NAME] = nickName
        }

    }

    override suspend fun clear() {
        context.userPreferencesDataStore.edit { preferences ->
            preferences[USER_NAME] = ""
        }
    }

    override fun getNickName(): Flow<String> =
        // Get the user name
        context.userPreferencesDataStore.data.map { preferences ->
            preferences[USER_NAME] ?: "" }

}


