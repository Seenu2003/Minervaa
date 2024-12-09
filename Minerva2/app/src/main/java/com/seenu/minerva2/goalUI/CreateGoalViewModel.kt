package com.seenu.minerva2.goalUI

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seenu.minerva2.data.goals.Goal
import com.seenu.minerva2.data.goals.GoalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateGoalViewModel @Inject constructor(
    private val goalRepository: GoalRepository
) : ViewModel() {
    val goalname = mutableStateOf("")
    val desc = mutableStateOf<String?>(null)
    val priority = mutableStateOf("")
    val category = mutableStateOf("")
    val deadline = mutableStateOf("")
    val currentDate = mutableStateOf("")

    fun validateData() : Boolean{
        return goalname.value.isNotEmpty() &&
                priority.value.isNotEmpty() &&
                category.value.isNotEmpty() &&
                deadline.value.isNotEmpty()
    }

    fun insertGoal() {
        if (!validateData()) {
            Log.d("Insert Goal", "Failed, missing fields.")
            return
        }
        viewModelScope.launch {
            try {
                goalRepository.insertGoal(
                    Goal(
                        goalName = goalname.value,
                        desc = desc.value.orEmpty(), // Use empty string if desc is null
                        priority = priority.value,
                        category = category.value,
                        deadline = deadline.value,
                        currentDate = currentDate.value
                    )
                )
                Log.d("Insert Goal", "Goal inserted successfully.")
            } catch (e: Exception) {
                Log.e("Insert Goal", "Error inserting goal: ${e.message}")
            }
        }
    }
}