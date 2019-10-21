package com.jeanpigomez.zomatotest.ui

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.jeanpigomez.zomatotest.R
import com.jeanpigomez.zomatotest.base.BaseViewModel
import com.jeanpigomez.zomatotest.model.LocationDetails
import com.jeanpigomez.zomatotest.model.Restaurant
import com.jeanpigomez.zomatotest.network.ZomatoApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RestaurantListViewModel : BaseViewModel() {
    @Inject
    lateinit var zomatoApi: ZomatoApi

    private val restaurantListAdapter: RestaurantListAdapter = RestaurantListAdapter()

    private var subscription: CompositeDisposable = CompositeDisposable()

    private val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    private val errorMessage: MutableLiveData<Int> = MutableLiveData()
    private val locationDetails: MutableLiveData<LocationDetails> = MutableLiveData()

    init {
        loadingVisibility.value = View.GONE
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun getLocationDetails(entityId: Long, entityType: String) {
        subscription.add(zomatoApi.getLocationDetails(entityId, entityType)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveLocationDetailsStart() }
                .doOnTerminate { onRetrieveLocationDetailsFinish() }
                .subscribe(this::onRetrieveLocationDetailsSuccess, this::onRetrieveLocationDetailsError))
    }

    private fun onRetrieveLocationDetailsStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveLocationDetailsFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveLocationDetailsSuccess(result: LocationDetails) {
        locationDetails.value = result
        if (result.bestRatedRestaurant.isNotEmpty()) {
            val restaurantsList = ArrayList<Restaurant>()
            result.bestRatedRestaurant.forEach {
                restaurantsList.add(it.restaurant)
            }
            restaurantListAdapter.updateRestaurantList(restaurantsList)
        } else
            errorMessage.value = R.string.no_restaurants
    }

    private fun onRetrieveLocationDetailsError(error: Throwable) {
        errorMessage.value = R.string.no_restaurants
    }

    fun getErrorMessage(): MutableLiveData<Int> = errorMessage

    fun getLoadingVisibility(): MutableLiveData<Int> = loadingVisibility

    fun getRestaurantListAdapter(): RestaurantListAdapter = restaurantListAdapter
}
