package com.pberrueco.apiai.ui.welcome

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pberrueco.apiai.data.data_store.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InitialViewModel : ViewModel() {
    fun saveUserName(context: Context, username: String){
        viewModelScope.launch(Dispatchers.IO){
            DataStoreManager.saveData(context, username)
        }
    }
}