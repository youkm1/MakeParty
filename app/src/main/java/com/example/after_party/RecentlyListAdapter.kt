package com.example.after_party

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.after_party.databinding.ItemRecentlyBinding

class RecentlyViewHolder(val binding: ItemRecentlyBinding) : RecyclerView.ViewHolder(binding.root)

class RecentlyListAdapter(private val itemList: List<RecentlyItem>) : RecyclerView.Adapter<RecentlyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentlyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular, parent, false)
        val binding = ItemRecentlyBinding.bind(view)
        return RecentlyViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: RecentlyViewHolder, position: Int) {
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