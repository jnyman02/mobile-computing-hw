package com.jnyman.homeworkapp

import com.jnyman.homeworkapp.database.Profile

data class Message(
    val author: String,
    val ownMessage: Boolean,
    val body: String
)
