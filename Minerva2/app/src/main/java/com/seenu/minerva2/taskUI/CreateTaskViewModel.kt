package com.seenu.minerva2.taskUI

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
class CreateTaskViewModel @Inject constructor(
    private  val taskRepository: TaskRepository,
    private val goalRepository: GoalRepository,
    private val goalStatsRepository: GoalStatsRepository
) : ViewModel(){
    val goals : LiveData<List<Goal>> = goalRepository.getGoals()
    val taskName = mutableStateOf("")
    val currentDate = mutableStateOf(LocalDate.now().toString())
    val isCompleted = mutableStateOf(false)
    val goalId = mutableStateOf<Int?>(null)
    val showdialog = mutableStateOf(false)
    fun validateData() : Boolean {
        return taskName.value.isNotEmpty() && currentDate.value.isNotEmpty()
                && goalId.value!=null
    }
    fun createTask(){
        if(validateData()){
            viewModelScope.launch {
                taskRepository.insertTaskAndUpdateStats(
                    Task(
                        taskName = taskName.value,
                        isCompleted = isCompleted.value,
                        currentDate = currentDate.value,
                        goalID = goalId.value?:Int.MAX_VALUE
                    ),
                    goalStatsRepository
                )
            }
        }
    }
}