package com.pberrueco.apiai.ui.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pberrueco.apiai.data.network.IANames
import com.pberrueco.apiai.databinding.ActivityMainBinding
import com.pberrueco.apiai.ui.AIAdapter

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

        viewModel.getQuestion(this)
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.question.observe(this) { question ->
            if (question != null) {
                viewModel.getResponse(question)
            }
        }
        viewModel.aiResponse.observe(this, Observer { response ->
            if (response != null) {
                adapter.submitList(response)
            } else {
                Toast.makeText(this, "Algo fue mal", Toast.LENGTH_SHORT).show()
            }
        })
    }
}