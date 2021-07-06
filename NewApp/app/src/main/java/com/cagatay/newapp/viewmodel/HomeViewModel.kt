package com.cagatay.newapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cagatay.newapp.model.BaseNewsResponse
import com.cagatay.newapp.model.articles
import com.cagatay.newapp.repository.repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: repo):ViewModel() {


    val topheadlinesnews=MutableLiveData<BaseNewsResponse>()
    val topheadLinesCategory = MutableLiveData<BaseNewsResponse>()
    val topheadlinesnewsloading=MutableLiveData<Boolean>()
    val topheadlinesnewserorr=MutableLiveData<Boolean>()



    fun gettopheadlinenews(queryString :String)
    {

        if (queryString.isNotEmpty())
        {

            viewModelScope.launch {

                topheadlinesnewsloading.postValue(true)
                repo.getTopHeadLineNews(quertString = queryString).apply {

                    topheadlinesnewsloading.postValue(false)

                    if (this.isSuccessful)
                    {
                        topheadlinesnews.postValue(this.body())
                    }
                    else
                    {
                        topheadlinesnewserorr.postValue(true)
                    }

                }

            }
        }
    }


    fun getHeadLinesByCategory(country:String?, category : String)
    {
        if (country!!.isNotEmpty() && category.isNotEmpty())
        {

            viewModelScope.launch {

                topheadlinesnewsloading.postValue(true)
                repo.getHeadLinesByCategory(country = country , category = category).apply {

                    topheadlinesnewsloading.postValue(false)

                    if (this.isSuccessful)
                    {
                        topheadLinesCategory.postValue(this.body())
                    }
                    else
                    {
                        topheadlinesnewserorr.postValue(true)
                    }
                }
            }
        }
    }
}