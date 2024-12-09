package com.seenu.minerva2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.seenu.minerva2.ui.theme.Minerva2Theme
import com.seenu.minerva2.welcomeUser.UserRegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seenu.minerva2.chatbot.ChatBotViewModel
import com.seenu.minerva2.chatbot.Chatbot
import com.seenu.minerva2.goalUI.CreateGoal
import com.seenu.minerva2.goalUI.CreateGoalViewModel
import com.seenu.minerva2.goalUI.ViewGoal
import com.seenu.minerva2.goalUI.ViewGoalViewModel
import com.seenu.minerva2.homeScreen.HomeScreen
import com.seenu.minerva2.homeScreen.HomeScreenViewModel
import com.seenu.minerva2.taskUI.CreateTask
import com.seenu.minerva2.taskUI.CreateTaskViewModel
import com.seenu.minerva2.welcomeUser.ProfilePageViewModel
import com.seenu.minerva2.welcomeUser.UserProfile
import com.seenu.minerva2.welcomeUser.UserRegistration

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Minerva2Theme {
                Surface{
                    RootApplication()
                }
            }
        }
    }
}

@Composable
fun RootApplication() {
    val navController = rememberNavController()
    val createGoalViewModel = hiltViewModel<CreateGoalViewModel>()
    val viewGoalViewModel = hiltViewModel<ViewGoalViewModel>()
    val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
    val addTaskViewModel = hiltViewModel<CreateTaskViewModel>()
    val userRegistrationViewModel = hiltViewModel<UserRegistrationViewModel>()
    val profilePageViewModel = hiltViewModel<ProfilePageViewModel>()
    val chatbotViewModel = hiltViewModel<ChatBotViewModel>()
    NavHost(navController = navController, startDestination = "HomeScreen") {
        composable("ViewGoal") {
            ViewGoal(viewGoalViewModel, navController)
        }
        composable("CreateGoal") {
            CreateGoal(createGoalViewModel, navController)
        }
        composable("HomeScreen") {
            HomeScreen(homeScreenViewModel, navController)
        }
        composable("AddTask") {
            CreateTask(addTaskViewModel, navController)
        }
        composable("UserRegistration") {
            UserRegistration(userRegistrationViewModel, navController)
        }
        composable("UserProfile") {
            UserProfile(profilePageViewModel, navController)
        }
        composable("ChatBot"){
            Chatbot(navController,chatbotViewModel)
        }
    }
}
