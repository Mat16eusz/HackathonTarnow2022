package com.example.hackathon2022.ui

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon2022.R
import com.example.hackathon2022.common.base.BaseActivity
import com.example.hackathon2022.databinding.ActivityHomeListBinding
import com.example.hackathon2022.domain.DomainHome
import com.example.hackathon2022.ui.dialog.AddHomeDialog

class HomeListActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeListBinding
    private val adapter: HomesAdapter by lazy {
        HomesAdapter(
            onClickItem = ::openDevice
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_list)

        val intent = Intent(this, AddDeviceActivity::class.java)
        startActivity(intent)

        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        binding.addHome.setOnClickListener {
            AddHomeDialog.openDialog(
                fragmentManager = supportFragmentManager,
                title = applicationContext.getString(R.string.add_home)
            )
        }
        /*val intent = Intent(this, HomeListActivity::class.java)
        startActivity(intent)*/
    }

    private fun openDevice(item: DomainHome) {
        // TODO: Implementation
    }
}