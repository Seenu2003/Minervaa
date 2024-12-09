package com.seenu.minerva2.chatbot


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GoogleGenerativeAIException
import com.google.ai.client.generativeai.type.content
import com.seenu.minerva2.BuildApiKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatBotViewModel @Inject constructor(): ViewModel() {
    private val generativeModel: GenerativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = BuildApiKey.apikey2
    )

    val messageList by lazy {
        mutableStateListOf<MessageModel>()
    }

    fun sendMessage(query: String) {
        // Add the user message to the message list
        messageList.add(MessageModel(query, "user"))

        // Launch the coroutine in the viewModelScope
        viewModelScope.launch {
            try {
                // Start a new chat session
                val chat = generativeModel.startChat(
                    history = messageList.map {
                        content(it.role){text(it.query)}
                    }.toList()
                )

                // Send the user query and receive a response
                val response = chat.sendMessage(query)

                // Add the bot's response to the message list
                messageList.add(MessageModel(response.text.toString(), "model"))
            } catch (e: GoogleGenerativeAIException) {
                // Handle API-specific errors
                handleError(e)
            } catch (e: Exception) {
                // Handle any other unexpected exceptions
                handleError(e)
            }
        }
    }

    private fun handleError(e: Exception) {
        val errorMessage = "An error occurred: ${e.message}"
        messageList.add(MessageModel("Error Message : ${e.message}","model"))
        }
    }