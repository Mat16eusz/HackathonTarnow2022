package com.example.hackathon2022.common.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

abstract class BaseFragment : Fragment() {

    protected open val viewModel: BaseViewModel? = null
    val baseActivity by lazy { activity as BaseActivity }

    fun <T : ViewModel> viewModelOf(viewModelClass: KClass<T>) =
        ViewModelProvider(this)[viewModelClass.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserve()
    }

    private fun initObserve() {
        viewModel?.baseAction?.observe(this, Observer {
            when (it) {
                is BaseViewModel.BaseAction.ShowError -> showInfoDialog(message = it.message)
                else -> {
                    /*Do nothing*/
                }
            }
        })
    }

    fun getSupportFragmentManager() = baseActivity.supportFragmentManager

    fun showInfoDialog(message: String?) {
        baseActivity.showInfoDialog(message)
    }
}