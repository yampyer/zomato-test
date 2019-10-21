package com.jeanpigomez.zomatotest.ui

import android.arch.lifecycle.MutableLiveData
import com.jeanpigomez.zomatotest.base.BaseViewModel
import com.jeanpigomez.zomatotest.model.Restaurant

class RestaurantViewModel : BaseViewModel() {
    private val restaurantName = MutableLiveData<String>()
    private val restaurantRating = MutableLiveData<String>()
    private val restaurantTimings = MutableLiveData<String>()
    private val restaurantImage = MutableLiveData<String>()
    private val restaurantUrl = MutableLiveData<String>()
    private val menuUrl = MutableLiveData<String>()
    private val restaurantPhone = MutableLiveData<String>()
    private val restaurantReviews = MutableLiveData<String>()

    fun bind(restaurant: Restaurant) {
        restaurantName.value = restaurant.name
        restaurantRating.value = restaurant.userRating.aggregateRating
        restaurantTimings.value = restaurant.timings.replace(",", "\n")
        restaurantImage.value = restaurant.featuredImage
        restaurantUrl.value = restaurant.url
        menuUrl.value = restaurant.menuUrl
        restaurantPhone.value = restaurant.phoneNumbers.replace(",", "\n")
        restaurantReviews.value = restaurant.reviewsCount.toString()
    }

    fun getRestaurantName(): MutableLiveData<String> {
        return restaurantName
    }

    fun getRestaurantRating(): MutableLiveData<String> {
        return restaurantRating
    }

    fun getRestaurantTimings(): MutableLiveData<String> {
        return restaurantTimings
    }

    fun getRestaurantImage(): MutableLiveData<String> {
        return restaurantImage
    }

    fun getRestaurantUrl(): MutableLiveData<String> {
        return restaurantUrl
    }

    fun getMenuUrl(): MutableLiveData<String> {
        return menuUrl
    }

    fun getRestaurantPhone(): MutableLiveData<String> {
        return restaurantPhone
    }

    fun getRestaurantReviews(): MutableLiveData<String> {
        return restaurantReviews
    }
}
