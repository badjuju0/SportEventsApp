package com.example.sporteventsapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Names")
class DataStoreManager(val context: Context) {
    suspend fun saveNames(namesData: PostNames){
        context.dataStore.edit { pref->
            pref[stringPreferencesKey("firstName")] = namesData.firstName
            pref[stringPreferencesKey("secondName")] = namesData.secondName
            pref[stringPreferencesKey("token")] = namesData.token
        }
    }

    fun getData() = context.dataStore.data.map { pref ->
        return@map PostNames(
            pref[stringPreferencesKey("firstName")]?: "null",
            pref[stringPreferencesKey("secondName")]?: "null",
            pref[stringPreferencesKey("token")]?: "null"
        )

    }

    suspend fun saveTitle(titleData: EventTitle){
        context.dataStore.edit { pref->
            pref[stringPreferencesKey("title")] = titleData.title

        }
    }

    fun getTitle() = context.dataStore.data.map { pref ->
        return@map EventTitle(
            pref[stringPreferencesKey("title")]?: "null"

        )

    }

    suspend fun saveEmail(emailData: String){
        context.dataStore.edit { pref->
            pref[stringPreferencesKey("email")] = emailData

        }
    }

    fun getEmail() = context.dataStore.data.map { pref ->
        return@map (
                pref[stringPreferencesKey("email")]?: "null"
                )

    }

//    suspend fun saveEventId(eventIdData: EventId){
//        context.dataStore.edit { pref->
//            pref[stringPreferencesKey("eventId")] = eventIdData.id
//
//        }
//    }
//
//    fun getEventId() = context.dataStore.data.map { pref ->
//        return@map EventId(
//            pref[stringPreferencesKey("eventId")]?: "null"
//
//        )
//
//    }

}