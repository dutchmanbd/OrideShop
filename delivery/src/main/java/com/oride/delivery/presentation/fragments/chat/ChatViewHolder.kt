package com.oride.delivery.presentation.fragments.chat

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.oride.delivery.databinding.LayoutChatOtherItemBinding
import com.oride.delivery.databinding.LayoutChatSelfItemBinding

sealed class ChatViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    class ChatSelfViewHolder(val binding: LayoutChatSelfItemBinding) : ChatViewHolder(binding)
    class ChatOtherViewHolder(val binding: LayoutChatOtherItemBinding) : ChatViewHolder(binding)
}
