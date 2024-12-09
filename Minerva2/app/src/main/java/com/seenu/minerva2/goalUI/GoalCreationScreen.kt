package com.seenu.minerva2.goalUI

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.seenu.minerva2.data.goals.Goal
import com.seenu.minerva2.ui.theme.MinervaTypography
import kotlinx.coroutines.launch
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGoal(createGoalViewModel: CreateGoalViewModel, navController: NavController){
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
                        navController.navigate("HomeScreen")
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
                        navController.navigate("HomeScreen")
                    }
                )
            }
        },
        drawerState = drawerState
    ){
        val openAlertDialog = remember { mutableStateOf(false) }
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {Text(text = "Minerva", style = MinervaTypography.headlineLarge)},
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
        ){
                innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ){
                val categoryList = listOf("Personal","Professional","Fitness","Hobbies","Other")
                val priorityList = listOf("Critical","High","Medium","Low")
                val sheetState = rememberSheetState()
                CalendarDialog(state = sheetState, config = CalendarConfig(
                    monthSelection = true,
                    yearSelection = true
                ), selection = CalendarSelection.Date{
                        date ->
                    createGoalViewModel.deadline.value=date.toString()
                    createGoalViewModel.currentDate.value=LocalDate.now().toString()
                    Log.d("Date","${createGoalViewModel.deadline}")
                })
                Column(
                    modifier = Modifier.fillMaxSize().padding(10.dp).verticalScroll(
                        state = rememberScrollState()
                    ),
                ){
                    // Heading
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(text = "Create a Goal",
                            style = MinervaTypography.displayMedium)
                    }
                    Divider(
                        modifier = Modifier.padding(10.dp)
                    )

                    // Name.
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(text = "Name of Goal", style = MinervaTypography.bodyMedium)
                        OutlinedTextField(
                            value = createGoalViewModel.goalname.value,
                            onValueChange = {createGoalViewModel.goalname.value=it},
                            placeholder = { Text(text = "eg: Become Skilled", style = MinervaTypography.bodySmall) },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.fillMaxWidth(0.9f)
                        )
                    }

                    // Category of Goal
                    Text(text = "Category of Goal", style = MinervaTypography.bodyMedium)
                    Spacer(modifier = Modifier.height(5.dp))
                    LazyRow{
                        items(categoryList){
                                category -> AssistChip(onClick = {
                            createGoalViewModel.category.value =
                                if (createGoalViewModel.category.value == category) "" else category
                        }, label = { Text(text = category, style = MinervaTypography.bodySmall) },
                            leadingIcon = {if(createGoalViewModel.category.value == category){
                                Icon(imageVector = Icons.Default.Done, contentDescription = "Selected")
                            } },
                            shape = RoundedCornerShape(20.dp)
                        )
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }

                    // Priority of Goal
                    Text(text = "Priority of Goal", style = MinervaTypography.bodyMedium)
                    Spacer(modifier = Modifier.height(5.dp))

                    LazyRow {
                        items(priorityList){
                                priority -> AssistChip(
                            onClick = {
                                createGoalViewModel.priority.value =
                                    if (createGoalViewModel.priority.value == priority) "" else priority
                            },
                            label = { Text(text = priority, style = MinervaTypography.bodySmall) },
                            shape = RoundedCornerShape(20.dp),
                            leadingIcon = {if(createGoalViewModel.priority.value == priority){
                                Icon(imageVector = Icons.Default.Done, contentDescription = "Selected")
                            }}
                        )
                            Spacer(modifier = Modifier.width(5.dp))
                        }
                    }
                    // Deadline
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        OutlinedTextField(
                            value = createGoalViewModel.deadline.value,
                            onValueChange = {},
                            shape = RoundedCornerShape(20.dp),
                            placeholder = { Text(text = "YYYY-MM-DD", style = MinervaTypography.bodySmall)},
                            modifier = Modifier.fillMaxWidth(0.52f)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Button(onClick = {sheetState.show()}) {
                            Text(text = "Select Deadline", style = MinervaTypography.labelLarge)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    // Description
                    Column {
                        Text(text = "Description", style = MinervaTypography.bodyMedium)
                        Spacer(modifier = Modifier.height(5.dp))
                        OutlinedTextField(
                            value = createGoalViewModel.desc.value?:"",
                            onValueChange = {createGoalViewModel.desc.value=it},
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.size(500.dp,200.dp)
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Button(onClick = {
                            createGoalViewModel.validateData()
                            if(createGoalViewModel.validateData()){
                                createGoalViewModel.insertGoal()
                                navController.navigate("ViewGoal")
                            }
                            else{
                                openAlertDialog.value=true
                            }
                            // reset all values.
                            createGoalViewModel.goalname.value=""
                            createGoalViewModel.desc.value=""
                            createGoalViewModel.category.value=""
                            createGoalViewModel.priority.value=""
                            createGoalViewModel.deadline.value=""
                        }) {
                            Text(text = "Create Goal", style = MinervaTypography.bodyLarge)
                        }
                    }
                }
            }
        }
        if(openAlertDialog.value){
            AlertDialog(
                onDismissRequest = {openAlertDialog.value=false},
                title = { Text(text = "Missing information") },
                text = { Text(text = "Some data regarding the goal is missing.") },
                confirmButton = { Button(onClick = {openAlertDialog.value=false}){ Text(text = "OK") } }
            )
        }
    }
}