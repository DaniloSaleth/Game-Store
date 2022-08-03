package com.example.gamestore.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.gamestore.R
import com.example.gamestore.databinding.ActivityMainBinding


class HomeActivity : AppCompatActivity() {
    private lateinit var adapter: HomeAdapter
    private lateinit var binding : ActivityMainBinding
    private val viewModel = HomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply { hide() }

        viewModel.getListOfGames()

        viewModel.gameList.observe(this){gameResponse->
            adapter = HomeAdapter(gameResponse.results)
            findViewById<RecyclerView>(R.id.rv_main).adapter = adapter

            binding.next.setBackgroundResource(R.drawable.radio_button_not_available)
            binding.previous.setBackgroundResource(R.drawable.radio_button_not_available)

            if (gameResponse.next != null){
                binding.next.setBackgroundResource(R.drawable.radio_button_available)
                binding.next.setOnClickListener{
                    paginationObserve(gameResponse.next)
                }
            }

            if(gameResponse.previous != null){
                binding.previous.setBackgroundResource(R.drawable.radio_button_available)
                binding.previous.setOnClickListener {
                    paginationObserve(gameResponse.previous)
                }
            }
        }

        viewModel.error.observe(this){
            Log.v("teste", ""+it.message)
        }
    }

    private fun paginationObserve(url : String){
        viewModel.getListOfGamesByUrl(url)
    }
}