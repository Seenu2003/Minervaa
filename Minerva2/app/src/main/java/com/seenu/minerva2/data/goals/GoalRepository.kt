package com.seenu.minerva2.data.goals

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GoalRepository @Inject constructor(private val goalDao: GoalDao){
    suspend fun insertGoal(goal:Goal){
        goalDao.insertGoal(goal)
    }
    suspend fun deleteGoal(goal: Goal){
        goalDao.deleteGoal(goal)
    }
    fun getGoals() : LiveData<List<Goal>> {
        return goalDao.getGoals()
    }
    suspend fun updateGoal(goal: Goal){
        goalDao.updateGoal(goal)
    }
    fun getGoalById(goalId: Int): LiveData<Goal> {
        return goalDao.getGoalById(goalId)
    }
    fun getGoalsByDate(currentDate: String): LiveData<List<Goal>> {
        return goalDao.getGoalsByDate(currentDate)
    }
    suspend fun deleteGoalById(goalId: Int) {
        return goalDao.deleteGoalById(goalId)
    }
}
