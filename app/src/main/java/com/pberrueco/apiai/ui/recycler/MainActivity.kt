package com.pberrueco.apiai.ui.recycler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pberrueco.apiai.data.network.IANames
import com.pberrueco.apiai.databinding.ActivityMainBinding
import com.pberrueco.apiai.ui.AIAdapter
import com.pberrueco.apiai.ui.score.ScoreActivity

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding: ActivityMainBinding get() = _binding

    private val viewModel: MainViewModel by viewModels()
    private val adapter = AIAdapter(IANames.names)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvNuevabase.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvNuevabase.adapter = adapter

        binding.progressBar.visibility = View.VISIBLE

        viewModel.getQuestion(this)
        observeViewModel()

        //Navegar a ScoreActivity
        binding.btnRatings.setOnClickListener {
            val intent = Intent(this, ScoreActivity::class.java)
            // Iniciar la actividad ScoreActivity
            startActivity(intent)
        }

    }

    private fun observeViewModel() {
        viewModel.question.observe(this) { question ->
            if (question != null) {
                viewModel.getResponse(question)
            }
        }
        viewModel.aiResponse.observe(this, Observer { response ->
            if (response != null) {
                binding.progressBar.visibility = View.GONE
                adapter.submitList(response)
                viewModel.deleteQuestion(this, viewModel.question)
            } else {
                Toast.makeText(this, "Algo fue mal", Toast.LENGTH_SHORT).show()
            }
        })
    }
}