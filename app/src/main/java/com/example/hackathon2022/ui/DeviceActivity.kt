package com.example.hackathon2022.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon2022.R
import com.example.hackathon2022.databinding.ActivityDeviceBinding
import com.example.hackathon2022.domain.DomainDevice
import com.example.hackathon2022.domain.DomainHome
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.math.roundToInt

class DeviceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeviceBinding
    private val adapter: DeviceAdapter by lazy {
        DeviceAdapter()
    }
    private var id: Int? = null
    private var sum: Double = 0.0
    private var homes: MutableList<DomainHome> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_device)

        binding.back.setOnClickListener {
            saveData(homes)
            val intent = Intent(this, HomeListActivity::class.java)
            startActivity(intent)
        }

        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        binding.addDeviceButton.setOnClickListener {
            val intent = Intent(this, AddDeviceActivity::class.java)
            startActivity(intent)
        }

        loadData()
        val sharedPref = getSharedPreferences("PREFERENCE_NAME", MODE_PRIVATE)
        id = sharedPref.getInt("ID", -1)
        if (homes.size == id) {
            val intentDataHome = intent.getStringExtra("HOME")
            if (intentDataHome != null) {
                val gson = Gson()
                val domainHome = gson.fromJson(intentDataHome, DomainHome::class.java)
                homes.add(domainHome)
                saveData(homes)
            }
        }

        adapter.items = homes[id!!].devices

        val intentDataDevice = intent.getStringExtra("DEVICE")
        if (intentDataDevice != null) {
            val gson = Gson()
            val domainDevice = gson.fromJson(intentDataDevice, DomainDevice::class.java)
            homes[id!!].devices.add(domainDevice)
            adapter.items = homes[id!!].devices

            saveData(homes)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                sum -= homes[id!!].devices[viewHolder.adapterPosition].powerUsage

                adapter.items.removeAt(viewHolder.adapterPosition)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)

                homes[id!!].devices = adapter.items


                binding.energyConsumption.text = sum.toString().plus(applicationContext.getString(R.string.energy_symbol))

                saveData(homes)
            }

        }).attachToRecyclerView(binding.recyclerView)

        sumPowerUsage()
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

    private fun sumPowerUsage(){
        if (homes.isNotEmpty()) {
            homes.forEach {
                it.devices.forEach {
                    sum += it.powerUsage
                }
                it.usagePower = sum
                binding.energyConsumption.text = sum.toString().plus(applicationContext.getString(R.string.energy_symbol))
            }
        }
    }

    fun year(view: View){
        var temp = binding.energyConsumption.text.toString().toDouble()
        temp = (temp * 4.0 * 12.0).roundToInt().toDouble()
        binding.energyConsumption.text = temp.toString()
    }
    fun moth(view : View){
        var temp = binding.energyConsumption.text.toString().toDouble()
        temp = (temp * 4.0).roundToInt().toDouble()
        binding.energyConsumption.text = temp.toString()
    }
    fun week(view : View){
//        var temp = binding.energyConsumption.text.toString().toDouble()
//        temp = temp / 7.0
//        binding.energyConsumption.text = temp.toString()
    }
}