package com.example.after_party;

import android.content.Intent
import com.example.after_party.databinding.ItemPopularBinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView;

class PopularViewHolder(val binding: ItemPopularBinding) : RecyclerView.ViewHolder(binding.root)

class PopularListAdapter(private val itemList: List<PopularItem>) : RecyclerView.Adapter<PopularViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular, parent, false)
                val binding = ItemPopularBinding.bind(view)
                return PopularViewHolder(binding)
        }

        override fun getItemCount(): Int = itemList.size

        override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
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
