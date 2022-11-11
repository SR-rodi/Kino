package com.example.kinopoisktest

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kinopoisktest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragment_container_view
        ) as NavHostFragment
        navController = navHostFragment.navController

        binding.navControllerViewTag.setupWithNavController(navController)

    }
}