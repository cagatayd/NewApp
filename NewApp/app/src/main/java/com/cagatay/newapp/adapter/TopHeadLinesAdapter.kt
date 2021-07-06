package com.cagatay.newapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cagatay.newapp.R
import com.cagatay.newapp.model.articles
import com.cagatay.newapp.util.GlideHelper
import com.cagatay.newapp.view.HomeFragmentDirections

class TopHeadLinesAdapter:RecyclerView.Adapter<TopHeadLinesAdapter.TopHeadLinesViewHolder>(),OnNewsClickListener {


    class TopHeadLinesViewHolder(itemview:View):RecyclerView.ViewHolder(itemview) {

    }

    private val diffUtil = object : DiffUtil.ItemCallback<articles>(){ // en ufak güncellemeleri recyleviewde güncellmek için
        override fun areItemsTheSame(oldItem: articles, newItem: articles): Boolean { // itemlerı aynı mmı

            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: articles, newItem: articles): Boolean { // içerikleri aynı mı?
            return oldItem==newItem
        }

    }
    private val recylelistdiffer= AsyncListDiffer(this,diffUtil)

    var topheadlinesnewslist:List<articles>
        get() = recylelistdiffer.currentList
        set(value) = recylelistdiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopHeadLinesViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.news_row,parent,false)
        return TopHeadLinesViewHolder(view)

    }

    override fun onBindViewHolder(holder: TopHeadLinesViewHolder, position: Int) {

        val newsposter=holder.itemView.findViewById<ImageView>(R.id.img_newsposter)
        val newsauthor=holder.itemView.findViewById<TextView>(R.id.txt_author)
        val newspostertitle=holder.itemView.findViewById<TextView>(R.id.txt_news_poster_title)


        val topheadlinenews=topheadlinesnewslist[position]

        holder.itemView.apply {
            if(topheadlinenews?.urlToImage != null && topheadlinenews?.author !=null && topheadlinenews?.title !=null){
                GlideHelper.loadImage(context,topheadlinenews.urlToImage,newsposter)
                newsauthor.text=topheadlinenews?.author
                newspostertitle.text=topheadlinenews?.title
            }

            holder.itemView.setOnClickListener {
                onNewsClicked(holder.itemView,topheadlinenews)
            }
        }


    }

    override fun getItemCount(): Int {
        return topheadlinesnewslist.size

    }

    override fun onNewsClicked(v: View, news: articles) {
        val action=HomeFragmentDirections.actionHomeFragment2ToDetailFragment(news.author,news.title,news.description,news.url,news.urlToImage,news.publishedAt,news.content)
        Navigation.findNavController(v).navigate(action)
    }


}