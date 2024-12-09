package com.seenu.minerva2.data.goals

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface GoalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: Goal)

    @Delete
    suspend fun deleteGoal(goal: Goal)

    @Query("SELECT * FROM goals")
    fun getGoals() : LiveData<List<Goal>>

    @Update
    suspend fun updateGoal(goal: Goal)

    @Query("DELETE FROM goals WHERE goalID = :goalId")
    suspend fun deleteGoalById(goalId: Int)

    @Query("SELECT * FROM goals WHERE goalID = :goalId")
    fun getGoalById(goalId: Int): LiveData<Goal>

    @Query("SELECT * FROM goals WHERE currentDate= :currentDate")
    fun getGoalsByDate(currentDate: String): LiveData<List<Goal>>

}