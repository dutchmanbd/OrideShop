package com.oride.vendor.presentation.fragments.dish.add

import android.view.View
import androidx.fragment.app.viewModels
import com.oride.vendor.R
import com.oride.vendor.databinding.FragmentAddDishesBinding
import com.oride.vendor.presentation.base.BaseFragment
import com.oride.vendor.presentation.fragments.dish.DishViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddDishesFragment : BaseFragment<DishViewModel, FragmentAddDishesBinding>(
    R.layout.fragment_add_dishes
) {
    override val viewModel by viewModels<DishViewModel>()

    override fun initializeViewBinding(view: View) = FragmentAddDishesBinding.bind(view)


}