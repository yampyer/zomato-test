package com.jeanpigomez.zomatotest.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Location (
        @field:PrimaryKey
        @SerializedName("entity_id")
        val entityId: String,
        @SerializedName("entity_type")
        val entityType: String,
        val title: String,
        val latitude: Double,
        val longitude: Double,
        @SerializedName("city_id")
        val cityId: String,
        @SerializedName("city_name")
        val cityName: String,
        @SerializedName("country_id")
        val countryId: String,
        @SerializedName("country_name")
        val countryName: String
)
