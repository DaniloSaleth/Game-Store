package com.example.gamestore.ui.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gamestore.R
import com.example.gamestore.databinding.GameItemBinding
import com.example.gamestore.model.game.Game

class HomeAdapter(private val listOfGames : List<Game>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>(){

    class ViewHolder(val binding :  GameItemBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GameItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvGameItemTitle.text = listOfGames[position].name
        if(listOfGames[position].released != null){
            holder.binding.tvGameItemRelease.text = listOfGames[position].released.substring(0,4)
        }
        holder.binding.tvGameItemTime.text = "${listOfGames[position].playtime}h"
        Glide.with(holder.binding.ivGameItemImage)
            .load(listOfGames[position].background_image)
            .fitCenter()
            .into(holder.binding.ivGameItemImage)

        var rate = listOfGames[position].rating
        holder.binding.tvGameItemRate.text = rate.toString()

        if (rate < 3){
            holder.binding.tvGameItemRate.setTextColor(Color.RED)
        }else if(rate < 4.2){
            holder.binding.tvGameItemRate.setTextColor(Color.YELLOW)
        } else {
            holder.binding.tvGameItemRate.setTextColor(Color.GREEN)

        }
    }

    override fun getItemCount(): Int {
        return listOfGames.size
    }
}