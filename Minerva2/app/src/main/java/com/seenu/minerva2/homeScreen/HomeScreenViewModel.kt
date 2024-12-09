package com.seenu.minerva2.homeScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seenu.minerva2.data.goalStats.GoalStats
import com.seenu.minerva2.data.goalStats.GoalStatsRepository
import com.seenu.minerva2.data.goals.Goal
import com.seenu.minerva2.data.goals.GoalRepository
import com.seenu.minerva2.data.tasks.Task
import com.seenu.minerva2.data.tasks.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val goalRepository: GoalRepository,
    private val taskRepository: TaskRepository,
     val goalStatsRepository: GoalStatsRepository
) : ViewModel(){
    val goals : LiveData<List<Goal>> = goalRepository.getGoals()
    val tasks : LiveData<List<Task>> = taskRepository.getTasks()

    fun onTaskCompleted(taskId: Int, goalId: Int) {
        viewModelScope.launch {
            taskRepository.completeTaskAndUpdateStats(taskId, goalId, goalStatsRepository)
        }
    }
    fun deleteTask(task: Task){
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }
    fun deleteTaskAndUpdateStats(task: Task){
        viewModelScope.launch {
            taskRepository.deleteTaskAndUpdateStats(task)
        }
    }
}