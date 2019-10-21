package com.jeanpigomez.zomatotest.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Restaurant(
        @field:PrimaryKey
        val id: String,
        val name: String,
        val url: String,
        val location: Location,
        val cuisines: String,
        val timings: String,
        @SerializedName("average_cost_for_two")
        val averageCostForTwo: Long,
        @SerializedName("price_range")
        val priceRange: Long,
        val currency: String,
        val thumb: String,
        @SerializedName("user_rating")
        val userRating: UserRating,
        @SerializedName("all_reviews_count")
        val reviewsCount: Long,
        @SerializedName("menu_url")
        val menuUrl: String,
        @SerializedName("featured_image")
        val featuredImage: String,
        @SerializedName("phone_numbers")
        val phoneNumbers: String
)
