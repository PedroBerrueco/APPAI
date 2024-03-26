package com.pberrueco.apiai.ui.recycler

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pberrueco.apiai.data.data_store.DataStoreManager
import com.pberrueco.apiai.data.network.AIApi
import com.pberrueco.apiai.data.network.QuestionBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var _question: MutableLiveData<String?> = MutableLiveData(null)
    val question: LiveData<String?> = _question

    private val _aiResponse = MutableLiveData<List<String>>()
    val aiResponse: LiveData<List<String>> = _aiResponse

    fun getQuestion(context: Context) {
        viewModelScope.launch(Dispatchers.IO){
            DataStoreManager.getData(context).collect{ question ->
                if(question != "No Data"){
                    _question.postValue(question)
                }
            }
        }

    }

    fun getResponse(question: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = AIApi.service.getResponse(QuestionBody(question))
                if (response.isSuccessful) {
                    val aiResponseList = response.body()
                    _aiResponse.postValue(aiResponseList!!)
                } else {
                    // Manejar respuesta de error si es necesario
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Manejar la excepción si es necesario
            }
        }
    }
}