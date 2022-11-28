package com.example.hackathon2022.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon2022.R
import com.example.hackathon2022.common.base.BaseActivity
import com.example.hackathon2022.databinding.ActivityHomeListBinding
import com.example.hackathon2022.domain.DomainDevice
import com.example.hackathon2022.domain.DomainHome
import com.example.hackathon2022.ui.dialog.AddHomeDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomeListActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeListBinding
    private val adapter: HomesAdapter by lazy {
        HomesAdapter()
    }
    private var homes = mutableListOf<DomainHome>()
    private var devices = mutableListOf<DomainDevice>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_list)

        loadData()
        adapter.items = homes

        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        binding.addHome.setOnClickListener {
            AddHomeDialog.openDialog(
                fragmentManager = supportFragmentManager,
                title = applicationContext.getString(R.string.add_home),
                onYesClick = {
                    val home = DomainHome()
                    home.homeName = it
                    home.id = homes.size
                    homes.add(home)

                    adapter.items = homes
                    saveData(homes)
                }
            )
        }
        val intent = Intent(this, DeviceActivity::class.java)
        adapter.setOnItemClickListener(object : HomesAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val gson = Gson()
                val domainHomes = gson.toJson(homes)
                intent.putExtra("HOME", domainHomes)
                intent.putExtra("ID", homes[position].id)
                startActivity(intent)
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
            if (domainHomes.isNotEmpty()) {
                homes = domainHomes.toMutableList()
            }
        }
    }
}