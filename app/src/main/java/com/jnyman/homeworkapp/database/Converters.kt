package com.jnyman.homeworkapp.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jnyman.homeworkapp.Message

class Converters {

    @TypeConverter
    fun fromMessageList(messages: List<Message>): String {
        return Gson().toJson(messages)
    }

    @TypeConverter
    fun toMessageList(messagesString: String): List<Message> {
        val type = object : TypeToken<List<Message>>() {}.type
        return Gson().fromJson(messagesString, type)
    }
}