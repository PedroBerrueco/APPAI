package com.pberrueco.apiai.data.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object DataStoreManager {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "MY_DATA_STORE")

    private val sampleKey = stringPreferencesKey("ANSWER_KEY")

    suspend fun saveData(context: Context, sampleData: String){
        context.dataStore.edit { editor ->
            editor[sampleKey] = sampleData
        }
    }

    suspend fun getData(context: Context): Flow<String> {
        return context.dataStore.data.map { editor ->
            editor[sampleKey]?: "No Data"
        }
    }

    suspend fun deleteAll(context: Context){
        context.dataStore.edit { editor ->
            editor.clear() //Para borrar todos los datos
        }
    }

    suspend fun deleteData(context: Context){
        context.dataStore.edit { editor ->
            editor.remove(sampleKey) //Para borrar solo uno
        }
    }


}