package com.davitydev.chat.Core.di.Token
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

@Singleton
class TokenDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val NAME_KEY = stringPreferencesKey("name")
        private val ID_KEY = intPreferencesKey("idUser")
    }

    suspend fun saveIdUser(idUser: Int) {
        context.dataStore.edit { it[ID_KEY] = idUser }
    }

    fun getIdUser(): Flow<Int?> {
        return context.dataStore.data.map { it[ID_KEY] }
    }

    fun getName(): Flow<String?> {
        return context.dataStore.data.map { it[NAME_KEY] }
    }

    suspend fun saveName(name: String) {
        context.dataStore.edit { it[NAME_KEY] = name }
    }
    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

}