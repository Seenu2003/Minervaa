package com.seenu.minerva2.data.userDetails

import javax.inject.Inject

class UserDetailsRepository @Inject constructor(private val userDetailsDao: UserDetailsDao) {

    suspend fun insertUserDetails(userDetails: UserDetails) {
        userDetailsDao.insertUserDetails(userDetails)
    }

    suspend fun deleteUserDetails(userDetails: UserDetails) {
        userDetailsDao.deleteUserDetails(userDetails)
    }

    fun getUserByUsername(username: String): UserDetails {
        return userDetailsDao.getUserByUsername(username)
    }

    suspend fun getUserByID(userID: Int): UserDetails {
        return userDetailsDao.getUserByID(userID)
    }
    suspend fun getMaxUserID(): Int {
        return userDetailsDao.getMaxUserID()
    }
}