package com.seenu.minerva2.data.userDetails

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "userDetails"
)
data class UserDetails(
    val name: String,
    val username : String,
    val email : String,
    val phoneNumber : String,
    val password : String,
    @PrimaryKey(autoGenerate = true)
    val userId:Int?=null,
)
