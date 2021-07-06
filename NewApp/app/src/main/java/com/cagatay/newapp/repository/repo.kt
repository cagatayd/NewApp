package com.cagatay.newapp.repository

import com.cagatay.newapp.service.NewsAPI
import com.cagatay.newapp.util.AppContant
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class repo @Inject constructor(
    private val newsAPI: NewsAPI
) {


suspend fun getTopHeadLineNews(quertString: String)=newsAPI.getTopHeadLinesNews(quertString,AppContant.API_KEY)
suspend fun getHeadLinesByCategory(country:String?, category : String)=newsAPI.getHeadLinesByCategory(country , category,AppContant.API_KEY)
}