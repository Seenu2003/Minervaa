package com.seenu.minerva2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seenu.minerva2.data.goalStats.GoalStats
import com.seenu.minerva2.data.goalStats.GoalStatsDao
import com.seenu.minerva2.data.goals.Goal
import com.seenu.minerva2.data.goals.GoalDao
import com.seenu.minerva2.data.tasks.Task
import com.seenu.minerva2.data.tasks.TaskDao
import com.seenu.minerva2.data.userDetails.UserDetails
import com.seenu.minerva2.data.userDetails.UserDetailsDao

@Database(
    entities = [Goal::class, Task::class, GoalStats::class, UserDetails::class],
    version = 1,
    exportSchema = false
)
abstract class MinervaDatabase : RoomDatabase() {
    abstract fun goalDao(): GoalDao
    abstract fun taskDao(): TaskDao
    abstract fun goalStatsDao(): GoalStatsDao
    abstract fun userDetailsDao(): UserDetailsDao
}

