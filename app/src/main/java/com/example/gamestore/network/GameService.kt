package com.example.gamestore.network

import com.example.gamestore.const.API_KEY
import com.example.gamestore.const.API_URL
import com.example.gamestore.model.game.GameResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface GameService {

    @GET("games?${API_KEY}&page_size=10")
    suspend fun getGameList() : GameResponse

    @GET
    suspend fun getGamesByUrl(@Url url : String) : GameResponse

    companion object {
        val gameService: GameService by lazy {
            val endpoint = Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            endpoint.create(GameService::class.java)
        }
    }
}