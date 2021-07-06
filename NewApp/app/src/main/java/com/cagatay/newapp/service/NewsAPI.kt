package com.cagatay.newapp.service

import com.cagatay.newapp.model.BaseNewsResponse
import com.cagatay.newapp.model.articles
import com.cagatay.newapp.util.AppContant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET(AppContant.TOP_HEADLİNES)
    suspend fun getTopHeadLinesNews(@Query("country")country:String,@Query("apiKey")APIKEY:String):Response<BaseNewsResponse>


    @GET(AppContant.TOP_HEADLİNES)
    suspend fun getHeadLinesByCategory(@Query("country")country:String?,@Query("category") category : String,@Query("apiKey")APIKEY:String):Response<BaseNewsResponse>

}