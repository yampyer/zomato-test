package com.jeanpigomez.zomatotest.di.component

import com.jeanpigomez.zomatotest.di.module.NetworkModule
import com.jeanpigomez.zomatotest.ui.ChooseCityViewModel
import com.jeanpigomez.zomatotest.ui.RestaurantListViewModel
import com.jeanpigomez.zomatotest.ui.RestaurantViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for viewModels.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(chooseCityViewModel: ChooseCityViewModel)

    fun inject(restaurantListViewModel: RestaurantListViewModel)

    fun inject(restaurantViewModel: RestaurantViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}
