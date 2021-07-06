package com.cagatay.newapp.di

import com.cagatay.newapp.service.NewsAPI
import com.cagatay.newapp.util.AppContant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

    @Module
    @InstallIn(SingletonComponent::class)
    object NetworkModule {

        @Provides
        @Singleton
        fun provideRetrofit(): Retrofit =
            Retrofit.Builder()
                .baseUrl(AppContant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        @Provides
        @Singleton
        fun providecnewsapi(retrofit: Retrofit):NewsAPI=
            retrofit.create(NewsAPI::class.java)

    }