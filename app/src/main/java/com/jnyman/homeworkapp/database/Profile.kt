package com.jnyman.homeworkapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile(
    val nickName: String,
    val profilePictureUri: String,
    val own: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0
)
