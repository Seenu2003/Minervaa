package com.seenu.minerva2.data.goalStats

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.seenu.minerva2.data.goals.Goal

@Entity(
    tableName = "goalStats",
    foreignKeys = [
        ForeignKey(
            entity = Goal::class, // Referencing the Goal entity
            parentColumns = ["goalID"], // The primary key of the Goal entity
            childColumns = ["goalID"], // The foreign key in GoalStats
            onDelete = ForeignKey.CASCADE // If the Goal is deleted, the associated GoalStats will be deleted
        )
    ]
)
data class GoalStats(
    @PrimaryKey
    val goalID: Int,  // This is the foreign key referencing Goal
    val totalTasks: Int,
    val completedTasks: Int,
    val fulfillmentRate: Double
)
