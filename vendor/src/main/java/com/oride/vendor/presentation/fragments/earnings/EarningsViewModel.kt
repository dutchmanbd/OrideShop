package com.oride.vendor.presentation.fragments.earnings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oride.vendor.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EarningsViewModel @Inject constructor(
) : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}