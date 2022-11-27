package com.example.hackathon2022.common.base

import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

abstract class BaseDialogFragment : DialogFragment() {

    companion object {
        const val BASE_DIALOG = "BASE_DIALOG"
    }

    protected open val viewModel: BaseViewModel? = null

    fun <T : ViewModel> viewModelOf(viewModelClass: KClass<T>) =
        ViewModelProvider(this)[viewModelClass.java]

    fun showInfo(message: String?) {
        message?.let {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }
    }
}