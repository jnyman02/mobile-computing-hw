package com.jnyman.homeworkapp.database

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jnyman.homeworkapp.Message

@Entity
data class Conversation(
    val name: String,
    val messages: List<Message>,
    val conversationPictureUri: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0
)