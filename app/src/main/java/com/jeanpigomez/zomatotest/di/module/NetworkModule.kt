package com.jeanpigomez.zomatotest.di.module

import com.jeanpigomez.zomatotest.BuildConfig
import com.jeanpigomez.zomatotest.network.AuthInterceptor
import com.jeanpigomez.zomatotest.network.ZomatoApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Module which provides all required dependencies about network
 */

@Module
@Suppress("unused")
object NetworkModule {
    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideLanguageApi(retrofit: Retrofit): ZomatoApi {
        return retrofit.create(ZomatoApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(getOkHttpClient())
                .build()
    }

    /**
     * OkHttpClient
     */
    @Provides
    @Singleton
    fun getOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(AuthInterceptor())
        return builder.build()
    }
}
