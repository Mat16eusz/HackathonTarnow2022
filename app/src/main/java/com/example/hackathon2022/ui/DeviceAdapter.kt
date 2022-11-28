package com.example.hackathon2022.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon2022.R
import com.example.hackathon2022.databinding.ItemDeviceBinding
import com.example.hackathon2022.domain.DomainDevice

class DeviceAdapter : RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {

    var context: Context? = null
    var items = mutableListOf<DomainDevice>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DeviceViewHolder {
        val binding = DataBindingUtil.inflate<ItemDeviceBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_device,
            parent,
            false
        )
        this.context = parent.context

        return DeviceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        return holder.bind(items[position])
    }

    inner class DeviceViewHolder(
        private val binding: ItemDeviceBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DomainDevice) {
            binding.deviceName.text = item.deviceName
            when(item.deviceType){
                "PC" -> binding.iconDevice.setBackgroundResource(R.drawable.pc_icon)
                "TV" -> binding.iconDevice.setBackgroundResource(R.drawable.tv_icon)
                "Pralka" -> binding.iconDevice.setBackgroundResource(R.drawable.washing_machine_icon)
                "Światło" -> binding.iconDevice.setBackgroundResource(R.drawable.light_icon)
                else -> binding.iconDevice.setBackgroundResource(R.drawable.tv_icon)
            }
            val usage: String =
                        context?.getString(R.string.time)   + item.workTime.toString()  +"h "+
                        context?.getString(R.string.power)  + item.power.toString()     + "W"
            binding.usagePower.text = usage
        }
    }
}