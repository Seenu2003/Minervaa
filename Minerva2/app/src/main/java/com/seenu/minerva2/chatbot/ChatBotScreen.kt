package com.seenu.minerva2.chatbot

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.seenu.minerva2.R
import com.seenu.minerva2.ui.theme.MinervaTypography
import com.yazantarifi.compose.library.MarkdownConfig
import com.yazantarifi.compose.library.MarkdownViewComposable
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chatbot(navController: NavController, chatBotViewModel: ChatBotViewModel){
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
        ){
            paddingValues ->  Column(
                modifier = Modifier.padding(paddingValues)
            ){
                MessageList(modifier = Modifier.weight(1f), messageList = chatBotViewModel.messageList)
                MessageInput{
                    chatBotViewModel.sendMessage(it)
                }
        }
        }
    }
}

@Composable
fun MessageInput(onMessageSend:(String)->Unit){
    var message by remember { mutableStateOf("") }
    Row(
        modifier = Modifier.padding(10.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ){
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = message,
            onValueChange = {message=it},
            shape = RoundedCornerShape(20.dp),
            placeholder = { Text(text = "Write your query here....", style = MinervaTypography.bodySmall) }
        )
        IconButton(onClick = {
            if(message.isNotEmpty()){
                onMessageSend(message)
                message=""
            }
        }) {
            Icon(imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Send")
        }
    }
}

@Composable
fun MessageList(modifier: Modifier,messageList : List<MessageModel>){
    if(messageList.isEmpty()){
        Column(
            modifier=Modifier.fillMaxWidth().fillMaxHeight(0.85f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(text = "Minerva Bot", style = MinervaTypography.headlineLarge)
            Text(text = "Ask Me anything", style = MinervaTypography.displayMedium)
            Icon(painter = painterResource(R.drawable.group), contentDescription = "No Message", modifier = Modifier
                .size(450.dp))
        }
    }
    else{
        LazyColumn(
            modifier = modifier
        ){
            items(messageList){
                MessageRow(messageModel = it)
            }
        }
    }
}

@Composable
fun MessageRow(messageModel: MessageModel){
    val isModel= messageModel.role=="model"
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier.fillMaxWidth().padding(5.dp)
        ){
            Box(
              modifier = Modifier.align(
                  if(isModel) Alignment.BottomStart else Alignment.BottomEnd
              ).padding(
                  start = if(isModel) 8.dp else 60.dp,
                  end= if(isModel) 60.dp else 8.dp,
                  top=8.dp,
                  bottom = 8.dp
              ).clip(RoundedCornerShape(51f))
                  .background(
                  color = if(isSystemInDarkTheme()) Color(0xFF605F54) else Color(0xFF49483E)
              ).padding(16.dp)
            ){
                // Text(text = messageModel.query, color = Color.White)
                MarkdownViewComposable(
                    modifier = Modifier.padding(3.dp),
                    content = messageModel.query,
                    config = MarkdownConfig(
                        isLinksClickable = true,
                        isScrollEnabled = false,
                        colors = HashMap<String,Color>().apply {
                            this[MarkdownConfig.TEXT_COLOR]=Color.White
                        }
                    )
                ){
                    _,_->
                }
            }
        }
    }
}