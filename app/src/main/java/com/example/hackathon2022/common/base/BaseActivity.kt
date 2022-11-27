package com.example.hackathon2022.common.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.hackathon2022.R
import com.example.hackathon2022.ui.dialog.BaseDialog
import com.example.hackathon2022.utils.or

abstract class BaseActivity : AppCompatActivity() {

    protected open val viewModel: BaseViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserve()
    }

    private fun initObserve() {
        viewModel?.baseAction?.observe(this, Observer {
            when (it) {
                is BaseViewModel.BaseAction.ShowError -> showInfoDialog(message = it.message)
                is BaseViewModel.BaseAction.StartActivity -> startActivity(
                    cls = it.cls,
                    flags = it.flags
                )
                is BaseViewModel.BaseAction.Loading -> showHideProgressbar(true)
            }
        })
    }

    fun startActivity(cls: Class<*>?, flags: Int? = null) {
        val intent = Intent(this, cls)
        flags?.let {
            intent.addFlags(it)
        }
        startActivity(intent)
    }

    fun showInfoDialog(message: String?) {
        showHideProgressbar(false)

        BaseDialog.openDialog(
            fragmentManager = supportFragmentManager,
            title = message.or(getString(R.string.unknown_error))
        )
    }

    protected open fun showHideProgressbar(isShow: Boolean) {
        /*Do nothing*/
    }
}