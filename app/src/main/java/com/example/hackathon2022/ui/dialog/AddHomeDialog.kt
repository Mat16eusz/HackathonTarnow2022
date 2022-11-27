package com.example.hackathon2022.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.example.hackathon2022.R
import com.example.hackathon2022.common.base.BaseDialogFragment
import com.example.hackathon2022.databinding.DialogAddHomeBinding
import com.example.hackathon2022.domain.DomainHome
import com.example.hackathon2022.utils.ScreenUtils

class AddHomeDialog : BaseDialogFragment() {

    companion object {
        fun openDialog(
            fragmentManager: FragmentManager,
            title: String = "",
            homeName: String = "",
            onNoClick: (() -> Unit)? = null,
            onYesClick: ((homeName: String) -> Unit)? = null,
        ): AddHomeDialog {
            val dialog = AddHomeDialog()
            dialog.show(
                fragmentManager,
                BaseDialogFragment.ADD_HOME_DIALOG
            )

            return dialog.apply {
                this.title = title
                this.homeName = homeName
                this.onNoClick = onNoClick
                this.onYesClick = onYesClick
            }
        }
    }

    private lateinit var binding: DialogAddHomeBinding
    private lateinit var title: String
    private var homeName: String = ""
    private var onNoClick: (() -> Unit)? = null
    private var onYesClick: ((homeName: String) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_home, container, false)
        binding.title.text = title

        binding.buttonNo.setOnClickListener {
            onNoClick?.let { onNoClick -> onNoClick() }
            dismiss()
        }

        binding.buttonYes.setOnClickListener {
            onYesClick?.let { onYesClick -> onYesClick(binding.homeName.text.toString()) }
            dismiss()
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            (ScreenUtils.getScreenWidth(requireActivity()) * 0.9).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun addDevice(item: DomainHome) {
        // TODO: Implementation
    }
}