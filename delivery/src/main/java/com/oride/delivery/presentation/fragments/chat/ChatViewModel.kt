package com.oride.delivery.presentation.fragments.chat

import androidx.lifecycle.liveData
import com.oride.delivery.data.Chat
import com.oride.delivery.presentation.base.BaseViewModel
import com.oride.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
) : BaseViewModel() {

    fun fetchMessages() = liveData {
        emit(Resource.Loading())
        emit(
            Resource.Success(
                listOf(
                    Chat(
                        name = "Peter Quill",
                        message = "Hi!",
                        isSelf = false
                    ),
                    Chat(
                        name = "Peter",
                        message = "Hello!",
                        isSelf = true
                    ),
                    Chat(
                        name = "Peter Quill",
                        message = "I'm on the way. Just wait a min",
                        isSelf = false
                    ),
                    Chat(
                        name = "Peter",
                        message = "No problem, I'm waiting",
                        isSelf = true
                    ),
                    Chat(
                        name = "Peter Quill",
                        message = "Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before the final copy is available.",
                        isSelf = false
                    ),
                    Chat(
                        name = "Peter Quill",
                        message = "Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before the final copy is available.",
                        isSelf = true
                    ),
                    Chat(
                        name = "Peter Quill",
                        message = "Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before the final copy is available.",
                        isSelf = true
                    ),
                    Chat(
                        name = "Peter Quill",
                        message = "Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before the final copy is available.",
                        isSelf = true
                    ),
                    Chat(
                        name = "Peter Quill",
                        message = "Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before the final copy is available.",
                        isSelf = true
                    ),
                    Chat(
                        name = "Peter Quill",
                        message = "Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before the final copy is available.",
                        isSelf = true
                    ),
                    Chat(
                        name = "Peter Quill",
                        message = "Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before the final copy is available.",
                        isSelf = true
                    )
                )
            )
        )
    }
}