package com.example.hackathon2022.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val baseAction = MutableLiveData<BaseAction>()

    sealed class BaseAction {
        data class ShowError(val message: String?) : BaseAction()
        data class StartActivity(
            val cls: Class<*>?,
            val flags: Int? = null,
        ) : BaseAction()

        object Loading : BaseAction()
    }
}