package com.jeanpigomez.zomatotest.model

import com.google.gson.annotations.SerializedName

data class LocationResponse(
        @SerializedName("location_suggestions")
        val locationSuggestions: ArrayList<Location>
)
