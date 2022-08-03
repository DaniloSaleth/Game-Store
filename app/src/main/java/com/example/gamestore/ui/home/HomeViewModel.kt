package com.example.gamestore.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamestore.const
import com.example.gamestore.model.game.Game
import com.example.gamestore.model.game.GameResponse
import com.example.gamestore.repository.home.HomeRepository
import com.example.gamestore.repository.home.HomeRepositoryImp
import com.example.gamestore.repository.home.HomeRepositoryStatus
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository = HomeRepositoryImp()) : ViewModel() {

    private val _gameListResponse = MutableLiveData<GameResponse>()
    val gameList : LiveData<GameResponse>
        get() = _gameListResponse

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error


    fun getListOfGames() = viewModelScope.launch {
        repository.getGameList().apply {
            when (this){
                is HomeRepositoryStatus.Success -> _gameListResponse.value = gameResponse
                is HomeRepositoryStatus.Error -> _error.value = error
            }
        }
    }

    fun getListOfGamesByUrl(url : String) = viewModelScope.launch {
        repository.getGameListByUrl(url).apply {
            when (this){
                is HomeRepositoryStatus.Success -> _gameListResponse.value = gameResponse
                is HomeRepositoryStatus.Error -> _error.value = error
            }
        }
    }
}