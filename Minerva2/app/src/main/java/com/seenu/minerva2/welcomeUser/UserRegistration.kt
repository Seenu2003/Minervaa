package com.seenu.minerva2.welcomeUser

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.seenu.minerva2.ui.theme.MinervaTypography
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRegistration(viewModel: UserRegistrationViewModel, navController: NavController){
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
        val invalidData = remember { mutableStateOf(false) }
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Minerva", style = MinervaTypography.headlineLarge) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch {
                            drawerState.open()
                        } }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                        }
                    }
                )
            }
        ){
                paddingValues ->  Column(
            modifier = Modifier.padding(paddingValues)
        ){

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Text(text = "Create your Profile.", style = MinervaTypography.displayMedium, modifier = Modifier.padding(5.dp))
                //subheading
                Divider(modifier = Modifier.padding(2.dp))
                Text(text = "The first step towards creating habits that will last forever. Let's go on a journey towards a better you.", style = MinervaTypography.bodyMedium,
                    modifier = Modifier.padding(8.dp))

                // name
                Row(
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = "Name", style = MinervaTypography.bodyMedium)
                    OutlinedTextField(
                        value = viewModel.name.value,
                        onValueChange = {viewModel.name.value=it},
                        shape = RoundedCornerShape(20.dp)
                    )
                }

                // username
                Row(
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = "User-Name", style = MinervaTypography.bodyMedium)
                    OutlinedTextField(
                        value = viewModel.username.value,
                        onValueChange = {viewModel.username.value=it},
                        shape = RoundedCornerShape(20.dp)
                    )
                }

                // phone number
                Row(
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = "Contact", style = MinervaTypography.bodyMedium)
                    OutlinedTextField(
                        value = viewModel.phoneNumber.value,
                        onValueChange = {viewModel.phoneNumber.value=it},
                        shape = RoundedCornerShape(20.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )
                }

                // email
                Row(
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = "E-mail", style = MinervaTypography.bodyMedium)
                    OutlinedTextField(
                        value = viewModel.email.value,
                        onValueChange = {viewModel.email.value=it},
                        shape = RoundedCornerShape(20.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )
                }

                // password
                Row(
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = "Password", style = MinervaTypography.bodyMedium)
                    OutlinedTextField(
                        value = viewModel.password.value,
                        onValueChange = {viewModel.password.value=it},
                        shape = RoundedCornerShape(20.dp),
                        visualTransformation = VisualTransformation.None,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    val isValid = viewModel.validateData()
                    if(isValid){
                        viewModel.insertData()
                    }
                    else{
                        invalidData.value=true
                    }
                    navController.navigate("UserProfile")
                }) {
                    Text(text = "Register", style = MinervaTypography.bodyLarge)
                }
            }
            if (invalidData.value){
                AlertDialog(
                    onDismissRequest = {invalidData.value=false},
                    title = { Text(text = "Fill all records.",
                        style = MinervaTypography.bodyLarge) },
                    text = { Text(text = "Minerva needs to complete your user profile to get started.",
                        style = MinervaTypography.bodySmall) },
                    confirmButton = {
                        Button(onClick = {invalidData.value=false}) {
                            Text(text = "OK")
                        }
                    },

                    )
            }
        }
        }
    }
}

