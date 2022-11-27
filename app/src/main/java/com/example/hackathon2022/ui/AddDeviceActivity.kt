package com.example.hackathon2022.ui

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Switch
import androidx.databinding.DataBindingUtil
import com.example.hackathon2022.R
import com.example.hackathon2022.common.base.BaseActivity
import com.example.hackathon2022.databinding.ActivityAddDeviceBinding


class AddDeviceActivity : BaseActivity() {

    private lateinit var binding: ActivityAddDeviceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_device)

        val arraySpinner = arrayOf(
            "1", "2", "3", "4", "5", "6", "7"
        )
        var spin = binding.devices
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, arraySpinner
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = adapter

    }

    fun disableAll(view: View) {
        var mySwitch = view.id.toInt()
        var lol = binding.checkBox1.id
        when (mySwitch){
            binding.switch1.id -> {
                binding.checkBox1.isEnabled = !binding.checkBox1.isEnabled
                binding.editTextNumber1.isEnabled = !binding.editTextNumber1.isEnabled
            }
            binding.switch2.id -> {
                binding.checkBox2.isEnabled = !binding.checkBox2.isEnabled
                binding.editTextNumber2.isEnabled = !binding.editTextNumber2.isEnabled
            }
            binding.switch3.id -> {
                binding.checkBox3.isEnabled = !binding.checkBox3.isEnabled
                binding.editTextNumber3.isEnabled = !binding.editTextNumber3.isEnabled
            }
            binding.switch4.id -> {
                binding.checkBox4.isEnabled = !binding.checkBox4.isEnabled
                binding.editTextNumber4.isEnabled = !binding.editTextNumber4.isEnabled
            }
            binding.switch5.id -> {
                binding.checkBox5.isEnabled = !binding.checkBox5.isEnabled
                binding.editTextNumber5.isEnabled = !binding.editTextNumber5.isEnabled
            }
            binding.switch6.id -> {
                binding.checkBox6.isEnabled = !binding.checkBox6.isEnabled
                binding.editTextNumber6.isEnabled = !binding.editTextNumber6.isEnabled
            }
            binding.switch7.id -> {
                binding.checkBox7.isEnabled = !binding.checkBox7.isEnabled
                binding.editTextNumber7.isEnabled = !binding.editTextNumber7.isEnabled
            }
        }
    }

    fun clean(view: View) {
        var myEditText = view
        //myEditText.setText("lol")
    }


}