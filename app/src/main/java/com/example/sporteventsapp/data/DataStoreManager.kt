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
        }
    }

    fun getData() = context.dataStore.data.map { pref ->
        return@map PostNames(
            pref[stringPreferencesKey("firstName")]?: "null",
            pref[stringPreferencesKey("secondName")]?: "null"
        )

    }

//    suspend fun saveTitles(titlesData: EventTitles){
//        context.dataStore.edit { pref->
//            pref[stringPreferencesKey("title")] = titlesData.titles.toString()
//
//        }
//    }
//
//    fun getTitles() = context.dataStore.data.map { pref ->
//        return@map EventTitles(
//            pref[stringPreferencesKey("title")]?: "null"
//
//        )
//
//    }

}