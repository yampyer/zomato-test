package com.jeanpigomez.zomatotest.model

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class LocationDetails(
        val popularity: String,
        @SerializedName("nightlife_index")
        val nightlifeIndex: String,
        @SerializedName("nearby_res")
        val nearbyRes: ArrayList<Long>,
        @SerializedName("top_cuisines")
        val topCuisines: ArrayList<String>,
        @SerializedName("popularity_res")
        val popularityRes: String,
        @SerializedName("nightlife_res")
        val nightlifeRes: String,
        @SerializedName("subzone")
        val subZone: String,
        @SerializedName("subzone_id")
        val subZoneId: Long,
        val city: String,
        val location: Location,
        @SerializedName("num_restaurant")
        val numRestaurant: Long,
        @SerializedName("best_rated_restaurant")
        val bestRatedRestaurant: ArrayList<RestaurantResponse>
)
