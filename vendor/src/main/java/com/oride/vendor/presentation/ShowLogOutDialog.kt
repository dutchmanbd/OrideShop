package com.oride.vendor.presentation

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.oride.vendor.databinding.LayoutLogoutBinding

class ShowLogOutDialog(
    context: Context,
    private var message: String?,
    private var yes: (() -> Unit),
    private var close: (() -> Unit)): AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = LayoutLogoutBinding.inflate(layoutInflater)
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