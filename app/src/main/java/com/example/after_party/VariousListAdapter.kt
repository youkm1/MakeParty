package com.example.after_party

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.after_party.databinding.ItemVariousBinding

class VariousViewHolder(val binding: ItemVariousBinding) : RecyclerView.ViewHolder(binding.root)

class VariousListAdapter(private val itemList: List<VariousItem>) : RecyclerView.Adapter<VariousViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VariousViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular, parent, false)
        val binding = ItemVariousBinding.bind(view)
        return VariousViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: VariousViewHolder, position: Int) {
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