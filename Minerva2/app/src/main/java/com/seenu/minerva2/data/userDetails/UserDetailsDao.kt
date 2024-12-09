package com.seenu.minerva2.data.userDetails

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDetailsDao {
    @Insert
    suspend fun insertUserDetails(userDetails: UserDetails)

    @Delete
    suspend fun deleteUserDetails(userDetails: UserDetails)

    @Query("SELECT * FROM userDetails WHERE username = :username")
    fun getUserByUsername(username: String): UserDetails

    @Query("SELECT * FROM userDetails WHERE userId= :userID")
    suspend fun getUserByID(userID: Int): UserDetails

    @Query("SELECT MAX(userId) FROM userdetails")
    suspend fun getMaxUserID(): Int


}