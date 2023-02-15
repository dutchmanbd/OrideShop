package com.oride.vendor.presentation.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.oride.vendor.R

abstract class BaseDialogFragment<VM: ViewModel, VB: ViewBinding>(
    @LayoutRes contentLayoutId: Int
) : DialogFragment(contentLayoutId) {

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding!!

    protected abstract val viewModel: VM

    protected open val fullscreen: Boolean = true
    protected open val transparent: Boolean = false


    companion object {
        const val TAG = "BaseDialogFragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = initializeViewBinding(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (fullscreen) setStyle(STYLE_NORMAL, R.style.FullScreenDialog)
        else setStyle(STYLE_NORMAL, R.style.Theme_AppCompat_Light_Dialog)
    }


    @CallSuper
    override fun onStart() {
        super.onStart()
        if (fullscreen) {
            dialog?.window?.setLayout(MATCH_PARENT, MATCH_PARENT)
        }

        if (transparent) {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    protected abstract fun initializeViewBinding(view: View): VB

}