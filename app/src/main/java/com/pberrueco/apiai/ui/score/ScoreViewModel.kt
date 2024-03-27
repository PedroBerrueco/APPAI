package com.pberrueco.apiai.ui.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pberrueco.apiai.data.network.AIApi
import com.pberrueco.apiai.data.network.AIService
import com.pberrueco.apiai.data.network.model.ScoreResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScoreViewModel : ViewModel() {

    private val _aiResponse = MutableLiveData<List<ScoreResponse>>()
    val aiResponse: LiveData<List<ScoreResponse>> = _aiResponse

    private val aiService: AIService = AIApi.service

    fun getScoreData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = AIApi.service.getScores()
                if (response.isSuccessful) {
                    val scoreList = response.body()
                    _aiResponse.postValue(scoreList!!)
                } else {
                    Log.d("ScoreActivity", "Respuesta MALA")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Manejar la excepción si es necesario
            }
        }
    }

    fun updateScore(id: Int, requestBody: Map<String, Long>) {
        viewModelScope.launch {
            try {
                val response = aiService.updateScore(id, requestBody)
                if (response.isSuccessful) {
                    // Manejar la respuesta exitosa si es necesario
                } else {
                    // Manejar la respuesta de error si es necesario
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Manejar la excepción si es necesario
            }
        }
    }

}