package com.oride.delivery.presentation

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.oride.delivery.databinding.LayoutLogoutPopupBinding

class ShowLogOutDialog(
    context: Context,
    private var message: String?,
    private var yes: (() -> Unit),
    private var close: (() -> Unit)): AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = LayoutLogoutPopupBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.tvTitle.text = message
        binding.sureBtn.setOnClickListener {
            dismiss()
            yes.invoke()
        }
        binding.noBtn.setOnClickListener {
            dismiss()
            close.invoke()
        }
    }
}