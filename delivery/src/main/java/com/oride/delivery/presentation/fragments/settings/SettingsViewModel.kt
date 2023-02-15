package com.oride.delivery.presentation.fragments.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oride.delivery.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
) : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}