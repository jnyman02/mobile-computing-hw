package com.jnyman.homeworkapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [Conversation::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ConversationDatabase: RoomDatabase() {

    abstract fun conversationDao(): ConversationDao

}