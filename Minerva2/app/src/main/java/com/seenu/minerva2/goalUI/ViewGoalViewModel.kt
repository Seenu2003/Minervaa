package com.seenu.minerva2.goalUI

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seenu.minerva2.data.goalStats.GoalStats
import com.seenu.minerva2.data.goalStats.GoalStatsRepository
import com.seenu.minerva2.data.goals.Goal
import com.seenu.minerva2.data.goals.GoalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewGoalViewModel @Inject constructor(
   private val goalRepository: GoalRepository,
    private val goalStatsRepository: GoalStatsRepository
) : ViewModel() {
    val goals : LiveData<List<Goal>> = goalRepository.getGoals()
    val goalStats : LiveData<List<GoalStats>> = goalStatsRepository.getAllStats()
    fun deleteGoal(goalId : Int){
        viewModelScope.launch {
            goalRepository.deleteGoalById(goalId)
        }
    }

}