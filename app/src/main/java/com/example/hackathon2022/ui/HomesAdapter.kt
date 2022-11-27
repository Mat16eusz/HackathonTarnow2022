package com.example.hackathon2022.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon2022.R
import com.example.hackathon2022.databinding.ItemListHomesBinding
import com.example.hackathon2022.domain.DomainHome

class HomesAdapter(
    private val onClickItem: (item: DomainHome) -> Unit,
) : RecyclerView.Adapter<HomesAdapter.HomesViewHolder>() {

    var context: Context? = null
    var items = mutableListOf<DomainHome>()
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
    ): HomesViewHolder {
        val binding = DataBindingUtil.inflate<ItemListHomesBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_list_homes,
            parent,
            false
        )
        this.context = parent.context

        return HomesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomesViewHolder, position: Int) {
        return holder.bind(items[position])
    }

    inner class HomesViewHolder(private val binding: ItemListHomesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DomainHome) {
            binding.homeName.text = item.homeName
            binding.homeEnergy.text = item.usagePower.toString() + context?.getString(R.string.energy_symbol)
            binding.iconHome.setImageDrawable(R.drawable.list_home_icon.toDrawable())
        }
    }
}