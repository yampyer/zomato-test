package com.jeanpigomez.zomatotest.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jeanpigomez.zomatotest.databinding.ItemRestaurantBinding
import com.jeanpigomez.zomatotest.R
import com.jeanpigomez.zomatotest.model.Restaurant
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.net.Uri

class RestaurantListAdapter : RecyclerView.Adapter<RestaurantListAdapter.ViewHolder>() {
    private lateinit var restaurantList: List<Restaurant>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantListAdapter.ViewHolder {
        val binding: ItemRestaurantBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_restaurant, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantListAdapter.ViewHolder, position: Int) {
        holder.bind(restaurantList[position])
        holder.itemView.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(restaurantList[position].url))
            startActivity(holder.itemView.context, browserIntent, null)
        }
    }

    override fun getItemCount(): Int {
        return if (::restaurantList.isInitialized) restaurantList.size else 0
    }

    fun updateRestaurantList(restaurantList: List<Restaurant>) {
        this.restaurantList = restaurantList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = RestaurantViewModel()

        fun bind(restaurant: Restaurant) {
            viewModel.bind(restaurant)
            binding.viewModel = viewModel
        }
    }
}
