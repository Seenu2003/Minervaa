package com.seenu.minerva2.welcomeUser

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seenu.minerva2.data.userDetails.UserDetails
import com.seenu.minerva2.data.userDetails.UserDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRegistrationViewModel @Inject constructor(
   private val userDetailsRepository: UserDetailsRepository): ViewModel() {

    val name = mutableStateOf("")
    val username = mutableStateOf("")
    val email = mutableStateOf("")
    val phoneNumber = mutableStateOf("")
    val password = mutableStateOf("")

    fun validateData() : Boolean{
        return name.value.isNotEmpty() &&
                username.value.isNotEmpty() &&
                email.value.isNotEmpty() &&
                phoneNumber.value.isNotEmpty()
                && password.value.isNotEmpty()
    }

    fun insertData() {
        if (validateData()) {
            viewModelScope.launch {
                userDetailsRepository.insertUserDetails(
                    UserDetails(
                        name = name.value,
                        username = username.value,
                        email = email.value,
                        phoneNumber = phoneNumber.value,
                        password = password.value
                    )
                )
                Log.d("UserRegistration", "Data inserted successfully")
            }
        } else {
            Log.e("UserRegistration", "Validation failed: Missing required fields")
        }
    }

}