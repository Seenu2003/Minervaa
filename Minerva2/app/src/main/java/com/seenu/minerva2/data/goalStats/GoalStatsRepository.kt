package com.seenu.minerva2.data.goalStats

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GoalStatsRepository @Inject constructor(private val goalStatsDao: GoalStatsDao){

    suspend fun insertGoalStats(goalStats: GoalStats) {
        goalStatsDao.insertGoalStats(goalStats)
    }

    suspend fun updateGoalStats(goalStats: GoalStats) {
        goalStatsDao.updateGoalStats(goalStats)
    }

    suspend fun upsertGoalStats(goalStats: GoalStats){
        goalStatsDao.upsertGoalStats(goalStats)
    }

    suspend fun getGoalStatsByGoalId(goalId: Int): GoalStats? {
        return goalStatsDao.getGoalStatsByGoalId(goalId)
    }
    fun getAllStats(): LiveData<List<GoalStats>> {
        return goalStatsDao.getAllStats()
    }
    fun getGoalStatsLiveDataByGoalId(goalId: Int): LiveData<GoalStats> {
        return goalStatsDao.getGoalStatsLiveDataByGoalId(goalId)
    }

    suspend fun getTotalTasksForGoal(goalId: Int): Int {
        return goalStatsDao.getTotalTasksForGoal(goalId)
    }

    suspend fun getCompletedTasksForGoal(goalId: Int): Int {
        return goalStatsDao.getCompletedTasksForGoal(goalId)
    }

    suspend fun getFulfillmentRateForGoal(totalTask:Int,completedTask:Int): Double {
        return goalStatsDao.getFulfillmentRateForGoal(totalTask,completedTask)
    }
}