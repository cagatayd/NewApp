package com.cagatay.newapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cagatay.newapp.R
import com.cagatay.newapp.databinding.FragmentDetailBinding
import com.cagatay.newapp.util.GlideHelper

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var detailfragmentbinding:FragmentDetailBinding?=null

     var news_poster:String?=null
     var news_poster_title:String?=null
     var news_author:String?=null
      var news_description:String?=null
      var news_publishat:String?=null
      var news_content:String?=null
       var news_url:String?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailBinding.bind(view)
        detailfragmentbinding=binding

        arguments?.let {


            news_poster=DetailFragmentArgs.fromBundle(it).urlToImage
            news_poster_title=DetailFragmentArgs.fromBundle(it).posterTitle
            news_author=DetailFragmentArgs.fromBundle(it).author
            news_description=DetailFragmentArgs.fromBundle(it).description
            news_publishat=DetailFragmentArgs.fromBundle(it).publishedAt
            news_content=DetailFragmentArgs.fromBundle(it).content
            news_url=DetailFragmentArgs.fromBundle(it).url

        }

        news_poster?.let {
            GlideHelper.loadImage(requireContext(),
                it,detailfragmentbinding!!.detailnewsposter)
        }

        detailfragmentbinding!!.detailTitle.text=news_poster_title
        detailfragmentbinding!!.detailAuthorName.text=news_author
        detailfragmentbinding!!.detailDesciription.text=news_description
        detailfragmentbinding!!.detailContent.text=news_content
        detailfragmentbinding!!.detailPublishatName.text=news_publishat
        detailfragmentbinding!!.detailUrlName.text=news_url




    }



}