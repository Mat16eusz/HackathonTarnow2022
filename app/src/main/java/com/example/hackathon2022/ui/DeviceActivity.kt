package com.example.hackathon2022.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.hackathon2022.R
import com.example.hackathon2022.databinding.ActivityAddDeviceBinding

class DeviceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddDeviceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_device)


    }
}