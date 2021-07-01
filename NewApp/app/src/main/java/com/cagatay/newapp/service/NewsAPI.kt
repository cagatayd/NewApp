package com.cagatay.newapp.service

import com.cagatay.newapp.model.BaseNewsResponse
import com.cagatay.newapp.util.AppContant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET(AppContant.TOP_HEADLİNES+AppContant.API_KEY)
    suspend fun getTopHeadLinesNews(@Query("country")country:String?):Response<BaseNewsResponse>

}