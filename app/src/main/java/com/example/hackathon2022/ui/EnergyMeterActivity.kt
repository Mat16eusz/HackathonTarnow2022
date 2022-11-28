package com.example.hackathon2022.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon2022.R
import com.example.hackathon2022.common.base.BaseActivity
import com.example.hackathon2022.databinding.ActivityEnergyMeterBinding
import com.example.hackathon2022.domain.DomainEnergyMeter
import com.example.hackathon2022.ui.dialog.AddHomeDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions

class EnergyMeterActivity : BaseActivity() {

    private lateinit var binding: ActivityEnergyMeterBinding
    private val adapter: EnergyMeterAdapter by lazy {
        EnergyMeterAdapter()
    }
    private var meters = mutableListOf<DomainEnergyMeter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_energy_meter)

        binding.homes.setOnClickListener {
            val intent = Intent(this, HomeListActivity::class.java)
            startActivity(intent)
        }

        loadData()
        adapter.items = meters

        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        binding.addEnergyMeter.setOnClickListener {
            AddHomeDialog.openDialog(
                fragmentManager = supportFragmentManager,
                title = applicationContext.getString(R.string.add_energy_meter),
                onYesClick = {
                    val energy = DomainEnergyMeter()
                    energy.energy = it
                    meters.add(energy)

                    adapter.items = meters
                    saveData(meters)
                }
            )
        }

        binding.scanEnergyMeter.setOnClickListener {
            barcodeLauncher.launch(ScanOptions())
        }
    }

    private fun saveData(listEnergyMeter: List<DomainEnergyMeter>) {
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        val prefsEditor: SharedPreferences.Editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(listEnergyMeter)
        prefsEditor.putString("METERS", json)
        prefsEditor.apply()
    }

    private fun loadData() {
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        val gson = Gson()
        val typeToken = object : TypeToken<List<DomainEnergyMeter>>() {}.type
        val energyMeterData = sharedPreferences.getString("METERS", "defaultName")
        if (energyMeterData != "defaultName") {
            val domainEnergyMeter =
                gson.fromJson<List<DomainEnergyMeter>>(energyMeterData, typeToken)
            if (domainEnergyMeter.isNotEmpty()) {
                meters = domainEnergyMeter.toMutableList()
            }
        }
    }

    private val barcodeLauncher = registerForActivityResult(ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this,
                "Scanned: " + result.contents,
                Toast.LENGTH_LONG).show()
        }
    }
}