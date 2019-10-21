package com.jeanpigomez.zomatotest.base

import android.arch.lifecycle.ViewModel
import com.jeanpigomez.zomatotest.di.component.DaggerViewModelInjector
import com.jeanpigomez.zomatotest.di.component.ViewModelInjector
import com.jeanpigomez.zomatotest.di.module.NetworkModule
import com.jeanpigomez.zomatotest.ui.ChooseCityViewModel
import com.jeanpigomez.zomatotest.ui.RestaurantListViewModel
import com.jeanpigomez.zomatotest.ui.RestaurantViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is ChooseCityViewModel -> injector.inject(this)
            is RestaurantListViewModel -> injector.inject(this)
            is RestaurantViewModel -> injector.inject(this)
        }
    }
}