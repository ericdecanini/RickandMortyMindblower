package com.example.rickandmortymindblower.entity

import java.io.Serializable

data class Character(
    val id: String,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
): Serializable
