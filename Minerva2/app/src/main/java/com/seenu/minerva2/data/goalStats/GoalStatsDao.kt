package com.seenu.minerva2.data.goalStats

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface GoalStatsDao {
    @Insert
    suspend fun insertGoalStats(goalStats: GoalStats)

    @Update
    suspend fun updateGoalStats(goalStats: GoalStats)

    @Upsert
    suspend fun upsertGoalStats(goalStats: GoalStats)

    @Query("SELECT * FROM goalStats WHERE goalID = :goalId")
    suspend fun getGoalStatsByGoalId(goalId: Int): GoalStats?

    @Query("SELECT * FROM goalStats WHERE goalID = :goalId")
    fun getGoalStatsLiveDataByGoalId(goalId: Int): LiveData<GoalStats>

    @Query("SELECT COUNT(*) FROM tasks WHERE goalID = :goalId")
    suspend fun getTotalTasksForGoal(goalId: Int): Int

    @Query("SELECT COUNT(*) FROM tasks WHERE goalID = :goalId AND isCompleted = 1")
    suspend fun getCompletedTasksForGoal(goalId: Int): Int

    suspend fun getFulfillmentRateForGoal(totalTasks:Int, completedTasks: Int): Double {
        return if (totalTasks > 0) {
            (completedTasks.toDouble() / totalTasks) * 100
        } else {
            0.0
        }
    }
    @Query("SELECT * FROM goalStats")
    fun getAllStats(): LiveData<List<GoalStats>>
}