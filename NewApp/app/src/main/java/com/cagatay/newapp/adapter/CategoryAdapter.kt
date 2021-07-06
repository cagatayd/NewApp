package com.cagatay.newapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cagatay.newapp.R
import com.cagatay.newapp.model.articles
import com.cagatay.newapp.util.GlideHelper
import com.cagatay.newapp.view.HomeFragmentDirections

class CategoryAdapter(private val onCategoryClickListener: OnCategoryClickListener): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){


    class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    private val diffUtil = object : DiffUtil.ItemCallback<String>(){ // en ufak güncellemeleri recyleviewde güncellmek için
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean { // itemlerı aynı mmı

            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean { // içerikleri aynı mı?
            return oldItem==newItem
        }

    }
    private val recylelistdiffer= AsyncListDiffer(this,diffUtil)

    var categoryList:List<String>
        get() = recylelistdiffer.currentList
        set(value) = recylelistdiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.category_row,parent,false)
        return CategoryViewHolder(view)

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val buttonCategory=holder.itemView.findViewById<AppCompatButton>(R.id.button_category)
        val category=categoryList[position]

        var isClicked = false
        holder.itemView.apply {
            buttonCategory.text = category
            buttonCategory.setOnClickListener {

                if (!isClicked){
                    isClicked = true
                    buttonCategory.setTextColor(context.getColor(R.color.white))
                    buttonCategory.setBackgroundColor(context.getColor(R.color.black))
                }else{
                    isClicked = false
                    buttonCategory.setTextColor(context.getColor(R.color.black))
                    buttonCategory.setBackgroundColor(context.getColor(R.color.white))
                }
                onCategoryClickListener.onClick(holder.itemView , category)
            }
        }


    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}