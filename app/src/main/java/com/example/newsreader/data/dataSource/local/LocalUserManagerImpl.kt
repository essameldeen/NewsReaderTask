package com.example.newsreader.data.dataSource.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsreader.domain.manager.LocalUserManager
import com.example.newsreader.core.utils.NewsConstant
import com.example.newsreader.core.utils.NewsConstant.USER_SETTINGS
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserManagerImpl @Inject constructor(
    @ApplicationContext private  val  context: Context
) : LocalUserManager {
    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readEntryApp(): Flow<Boolean> {
        return context.dataStore.data.map { settings ->
            settings[PreferencesKeys.APP_ENTRY] ?: false
        }
    }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PreferencesKeys {
    val APP_ENTRY = booleanPreferencesKey(name = NewsConstant.APP_ENTRY)
}