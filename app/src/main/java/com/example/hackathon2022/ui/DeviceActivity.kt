package com.example.hackathon2022.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon2022.R
import com.example.hackathon2022.databinding.ActivityDeviceBinding
import com.example.hackathon2022.domain.DomainDevice
import com.example.hackathon2022.domain.DomainHome
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DeviceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeviceBinding
    private val adapter: DeviceAdapter by lazy {
        DeviceAdapter()
    }
    private var devices = mutableListOf<DomainDevice>()
    private var homes: List<DomainHome>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_device)

        binding.back.setOnClickListener {
            finish()
        }

        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        binding.addDeviceButton.setOnClickListener {
            val intent = Intent(this, AddDeviceActivity::class.java)
            startActivity(intent)
        }

        val intentDataHome = intent.getStringExtra("HOME")
        if (intentDataHome != null) {
            val gson = Gson()
            val typeToken = object : TypeToken<List<DomainHome>>() {}.type
            val h = gson.fromJson<List<DomainHome>>(intentDataHome, typeToken)

            saveData(h)
        }

        loadData()
        val intentDataId = intent.getIntExtra("ID", -1)
        if (intentDataId != -1) {
            adapter.items = homes?.get(0)!!.devices
        }

        val intentDataDevice = intent.getStringExtra("DEVICE")
        if (intentDataDevice != null) {
            val gson = Gson()
            val domainDevice = gson.fromJson(intentDataDevice, DomainDevice::class.java)
            homes!![0].devices.add(domainDevice)
            adapter.items = homes!![0].devices

            saveData(homes!!)
        }

        adapter.setOnItemClickListener(object : DeviceAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                // TODO:
            }
        })
    }

    private fun saveData(listHomes: List<DomainHome>) {
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        val prefsEditor: SharedPreferences.Editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(listHomes)
        prefsEditor.putString("HOMES", json)
        prefsEditor.apply()
    }

    private fun loadData() {
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        val gson = Gson()
        val typeToken = object : TypeToken<List<DomainHome>>() {}.type
        val homesData = sharedPreferences.getString("HOMES", "defaultName")
        if (homesData != "defaultName") {
            val domainHomes = gson.fromJson<List<DomainHome>>(homesData, typeToken)
            if (!domainHomes.isNullOrEmpty()) {
                homes = domainHomes.toMutableList()
            }
        }
    }
}