package com.example.hackathon2022.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon2022.R
import com.example.hackathon2022.databinding.ItemListHomesBinding
import com.example.hackathon2022.domain.DomainHome

class HomesAdapter : RecyclerView.Adapter<HomesAdapter.HomesViewHolder>() {

    private lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

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

        return HomesViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: HomesViewHolder, position: Int) {
        return holder.bind(items[position])
    }

    inner class HomesViewHolder(
        private val binding: ItemListHomesBinding,
        listener: OnItemClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DomainHome) {
            binding.homeName.text = item.homeName
            binding.homeEnergy.text =
                context?.getString(R.string.home_energy_use).plus(item.usagePower.toString().plus(context?.getString(
                    R.string.energy_symbol)))
            binding.iconHome.setBackgroundResource(R.drawable.list_home_icon)
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}