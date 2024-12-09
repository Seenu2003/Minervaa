package com.seenu.minerva2.data.goals


import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(
    tableName = "goals"
)
data class Goal(
    val goalName : String,
    val desc : String,
    val priority: String,
    val category: String,
    val deadline: String,
    val currentDate: String,
    @PrimaryKey(autoGenerate = true)
    val goalID : Int?= null,
)
