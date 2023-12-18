package com.example.after_party

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.after_party.databinding.ItemDeliciousBinding

class DeliciousViewHolder(val binding: ItemDeliciousBinding) : RecyclerView.ViewHolder(binding.root)

class DeliciousListAdapter(private val itemList: List<DeliciousItem>) : RecyclerView.Adapter<DeliciousViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliciousViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular, parent, false)
        val binding = ItemDeliciousBinding.bind(view)
        return DeliciousViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: DeliciousViewHolder, position: Int) {
        val binding = holder.binding
        binding.itemImage.setImageResource(itemList[position].image)
        binding.itemName.text = itemList[position].restaurantName
        binding.itemDate.text = itemList[position].about
        binding.itemNum.text = itemList[position].numPeople

        binding.button.setOnClickListener {
            val intent = Intent(binding.root.context, DetailReservation::class.java)
            binding.root.context.startActivity(intent)
        }
    }
}