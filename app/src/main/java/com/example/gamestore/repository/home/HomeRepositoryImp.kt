package com.example.gamestore.repository.home

import com.example.gamestore.model.game.Game
import com.example.gamestore.model.game.GameResponse
import com.example.gamestore.network.GameService.Companion.gameService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepositoryImp : HomeRepository {

    override suspend fun getGameList(): HomeRepositoryStatus<List<Game>> {
        return withContext(Dispatchers.IO){
            try {
                val gameResponse = gameService.getGameList()
                HomeRepositoryStatus.Success(gameResponse)
            }catch (t : Throwable){
                HomeRepositoryStatus.Error(t)
            }
        }
    }

    override suspend fun getGameListByUrl(url : String): HomeRepositoryStatus<List<Game>> {
        return withContext(Dispatchers.IO){
            try {
                val gameResponse = gameService.getGamesByUrl(url)
                HomeRepositoryStatus.Success(gameResponse)
            }catch (t : Throwable){
                HomeRepositoryStatus.Error(t)
            }
        }
    }
}