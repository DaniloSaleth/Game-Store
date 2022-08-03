package com.example.gamestore.model.game

class GameResponse (
    val count : Int,
    val next : String?,
    val previous : String?,
    val results: List<Game>
        )
