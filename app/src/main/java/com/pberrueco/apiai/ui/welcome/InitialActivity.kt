package com.pberrueco.apiai.ui.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pberrueco.apiai.R
import com.pberrueco.apiai.databinding.ActivityInitialBinding

class InitialActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityInitialBinding
    private val binding: ActivityInitialBinding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInitialBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}