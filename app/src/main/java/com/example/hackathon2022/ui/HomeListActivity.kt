package com.example.hackathon2022.ui

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.hackathon2022.R
import com.example.hackathon2022.common.base.BaseActivity
import com.example.hackathon2022.databinding.ActivityHomeListBinding

class HomeListActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_list)

        /*val intent = Intent(this, HomeListActivity::class.java)
        startActivity(intent)*/
    }
}