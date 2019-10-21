package com.jeanpigomez.zomatotest.ui

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.jeanpigomez.zomatotest.R
import com.jeanpigomez.zomatotest.base.BaseViewModel
import com.jeanpigomez.zomatotest.model.Location
import com.jeanpigomez.zomatotest.model.LocationResponse
import com.jeanpigomez.zomatotest.network.ZomatoApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ChooseCityViewModel : BaseViewModel() {
    @Inject
    lateinit var zomatoApi: ZomatoApi

    private var subscription: CompositeDisposable = CompositeDisposable()

    private val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    private val errorMessage: MutableLiveData<Int> = MutableLiveData()
    private val location: MutableLiveData<Location> = MutableLiveData()

    init {
        loadingVisibility.value = View.GONE
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun getLocation(cityName: String) {
        subscription.add(zomatoApi.getLocations(cityName)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveLocationStart() }
                .doOnTerminate { onRetrieveLocationFinish() }
                .subscribe(this::onRetrieveLocationSuccess, this::onRetrieveLocationError))
    }

    private fun onRetrieveLocationStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveLocationFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveLocationSuccess(result: LocationResponse) {
        if (result.locationSuggestions.isNotEmpty())
            location.value = result.locationSuggestions[0]
    }

    private fun onRetrieveLocationError(error: Throwable) {
        errorMessage.value = R.string.no_city_available
    }

    fun getErrorMessage(): MutableLiveData<Int> = errorMessage

    fun getLocationData(): MutableLiveData<Location> = location

    fun getLoadingVisibility(): MutableLiveData<Int> = loadingVisibility
}
