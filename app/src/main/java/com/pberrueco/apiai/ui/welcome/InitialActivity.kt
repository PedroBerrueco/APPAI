package com.pberrueco.apiai.ui.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.pberrueco.apiai.ui.recycler.MainActivity
import com.pberrueco.apiai.databinding.ActivityInitialBinding

//TODO Icono para la APP.
class InitialActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityInitialBinding
    private val binding: ActivityInitialBinding get() = _binding

    private val ViewModel: InitialViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInitialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btQuestion.setOnClickListener{//Funcion para guardar en DataStore al pulsar el boton
            saveQuestion()
        }

    }

    private fun saveQuestion() {
        val user = binding.etQuestion.text.toString().trim()
        if(!user.isNullOrEmpty()){
            ViewModel.saveQuestion(this, user)
            goToRecycler()
        }else{
            Toast.makeText(this, "Escribe una pregunta", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToRecycler() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}