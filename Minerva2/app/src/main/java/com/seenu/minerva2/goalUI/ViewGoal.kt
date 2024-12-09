package com.seenu.minerva2.goalUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.seenu.minerva2.ui.theme.MinervaTypography
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewGoal(viewModel : ViewGoalViewModel,
             navController: NavController){
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
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
                        navController.navigate("HomeScreen") }
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
                        navController.navigate("ViewGoal") }
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
                        navController.navigate("UserProfile") }
                )
            }
        }
    ){
        val goals by viewModel.goals.observeAsState(emptyList())
        val noGoals = remember { mutableStateOf(false) }
        val goalStats by viewModel.goalStats.observeAsState(emptyList())
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
                    },
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {navController.navigate("CreateGoal")}) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)}
            }
        ){
                paddingValues -> Column(
            modifier = Modifier.padding(paddingValues)
        ){
            if(goals.isNotEmpty()){
                LazyColumn{
                    items(goals.reversed()){
                            goal ->
                        val statsForGoal = goalStats.filter { it.goalID==goal.goalID }
                        Card(
                            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f).padding(10.dp),
                            shape = RoundedCornerShape(30.dp),
                            elevation = CardDefaults.cardElevation(9.dp),

                            ){
                            Column(
                                modifier = Modifier.fillMaxSize().padding(5.dp)
                            ){
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(6.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Text(text = goal.goalName, style = MinervaTypography.headlineLarge)
                                    IconButton(onClick = {
                                        viewModel.deleteGoal(goal.goalID?: Int.MAX_VALUE)
                                    }) {
                                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Goal")
                                    }
                                }
                                Divider()
                                Text(text = goal.desc, style = MinervaTypography.bodySmall,
                                    modifier = Modifier.padding(6.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    AssistChip(onClick = {}, label = { Text(text = goal.priority)},
                                        shape = RoundedCornerShape(20.dp), modifier = Modifier.padding(3.dp))
                                    AssistChip(onClick = {}, label = { Text(text = goal.category)},
                                        shape = RoundedCornerShape(20.dp), modifier = Modifier.padding(3.dp))
                                    AssistChip(onClick = {}, label = { Text(text = goal.deadline)},
                                        shape = RoundedCornerShape(20.dp), modifier = Modifier.padding(3.dp))
                                }

                            }
                            statsForGoal.forEach {
                                goalStats ->
                                Column(
                                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                                ){
                                    Row(
                                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ){
                                        Text(text = "Total Tasks :",style = MinervaTypography.bodySmall)
                                        Text(text = goalStats.totalTasks.toString(), style = MinervaTypography.bodySmall)
                                    }
                                    Row(
                                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ){
                                        Text(text = "Completed Tasks :",style = MinervaTypography.bodySmall)
                                        Text(text = goalStats.completedTasks.toString(), style = MinervaTypography.bodySmall)
                                    }
                                    Row(
                                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ){
                                        Text(text = "Fulfillment Rate :",style = MinervaTypography.bodySmall)
                                        Text(text = goalStats.fulfillmentRate.toString(), style = MinervaTypography.bodySmall)
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
            else if(noGoals.value){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Text(text = "No Goals Found Yet.")
                }
            }
        }
        }
    }
}