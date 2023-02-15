package com.oride.delivery.presentation.fragments.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oride.delivery.R
import com.oride.delivery.data.Chat
import com.oride.delivery.databinding.LayoutChatOtherItemBinding
import com.oride.delivery.databinding.LayoutChatSelfItemBinding
import javax.inject.Inject

class ChatAdapter @Inject constructor(
) : RecyclerView.Adapter<ChatViewHolder>() {

    val differ by lazy {
        AsyncListDiffer(this, object : DiffUtil.ItemCallback<Chat>() {
            override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
                return oldItem.hashCode() == newItem.hashCode();
            }

            override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.layout_chat_self_item -> {
                ChatViewHolder.ChatSelfViewHolder(LayoutChatSelfItemBinding.inflate(inflater, parent, false))
            }

            R.layout.layout_chat_other_item -> {
                ChatViewHolder.ChatOtherViewHolder(LayoutChatOtherItemBinding.inflate(inflater, parent, false))
            }
            else -> throw IllegalArgumentException("Invalid viewType provided")
        }
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = differ.currentList[position]
        when (holder) {
            is ChatViewHolder.ChatSelfViewHolder -> {
                holder.binding.apply {
                    tvMessage.text = chat.message
                }
            }
            is ChatViewHolder.ChatOtherViewHolder -> {
                holder.binding.apply {
                    tvMessage.text = chat.message
                }
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun getItemViewType(position: Int): Int {
        return when  {
            differ.currentList[position].isSelf -> R.layout.layout_chat_self_item
            else -> R.layout.layout_chat_other_item
        }
    }
}