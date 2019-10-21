package com.jeanpigomez.zomatotest.network

import com.jeanpigomez.zomatotest.model.LocationDetails
import com.jeanpigomez.zomatotest.model.LocationResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ZomatoApi {

    @GET("locations")
    fun getLocations(@Query("query") query: String): Observable<LocationResponse>

    @GET("location_details")
    fun getLocationDetails(@Query("entity_id") entityId: Long, @Query("entity_type") entityType: String): Observable<LocationDetails>
}
