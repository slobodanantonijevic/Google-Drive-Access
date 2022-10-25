package com.sloba.googledriveaccess.app.di

import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.sloba.googledriveaccess.BuildConfig
import com.sloba.googledriveaccess.app.network.Api
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Hilt DI module to provide HTTP client
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Building OkHttpClient client
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(getLoggingInterceptor())
        httpClient.addInterceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .build()

            val response = chain.proceed(request)
            response
        }
        httpClient.connectTimeout(1, TimeUnit.SECONDS)
        httpClient.readTimeout(1, TimeUnit.SECONDS)
        return httpClient.build()
    }

    /**
     * Adding the interceptor for logging API calls in logCat (DEBUG build only)
     */
    private fun getLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }

    /**
     * Building a Retrofit instance
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val moshi =
            Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.DRIVE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    /**
     * Actually creating the service from the API interface
     */
    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

}