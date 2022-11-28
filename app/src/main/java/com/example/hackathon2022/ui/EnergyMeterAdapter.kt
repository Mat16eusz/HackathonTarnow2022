package com.example.hackathon2022.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon2022.R
import com.example.hackathon2022.databinding.ItemEnergyMeterBinding
import com.example.hackathon2022.domain.DomainEnergyMeter

class EnergyMeterAdapter : RecyclerView.Adapter<EnergyMeterAdapter.EnergyMeterViewHolder>() {

    var context: Context? = null
    var items = mutableListOf<DomainEnergyMeter>()
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
    ): EnergyMeterViewHolder {
        val binding = DataBindingUtil.inflate<ItemEnergyMeterBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_energy_meter,
            parent,
            false
        )
        this.context = parent.context

        return EnergyMeterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EnergyMeterViewHolder, position: Int) {
        return holder.bind(items[position])
    }

    inner class EnergyMeterViewHolder(

        private val binding: ItemEnergyMeterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DomainEnergyMeter) {
            binding.title.text = item.energy
        }
    }
}