package com.example.hackathon2022.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Switch
import androidx.databinding.DataBindingUtil
import com.example.hackathon2022.R
import com.example.hackathon2022.common.base.BaseActivity
import com.example.hackathon2022.databinding.ActivityAddDeviceBinding
import com.example.hackathon2022.domain.DomainDevice
import com.google.gson.Gson

class AddDeviceActivity : BaseActivity() {

    private lateinit var binding: ActivityAddDeviceBinding
    private var sumTime = 0.0
    private var arrayBoxes: Array<CheckBox>? = null
    private var arraySwitches: Array<Switch>? = null
    private var arrayTimeInputs: Array<EditText>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_device)

        val arraySpinner = arrayOf(
            "TV",
            "PC",
            "Ładowarka",
            "Laptop",
            "Pralka",
            "Lodówka",
            "Kuchenka",
            "Mikrofalówka",
            "Światło",
            "Inne"
        )
        arrayBoxes = arrayOf(
            binding.checkBox1,
            binding.checkBox2,
            binding.checkBox3,
            binding.checkBox4,
            binding.checkBox5,
            binding.checkBox6,
            binding.checkBox7
        )
        arraySwitches = arrayOf(
            binding.switch1,
            binding.switch2,
            binding.switch3,
            binding.switch4,
            binding.switch5,
            binding.switch6,
            binding.switch7
        )
        arrayTimeInputs = arrayOf(
            binding.timeInput1,
            binding.timeInput2,
            binding.timeInput3,
            binding.timeInput4,
            binding.timeInput5,
            binding.timeInput6,
            binding.timeInput7
        )
        val spin = binding.deviceType
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, arraySpinner
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = adapter
        binding.backAddDevice.setOnClickListener {
            finish()
        }
    }

    fun disableAll(view: View) {
        val mySwitch = findViewById<Switch>(view.id)
        var indexID = 0
        if (arraySwitches?.contains(mySwitch) == true) {
            indexID = getIdInt(mySwitch)
            if (mySwitch.isChecked) {
                arrayBoxes?.get(indexID)?.isEnabled = false
                arrayBoxes?.get(indexID)?.isChecked = true
                arrayTimeInputs?.get(indexID)?.isEnabled = false
            } else {
                arrayBoxes?.get(indexID)?.isEnabled = true
                arrayBoxes?.get(indexID)?.isChecked = false
                arrayTimeInputs?.get(indexID)?.isEnabled = false
            }
        }

        if (mySwitch.isChecked) {
            sumTime += 24.0
        } else {
            sumTime -= 24.0
        }
    }

    fun addDevice(view: View) {
        val device = DomainDevice()

        device.deviceName = binding.deviceNameInput.text.toString()
        device.deviceType = binding.deviceType.selectedItem.toString()
        if (binding.powerDeviceInput.text.isNotEmpty()) {
            device.power = binding.powerDeviceInput.text.toString().toDouble()
        }
        arrayTimeInputs?.forEach {
            if(it.text.isNotEmpty() && it.isEnabled){
                sumTime += it.text.toString().toDouble()
            }
        }
        device.workTime = sumTime
        device.powerUsage = device.workTime * device.power / 1000.0

        val intent = Intent(this, DeviceActivity::class.java)
        val gson = Gson()
        val domainDevice = gson.toJson(device)
        intent.putExtra("DEVICE", domainDevice)
        startActivity(intent)

    }

    fun checkDay(view: View) {
        val myId = getIdInt(view)
        val myBox = arrayBoxes?.get(myId)
        val myInput = arrayTimeInputs?.get(myId)

        if (arrayBoxes?.contains(myBox) == true) {
            if (arrayBoxes?.get(myId)?.isEnabled == arrayTimeInputs?.get(myId)?.isEnabled) {
                arrayTimeInputs?.get(myId)?.isEnabled = !arrayBoxes?.get(myId)?.isEnabled!!
            } else {
                arrayTimeInputs?.get(myId)?.isEnabled = !arrayTimeInputs?.get(myId)?.isEnabled!!
            }
        }
    }

    private fun getId(view: View): String? {
        return if (view.id == View.NO_ID) "no-id" else view.resources.getResourceName(view.id)
    }

    private fun getIdInt(view: View): Int {
        return getId(view).toString().last().toString().toInt() - 1
    }
}