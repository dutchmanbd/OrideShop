package com.oride.vendor.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.oride.vendor.R

abstract class BaseFragment<VM: BaseViewModel, VB: ViewBinding>(
    @LayoutRes contentLayoutId : Int
) : Fragment(contentLayoutId) {

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding!!

    protected abstract val viewModel: VM

    companion object {
        const val TAG = "BaseFragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = initializeViewBinding(view)

//        view.findViewById<ShapeableImageView>(R.id.ivBack)?.setOnClickListener {
//            requireActivity().onBackPressed()
//        }
//
//        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
//            isLoading ?: return@observe
//            if (isLoading) {
//                view.findViewById<LinearProgressIndicator>(R.id.piLoading)?.show()
//            } else {
//                view.findViewById<LinearProgressIndicator>(R.id.piLoading)?.hide()
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun initializeViewBinding(view: View): VB


}