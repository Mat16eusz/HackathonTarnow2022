package com.example.hackathon2022.ui

import android.os.Bundle
import android.util.Log
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
            "TV", "PC", "Ładowarka", "Laptop", "Pralka", "Lodówka", "Kuchenka", "Mikrofalówka", "Światło", "Inne"
        )
        arrayBoxes = arrayOf(
            binding.checkBox1, binding.checkBox2, binding.checkBox3, binding.checkBox4, binding.checkBox5, binding.checkBox6, binding.checkBox7
        )
        arraySwitches = arrayOf(
            binding.switch1, binding.switch2, binding.switch3, binding.switch4, binding.switch5, binding.switch6, binding.switch7
        )
        arrayTimeInputs = arrayOf(
            binding.timeInput1, binding.timeInput2, binding.timeInput3, binding.timeInput4, binding.timeInput5, binding.timeInput6, binding.timeInput7
        )
        var spin = binding.deviceType
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, arraySpinner
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = adapter

    }

    fun disableAll(view: View) {
        var mySwitch = findViewById<Switch>(view.id)
        var indexID = 0
        if(arraySwitches?.contains(mySwitch) == true){
            indexID = getIdInt(mySwitch)
            if(mySwitch.isChecked){
                arrayBoxes?.get(indexID)?.isEnabled = true
                arrayBoxes?.get(indexID)?.isChecked = true
                arrayTimeInputs?.get(indexID)?.isEnabled = false
            }else{
                arrayBoxes?.get(indexID)?.isEnabled = false
                arrayBoxes?.get(indexID)?.isChecked = false
                arrayTimeInputs?.get(indexID)?.isEnabled = false
            }
            Log.e("test2", indexID.toString())
        }

        if(mySwitch.isChecked){
            sumTime += 24.0
        }else{
            sumTime -= 24.0
        }

        Log.e("test2", sumTime.toString())
    }

    fun addDevice(view: View) {
        var device = DomainDevice()

        device.deviceName = binding.deviceNameInput.text.toString()
        device.deviceType = binding.deviceType.selectedItem.toString()
        device.normalPower = binding.powerSleepModeInput.text.toString().toDouble()
        device.sleepPower = binding.powerSleepModeInput.text.toString().toDouble()
        device.workTime = sumTime

    }

    fun checkDay(view: View){
        var myId = getIdInt(view)
        var myBox = arrayBoxes?.get(myId)
        var myEdit = arrayTimeInputs?.get(myId)
        getId(view)?.let { Log.e("test", it.last().toString()) }

//        if(arrayBoxes?.contains(myBox) == true){
//            myEdit!!.isEnabled = true
////            if (myEdit != null) {
////                if(myBox!!.isChecked){
////                    if (myEdit != null) {
////                        sumTime += myEdit.text.toString().toDouble()
////                    }
////                }else{
////                    if (myEdit != null) {
////                        sumTime -= myEdit.text.toString().toDouble()
////                    }
////                }
////            }
//        }else{
//            myEdit!!.isEnabled = false
//        }

        if(arrayBoxes?.contains(myBox) == true){
            if(arrayBoxes?.get(myId)?.isEnabled == arrayTimeInputs?.get(myId)?.isEnabled){
                arrayTimeInputs?.get(myId)?.isEnabled = !arrayBoxes?.get(myId)?.isEnabled!!
            }else{
                arrayTimeInputs?.get(myId)?.isEnabled = !arrayTimeInputs?.get(myId)?.isEnabled!!
            }
            Log.e("test3", myId.toString())
        }

        Log.e("test2", sumTime.toString())
    }

    fun getTimeFromInput(view: View){
        var myId = getIdInt(view)
        var myInput = arrayTimeInputs?.get(myId)

        Log.e("test", myId.toString())
        if (myInput != null) {
            Log.e("test", myInput.text.toString())
        }
        if (myInput != null) {
            if (myInput.isEnabled) {
                sumTime += myInput.text.toString().toDouble()
            }
        }
        Log.e("test",sumTime.toString())
    }

    fun getId(view: View): String? {
        return if (view.id == View.NO_ID) "no-id" else view.resources.getResourceName(view.id)
    }
    fun getIdInt(view: View): Int {
        return getId(view).toString().last().toString().toInt()-1
    }
}