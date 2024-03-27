package com.pberrueco.apiai.ui.welcome

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pberrueco.apiai.data.data_store.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InitialViewModel : ViewModel() {
    fun saveQuestion(context: Context, question: String){
        viewModelScope.launch(Dispatchers.IO){
            DataStoreManager.saveData(context, question)
        }
    }
}
