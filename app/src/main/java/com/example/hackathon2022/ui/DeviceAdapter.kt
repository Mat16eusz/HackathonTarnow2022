package com.example.hackathon2022.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon2022.R
import com.example.hackathon2022.databinding.ItemDeviceBinding
import com.example.hackathon2022.domain.DomainDevice

class DeviceAdapter(
    private val onClickItem: (item: DomainDevice) -> Unit,
) : RecyclerView.Adapter<DeviceAdapter.ProductIndexViewHolder>() {

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
    ): ProductIndexViewHolder {
        val binding = DataBindingUtil.inflate<ItemDeviceBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_device,
            parent,
            false
        )
        this.context = parent.context

        return ProductIndexViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductIndexViewHolder, position: Int) {
        return holder.bind(items[position])
    }

    inner class ProductIndexViewHolder(private val binding: ItemDeviceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DomainDevice) {
            binding.deviceName.text = item.deviceName

            val usage: String =
                context?.getString(R.string.sleep) + item.sleepConsumption.toString() +
                        context?.getString(R.string.power) + item.normalConsumption.toString()
            binding.usagePower.text = usage
        }
    }
}