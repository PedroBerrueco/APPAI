package com.pberrueco.apiai.ui.score

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.pberrueco.apiai.databinding.ActivityScoreBinding
import androidx.lifecycle.Observer
import com.pberrueco.apiai.data.network.AIApi
import com.pberrueco.apiai.data.network.model.ScoreResponse

class ScoreActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityScoreBinding
    private val binding: ActivityScoreBinding get() = _binding

    private val viewModel: ScoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getScoreData()
        observeViewModel()

        // Configurar listeners para las RatingBars
        binding.rt0.setOnRatingBarChangeListener { _, rating, _ ->
            sendRatingToAPI(1, rating) // Asociar GPT con el contenedor 1
        }
        binding.rt1.setOnRatingBarChangeListener { _, rating, _ ->
            sendRatingToAPI(2, rating) // Asociar Llama con el contenedor 2
        }
        binding.rt2.setOnRatingBarChangeListener { _, rating, _ ->
            sendRatingToAPI(3, rating) // Asociar Ant con el contenedor 3
        }

    }

    private fun sendRatingToAPI(i: Int, rating: Float) {
        val additionalVote: Long  = 1 // Cantidad de votos adicionales
        val additionalPoints = rating.toLong() // Puntos adicionales según la puntuación seleccionada

        try {
            val requestBody = mapOf<String, Long>(
                ("additionalVote" to additionalVote),
                "additionalPoints" to additionalPoints // Convertir directamente a Long
            )

            // Actualizar el puntaje en el ViewModel
            viewModel.updateScore(i, requestBody)
        } catch (e: Exception) {
            e.printStackTrace()
            // Manejar la excepción si la solicitud falla
        }
    }

    private fun observeViewModel() {
        viewModel.aiResponse.observe(this, Observer { _ ->
            // Actualizar las vistas con los datos de puntuación
            viewModel.aiResponse.value?.let { scoreList ->
                updateScoreViews(scoreList)
            }
        })
    }

    private fun updateScoreViews(scoreList: List<ScoreResponse>) {
        // Iterar sobre los elementos en la lista scoreList
        scoreList.forEach { score ->
            // Obtener el ID del conjunto de datos actual
            val id = score.id

            // Actualizar las vistas basadas en el ID del conjunto de datos
            when (id) {
                1 -> {
                    // Asociar los datos de GPT al contenedor 1
                    binding.title1.text = score.name
                    binding.media1.text = "Media: ${score.media}"
                    binding.votes1.text = "Votes: ${score.votes}"
                }
                2 -> {
                    // Asociar los datos de Llama al contenedor 2
                    binding.title2.text = score.name
                    binding.media2.text = "Media: ${score.media}"
                    binding.votes2.text = "Votes: ${score.votes}"
                }
                3 -> {
                    // Asociar los datos de Ant al contenedor 3
                    binding.title3.text = score.name
                    binding.media3.text = "Media: ${score.media}"
                    binding.votes3.text = "Votes: ${score.votes}"
                }
                // Agregar más casos según sea necesario para otros IDs
            }
        }
    }
}