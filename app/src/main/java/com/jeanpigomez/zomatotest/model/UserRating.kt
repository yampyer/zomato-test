package com.jeanpigomez.zomatotest.model

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class UserRating(
        @SerializedName("aggregate_rating")
        val aggregateRating: String,
        @SerializedName("rating_text")
        val ratingText: String,
        @SerializedName("rating_color")
        val ratingColor: String,
        val votes: Long
)
