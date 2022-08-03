package com.example.gamestore.repository.home

import androidx.lifecycle.MutableLiveData
import com.example.gamestore.model.game.Game
import com.example.gamestore.model.game.GameResponse

sealed class HomeRepositoryStatus<out R>{
    class Success(val gameResponse: GameResponse) : HomeRepositoryStatus<List<Game>>()
    class Error(val error : Throwable) : HomeRepositoryStatus<Nothing>()
}

interface HomeRepository {
    suspend fun getGameList() : HomeRepositoryStatus<List<Game>>
    suspend fun getGameListByUrl(url : String) : HomeRepositoryStatus<List<Game>>
}