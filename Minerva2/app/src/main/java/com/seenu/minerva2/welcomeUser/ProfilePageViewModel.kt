package com.seenu.minerva2.welcomeUser

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seenu.minerva2.data.userDetails.UserDetails
import com.seenu.minerva2.data.userDetails.UserDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfilePageViewModel @Inject constructor(
    private val userDetailsRepository: UserDetailsRepository
) : ViewModel() {
    private val _accountInfo = mutableStateOf<UserDetails?>(null)
    val accountInfo: State<UserDetails?> = _accountInfo

    init {
        // Fetch user data asynchronously
        viewModelScope.launch {
            val userDetails = userDetailsRepository.getUserByID(1)
            _accountInfo.value = userDetails
        }
    }

    val name: String? get() = accountInfo.value?.name
    val username: String? get() = accountInfo.value?.username
    val phoneNumber: String? get() = accountInfo.value?.phoneNumber
    val email: String? get() = accountInfo.value?.email
}