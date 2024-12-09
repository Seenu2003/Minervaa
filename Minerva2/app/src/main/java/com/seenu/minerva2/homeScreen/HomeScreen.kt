package com.seenu.minerva2.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.seenu.minerva2.R
import com.seenu.minerva2.data.goalStats.GoalStatsRepository
import com.seenu.minerva2.data.tasks.Task
import com.seenu.minerva2.ui.theme.MinervaTypography
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel,
    navController: NavController
){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerItem(
                    label = { Row(
                        modifier = Modifier.padding(5.dp).fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Icon(imageVector = Icons.Default.Home, contentDescription = null,
                            modifier = Modifier.size(50.dp).padding(5.dp))
                        Text(text = "Home")
                    } },
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate("HomeScreen")
                    }
                )
                NavigationDrawerItem(
                    label = { Row(
                        modifier = Modifier.padding(5.dp).fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = null,
                            modifier = Modifier.size(50.dp).padding(5.dp))
                        Text(text = "Goals")
                    } },
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate("ViewGoal")
                    }
                )
                NavigationDrawerItem(
                    label = { Row(
                        modifier = Modifier.padding(5.dp).fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(imageVector = Icons.Default.AccountBox, contentDescription = null,
                            modifier = Modifier.size(50.dp).padding(5.dp))
                        Text(text = "User Details")
                    } },
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate("UserProfile")
                    }
                )
            }
        },
        drawerState = drawerState
    ) {
        val goals by homeScreenViewModel.goals.observeAsState(emptyList())
        val tasks by homeScreenViewModel.tasks.observeAsState(emptyList())
        val noGoals = remember { mutableStateOf(false) }
        LaunchedEffect(goals) {
            if(goals.isEmpty()){
                delay(100)
                noGoals.value=true
            }
            else{
                noGoals.value=false
            }
        }
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Minerva", style = MinervaTypography.headlineLarge) },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                        }
                    }
                )
            },
            floatingActionButton = {FloatingActionButton(onClick = {navController.navigate("ChatBot")}) {
                Icon(painter = painterResource(R.drawable._23), contentDescription = null,
                    modifier = Modifier.size(40.dp))
            }
            }
        ){
                paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = LocalDate.now().toString(), style = MinervaTypography.headlineLarge)
                }
                Divider(modifier = Modifier.padding(5.dp))
                Column(
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Warning", style = MinervaTypography.headlineLarge)
                    Text(text = "You cannot un-click a task, only click checked if you completed it. Otherwise your stats will be wrong.",
                        style = MinervaTypography.labelLarge)
                }
                if(goals.isNotEmpty()){
                    LazyColumn {
                        items(goals.reversed()){
                                goal->
                            val taskForGoal = tasks.filter { it.goalID == goal.goalID }
                            Card(
                                modifier = Modifier.fillMaxWidth().padding(10.dp),
                                elevation = CardDefaults.cardElevation(10.dp),
                                shape = RoundedCornerShape(25.dp)
                            ){
                                Column(
                                    modifier = Modifier.padding(10.dp)
                                ){
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ){
                                        Text(text = goal.goalName, style = MinervaTypography.headlineLarge)
                                        Button(onClick = {navController.navigate("AddTask")}) {
                                            Text(text = "Add Task")
                                        }
                                    }
                                    Divider(modifier = Modifier.padding(5.dp))
                                    Text(text = goal.desc, style = MinervaTypography.bodyMedium)
                                    taskForGoal.forEach {
                                            task ->
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ){
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically
                                            ){
                                                Checkbox(
                                                    checked = task.isCompleted,
                                                    onCheckedChange = {
                                                            isChecked ->
                                                        if(!task.isCompleted){
                                                            homeScreenViewModel.onTaskCompleted(task.taskID?: Int.MAX_VALUE,goal.goalID?: Int.MAX_VALUE)
                                                        }
                                                    }
                                                )
                                                Text(text = task.taskName, style = MinervaTypography.bodySmall)
                                            }
                                            IconButton(onClick = {
                                                homeScreenViewModel.deleteTaskAndUpdateStats(Task(
                                                    task.taskName,
                                                    task.isCompleted,
                                                    task.currentDate,
                                                    task.goalID,
                                                    task.taskID
                                                ))
                                            }) {
                                                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    }
                else if(noGoals.value){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(text = "No Goals Found Yet. Click on the menu to add new goals.")
                    }
                }
            }
        }
    }
}