package com.pberrueco.apiai.ui.score

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
        setupRatingBarListeners()

        // Configurar OnClickListener para el botón de enviar valoraciones
        binding.btnScore.setOnClickListener {
            sendRatingsToAPI()
        }

    }

    private fun sendRatingsToAPI() {
        // Deshabilitar las RatingBars después de enviar las valoraciones
        disableRatingBars()

        // Obtener las valoraciones de las RatingBars
        val rating0 = binding.rt0.rating
        val rating1 = binding.rt1.rating
        val rating2 = binding.rt2.rating

        // Llamar al método updateScore del ViewModel para enviar las valoraciones al servidor
        viewModel.updateScore(1, mapOf("additionalVote" to 1L, "additionalPoints" to rating0.toLong()))
        viewModel.updateScore(2, mapOf("additionalVote" to 1L, "additionalPoints" to rating1.toLong()))
        viewModel.updateScore(3, mapOf("additionalVote" to 1L, "additionalPoints" to rating2.toLong()))

    }

    private fun disableRatingBars() {
        binding.rt0.isEnabled = false
        binding.rt1.isEnabled = false
        binding.rt2.isEnabled = false
    }

    private fun setupRatingBarListeners() {
        binding.rt0.setOnRatingBarChangeListener { _, _, _ ->
            // No hagas nada aquí, la valoración se enviará al hacer clic en el botón de enviar
        }
        binding.rt1.setOnRatingBarChangeListener { _, _, _ ->
            // No hagas nada aquí, la valoración se enviará al hacer clic en el botón de enviar
        }
        binding.rt2.setOnRatingBarChangeListener { _, _, _ ->
            // No hagas nada aquí, la valoración se enviará al hacer clic en el botón de enviar
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
                    binding.media1.text = "Media: ${String.format("%.1f", score.media)}"
                    binding.votes1.text = "Votes: ${score.votes}"
                }
                2 -> {
                    // Asociar los datos de Llama al contenedor 2
                    binding.title2.text = score.name
                    binding.media2.text = "Media: ${String.format("%.1f", score.media)}"
                    binding.votes2.text = "Votes: ${score.votes}"
                }
                3 -> {
                    // Asociar los datos de Ant al contenedor 3
                    binding.title3.text = score.name
                    binding.media3.text = "Media: ${String.format("%.1f", score.media)}"
                    binding.votes3.text = "Votes: ${score.votes}"
                }

            }
        }
    }
}