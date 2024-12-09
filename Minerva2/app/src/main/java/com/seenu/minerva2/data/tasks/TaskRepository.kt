package com.seenu.minerva2.data.tasks

import androidx.lifecycle.LiveData
import com.seenu.minerva2.data.goalStats.GoalStats
import com.seenu.minerva2.data.goalStats.GoalStatsDao
import com.seenu.minerva2.data.goalStats.GoalStatsRepository
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao,
    val goalStatsRepository: GoalStatsRepository) {
    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

    // Delete a task
    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    // Get all tasks as LiveData
    fun getTasks(): LiveData<List<Task>> {
        return taskDao.getTasks()
    }

    // Get a specific task by its ID
    fun getTaskById(taskId: Int): LiveData<Task> {
        return taskDao.getTaskById(taskId)
    }

    // Get tasks for a specific date
    fun getTasksByDate(currentDate: String): LiveData<List<Task>> {
        return taskDao.getTasksByDate(currentDate)
    }

    // Get tasks for a specific goal
    fun getTasksByGoalId(goalId: Int): LiveData<List<Task>> {
        return taskDao.getTasksByGoalId(goalId)
    }

    // Get the total number of tasks for a specific goal
    suspend fun getTotalTasksForGoal(goalId: Int): Int {
        return taskDao.getTotalTasksForGoalTaskDao(goalId)
    }

    // Get the number of completed tasks for a specific goal
    suspend fun getCompletedTasksForGoal(goalId: Int): Int {
        return taskDao.getCompletedTasksForGoalTaskDao(goalId)
    }
    suspend fun insertTaskAndUpdateStats(task: Task, goalStatsRepository: GoalStatsRepository){
        taskDao.insertTaskAndUpdateStats(task,goalStatsRepository)
    }
    suspend fun completeTaskAndUpdateStats(taskId: Int,goalId: Int, goalStatsRepository: GoalStatsRepository){
        return taskDao.completeTaskAndUpdateStats(taskId,goalId,goalStatsRepository)
    }
    suspend fun deleteTaskAndUpdateStats(task: Task) {
        // Delete the task
        taskDao.deleteTask(task)

        // Retrieve current stats
        val currentStats = goalStatsRepository.getGoalStatsByGoalId(task.goalID) ?: GoalStats(
            goalID = task.goalID,
            totalTasks = 0,
            completedTasks = 0,
            fulfillmentRate = 0.0
        )

        // Determine if the task was completed
        val wasTaskCompleted = task.isCompleted // Assuming `isCompleted` is a property of Task

        // Update stats
        val updatedStats = currentStats.copy(
            totalTasks = maxOf(currentStats.totalTasks - 1, 0),
            completedTasks = if (wasTaskCompleted) {
                maxOf(currentStats.completedTasks - 1, 0)
            } else {
                currentStats.completedTasks
            },
            fulfillmentRate = if (currentStats.totalTasks > 1) {
                calculateFullfilmateRate(
                    completedTasks = if (wasTaskCompleted) currentStats.completedTasks - 1 else currentStats.completedTasks,
                    totalTasks = currentStats.totalTasks - 1
                )
            } else {
                0.0
            }
        )

        // Save updated stats
        goalStatsRepository.upsertGoalStats(updatedStats)
    }
    private fun calculateFullfilmateRate(completedTasks: Int, totalTasks: Int): Double {
        return if (totalTasks > 0) (completedTasks.toDouble() / totalTasks) * 100 else 0.0
    }
}