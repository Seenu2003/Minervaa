package com.seenu.minerva2.data.tasks

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.seenu.minerva2.data.goals.Goal

@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = Goal::class,
            parentColumns = ["goalID"],
            childColumns = ["goalID"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Task(
    val taskName: String,
    var isCompleted : Boolean = false,
    val currentDate : String,
    val goalID: Int,
    @PrimaryKey(autoGenerate = true)
    val taskID: Int? = null,
)
