package com.seenu.minerva2.taskUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarView
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.seenu.minerva2.ui.theme.MinervaTypography
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTask(createTaskViewModel: CreateTaskViewModel,
               navController: NavController) {
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
    ){
        val sheetState = rememberSheetState()
        val goals by createTaskViewModel.goals.observeAsState(emptyList())
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
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier.fillMaxSize().padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                ) {
                    // heading
                    Text(text = "Create Your Task.", style = MinervaTypography.displaySmall)
                    Divider(modifier = Modifier.padding(5.dp))
                }
                // name
                Row(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Name of Task : ")
                    OutlinedTextField(
                        value = createTaskViewModel.taskName.value, onValueChange = {
                            createTaskViewModel.taskName.value = it
                        },
                        shape = RoundedCornerShape(25.dp)
                    )
                }
                // goal
                Text(text = "Under the goal.", modifier = Modifier.padding(10.dp))
                LazyRow {
                    items(goals.reversed().size) { goal ->
                        AssistChip(
                            onClick = { createTaskViewModel.goalId.value = if(createTaskViewModel.goalId.value==goals[goal].goalID) null else goals[goal].goalID },
                            label = { Text(text = goals[goal].goalName) },
                            shape = RoundedCornerShape(25.dp),
                            modifier = Modifier.padding(5.dp),
                            leadingIcon = {if(createTaskViewModel.goalId.value==goals[goal].goalID){
                                Icon(imageVector = Icons.Default.Done, contentDescription = "Selected")
                            }}
                        )
                    }
                }
                // calendar
                CalendarView(
                    sheetState = sheetState,
                    config = CalendarConfig(
                        monthSelection = false,
                        yearSelection = false,
                        style = CalendarStyle.WEEK
                    ),
                    selection = CalendarSelection.Date(
                        withButtonView = false
                    ){
                            date ->
                        createTaskViewModel.currentDate.value=date.toString()
                    },
                    header = Header.Custom{
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(text = "Selected Date for Task", style = MinervaTypography.bodySmall,
                                modifier = Modifier.padding(10.dp))
                            AssistChip(onClick = {}, label = { Text(text =
                            if(createTaskViewModel.currentDate.value==LocalDate.now().toString())"Today"
                            else createTaskViewModel.currentDate.value)
                            },
                                shape = RoundedCornerShape(25.dp), modifier = Modifier.padding(10.dp))
                        }
                    }
                )
                createTaskViewModel.isCompleted.value=false
                // notes
                Column(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Note", style = MinervaTypography.labelLarge)
                    Text(text = "All tasks are considered, not complete by default. You will be able to change it from the Home-screen easily.", style =
                    MinervaTypography.labelMedium)
                    Text(text = "Sometimes the calendar may glitch and dates may not be visible, So just go to the start or end of the month to fix it. We will fix this shortly.",
                        style = MinervaTypography.labelMedium)
                }
                // button
                Column(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        if(createTaskViewModel.validateData()){
                            createTaskViewModel.createTask()
                        }
                        else{
                            createTaskViewModel.showdialog.value=true
                        }
                        createTaskViewModel.taskName.value=""
                        createTaskViewModel.goalId.value=null
                        createTaskViewModel.currentDate.value=LocalDate.now().toString()
                       navController.navigate("HomeScreen")
                    }) {
                        Text(text = "Add Task.", style = MinervaTypography.bodyLarge)
                    }
                }
            }
        }
        // dialog
        if(createTaskViewModel.showdialog.value){
            AlertDialog(
                onDismissRequest = {createTaskViewModel.showdialog.value=false},
                title = { Text(text = "Missing or Incorrect Details entered.") },
                text = { Text(text = "Please verify the details again") },
                confirmButton = { Button(onClick = {createTaskViewModel.showdialog.value=false}){
                    Text(text = "OK")
                } }
            )
        }
    }
}