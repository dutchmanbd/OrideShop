package com.oride.delivery.presentation.fragments.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.oride.delivery.presentation.base.BaseFragment
import com.oride.delivery.R
import com.oride.delivery.databinding.FragmentChatBinding
import com.oride.utilities.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChatFragment : BaseFragment<ChatViewModel, FragmentChatBinding>(R.layout.fragment_chat) {

    @Inject
    lateinit var chatAdapter: ChatAdapter

    override val viewModel by viewModels<ChatViewModel>()

    override fun initializeViewBinding(view: View) = FragmentChatBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setRecyclerView()
        fetchMessages()
    }

    private fun setupListeners() {
        binding.sendBtn.setOnClickListener {

        }
    }

    private fun setRecyclerView() {
        binding.rvChat.adapter = chatAdapter
    }

    private fun fetchMessages() {
        viewModel.fetchMessages().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Failure -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    chatAdapter.differ.submitList(resource.data)
                }
            }

        }
    }
}