package com.example.hackathon2022.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon2022.R
import com.example.hackathon2022.databinding.ActivityDeviceBinding

class DeviceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeviceBinding
    private val adapter: DeviceAdapter by lazy {
        DeviceAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_device)

        binding.back.setOnClickListener {
            finish()
        }

        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        binding.addDeviceButton.setOnClickListener{
            val intent = Intent(this, AddDeviceActivity::class.java)
            startActivity(intent)
        }
    }
}