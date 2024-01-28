package com.jnyman.homeworkapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ProfileDao {

    @Upsert
    fun upsertProfile(profile: Profile)

    @Delete
    fun deleteProfile(profile: Profile)

    @Query("SELECT * FROM profile ORDER BY nickName ASC")
    fun getProfilesOrderedByNickname(): List<Profile>

    @Query("SELECT * FROM profile WHERE nickName LIKE :nickname LIMIT 1")
    fun getProfileByName(nickname: String): Profile

    @Query("SELECT * FROM profile WHERE own = 1 LIMIT 1")
    fun getOwnProfile(): Profile


}