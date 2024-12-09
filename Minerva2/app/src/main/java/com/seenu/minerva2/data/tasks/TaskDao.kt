package com.seenu.minerva2.data.tasks

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.seenu.minerva2.data.goalStats.GoalStats
import com.seenu.minerva2.data.goalStats.GoalStatsDao
import com.seenu.minerva2.data.goalStats.GoalStatsRepository

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM tasks")
    fun getTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE taskID = :taskId")
    fun getTaskById(taskId: Int): LiveData<Task>

    @Query("SELECT * FROM tasks WHERE currentDate= :currentDate")
    fun getTasksByDate(currentDate: String): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE goalID = :goalId")
    fun getTasksByGoalId(goalId: Int): LiveData<List<Task>>

    @Query("SELECT COUNT(*) FROM tasks WHERE goalID = :goalId")
    suspend fun getTotalTasksForGoalTaskDao(goalId: Int): Int

    @Query("SELECT COUNT(*) FROM tasks WHERE goalID = :goalId AND isCompleted = 1")
    suspend fun getCompletedTasksForGoalTaskDao(goalId: Int): Int

    @Query("UPDATE tasks SET isCompleted = 1 WHERE taskID = :taskId")
    suspend fun markTaskAsCompleted(taskId: Int)

    @Transaction
    suspend fun insertTaskAndUpdateStats(task: Task, goalStatsRepository: GoalStatsRepository){
        insertTask(task)
        val currentStats = goalStatsRepository.getGoalStatsByGoalId(task.goalID)?: GoalStats(
            goalID = task.goalID,
            totalTasks = 0,
            completedTasks = 0,
            fulfillmentRate = 0.0
        )
        val updatedStats = currentStats.copy(
            totalTasks = currentStats.totalTasks+1,
            fulfillmentRate = calculateFullfilmateRate(currentStats.completedTasks, currentStats.completedTasks)
        )
        goalStatsRepository.upsertGoalStats(updatedStats)
    }
    @Transaction
    suspend fun deleteTaskAndUpdateStats(task: Task, goalStatsRepository: GoalStatsRepository) {
        deleteTask(task)
        val currentStats = goalStatsRepository.getGoalStatsByGoalId(task.goalID) ?: GoalStats(
            goalID = task.goalID,
            totalTasks = 0,
            completedTasks = 0,
            fulfillmentRate = 0.0
        )

        val updatedStats = currentStats.copy(
            totalTasks = maxOf(currentStats.totalTasks - 1, 0),
            fulfillmentRate = if (currentStats.totalTasks > 1) {
                calculateFullfilmateRate(currentStats.completedTasks, currentStats.totalTasks - 1)
            } else {
                0.0
            }
        )

        goalStatsRepository.upsertGoalStats(updatedStats)
    }

    private fun calculateFullfilmateRate(completedTasks: Int, totalTasks: Int): Double {
        return if (totalTasks > 0) (completedTasks.toDouble() / totalTasks) * 100 else 0.0
    }
    @Transaction
    suspend fun completeTaskAndUpdateStats(taskId: Int, goalId: Int, goalStatsRepository: GoalStatsRepository) {
        // Mark the task as completed
        markTaskAsCompleted(taskId)

        // Fetch current GoalStats or create a new one if not present
        val currentStats = goalStatsRepository.getGoalStatsByGoalId(goalId)
            ?: GoalStats(goalID = goalId, totalTasks = 0, completedTasks = 0, fulfillmentRate = 0.0)

        // Update completedTasks and calculate fulfillmentRate
        val updatedStats = currentStats.copy(
            completedTasks = currentStats.completedTasks + 1,
            fulfillmentRate = calculateFullfilmateRate(currentStats.completedTasks + 1, currentStats.totalTasks)
        )
        goalStatsRepository.upsertGoalStats(updatedStats) // Upsert updated stats
    }

}