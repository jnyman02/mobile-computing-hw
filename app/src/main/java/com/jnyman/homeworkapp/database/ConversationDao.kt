package com.jnyman.homeworkapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ConversationDao {

    @Upsert
    fun upsertConversation(conversation: Conversation)

    @Delete
    fun deleteConversation(conversation: Conversation)

    @Query("SELECT * FROM conversation ORDER BY name ASC")
    fun getConversationsOrderedByName(): List<Conversation>

    @Query("SELECT * FROM conversation WHERE name LIKE :name LIMIT 1")
    fun getConversationByName(name: String): Conversation

}