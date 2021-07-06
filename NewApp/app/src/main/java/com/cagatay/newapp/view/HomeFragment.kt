package com.cagatay.newapp.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.cagatay.newapp.R
import com.cagatay.newapp.adapter.CategoryAdapter
import com.cagatay.newapp.adapter.OnCategoryClickListener
import com.cagatay.newapp.adapter.TopHeadLinesAdapter
import com.cagatay.newapp.databinding.FragmentHomeBinding
import com.cagatay.newapp.viewmodel.HomeViewModel


class HomeFragment : Fragment(R.layout.fragment_home),OnCategoryClickListener {

    private var fragmentBinding:FragmentHomeBinding?=null
    lateinit var viewmodel:HomeViewModel
    private lateinit var adapter:TopHeadLinesAdapter
    private lateinit var categoryAdapter : CategoryAdapter
    private lateinit var selectedcountry:String
    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


      //  (activity as MainActivity?)!!.setSupportActionBar(fragmentBinding!!.toolbar)

        viewmodel=ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)



        val binding =FragmentHomeBinding.bind(view)
        fragmentBinding=binding




        lifecycleScope.launchWhenCreated {
            viewmodel.gettopheadlinenews("tr")
        }



        observeLiveData()

        setCategoryAdapter()

    }


    private fun setCategoryAdapter(){
        categoryAdapter = CategoryAdapter(this)

        val categoryList = arrayListOf<String>()
        categoryList.add("business")
        categoryList.add("asas")
        categoryList.add("dsd")
        categoryList.add("dfd")
        categoryList.add("fgfg")
        categoryList.add("dfd")
        categoryList.add("sds")
        categoryList.add("dfd")
        categoryList.add("fgf")
        categoryAdapter.categoryList = categoryList
        fragmentBinding?.categoryRecyclerview?.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL , false
        )
        fragmentBinding?.categoryRecyclerview?.adapter = categoryAdapter

    }
    fun observeLiveData(){
        viewmodel.topheadlinesnews.observe(viewLifecycleOwner, Observer {
            it?.let {

                if (it.status == "ok") {
                    adapter = TopHeadLinesAdapter()
                    adapter.topheadlinesnewslist = it.articles
                    fragmentBinding?.Recyleviewhomefragment?.layoutManager = LinearLayoutManager(
                        requireContext()
                    )
                    fragmentBinding?.Recyleviewhomefragment?.adapter = adapter
                    fragmentBinding?.newshomeErorrTxt?.visibility = View.GONE
                    fragmentBinding?.newshomeyLoadingPrgsbar?.visibility = View.GONE
                } else {
                    fragmentBinding?.newshomeErorrTxt?.visibility = View.VISIBLE
                    fragmentBinding?.newshomeyLoadingPrgsbar?.visibility = View.GONE
                }

            }
        })


        viewmodel.topheadlinesnewserorr.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    fragmentBinding?.newshomeErorrTxt?.visibility = View.VISIBLE
                    fragmentBinding?.newshomeyLoadingPrgsbar?.visibility = View.GONE
                } else {
                    fragmentBinding?.newshomeErorrTxt?.visibility = View.GONE
                    fragmentBinding?.newshomeyLoadingPrgsbar?.visibility = View.GONE
                }

            }
        })

        viewmodel.topheadlinesnewsloading.observe(viewLifecycleOwner, Observer {

            it?.let {
                if (it) {
                    fragmentBinding?.newshomeyLoadingPrgsbar?.visibility = View.VISIBLE
                } else {
                    fragmentBinding?.newshomeyLoadingPrgsbar?.visibility = View.GONE
                }
            }

        })

        viewmodel.topheadLinesCategory.observe(viewLifecycleOwner, Observer {
            it.let {
                if (it.status == "ok") {
                    adapter = TopHeadLinesAdapter()
                    adapter.topheadlinesnewslist = it.articles
                    fragmentBinding?.Recyleviewhomefragment?.layoutManager = LinearLayoutManager(
                        requireContext()
                    )
                    fragmentBinding?.Recyleviewhomefragment?.adapter = adapter
                    fragmentBinding?.newshomeErorrTxt?.visibility = View.GONE
                    fragmentBinding?.newshomeyLoadingPrgsbar?.visibility = View.GONE
                } else {
                    fragmentBinding?.newshomeErorrTxt?.visibility = View.VISIBLE
                    fragmentBinding?.newshomeyLoadingPrgsbar?.visibility = View.GONE
                }
            }
        })



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.country_menu, menu)

    }


    /*
    ae
    ar
    at
    au
    be
    bg
    br
    ca
    ch
    cn
    co
    cu
    cz
    de
    eg
    fr
    gb
    gr
    hk
    hu
    id
    ie
    il
    in
    it
    jp
    kr
    lt
    lv
    ma
    mx
    my
    ng
    nl
    no
    nz
    ph
    pl
    pt
    ro
    rs
    ru
    sa
    se
    sg
    si
    sk
    th
    tr
    tw
     */

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.selected_turkey -> {
                Toast.makeText(requireContext(), "sssssssss", Toast.LENGTH_LONG).show()
                selectedcountry = "tr"

            }

            R.id.selected_Argentina -> {
                selectedcountry = "ae"

            }

            R.id.selected_Australia -> {
                selectedcountry = "ar"
            }


        }

        lifecycleScope.launchWhenCreated {
            viewmodel.gettopheadlinenews(selectedcountry)
        }

        return super.onOptionsItemSelected(item)



    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding==null
    }

    override fun onClick(v: View, category: String) {
        viewmodel.getHeadLinesByCategory("tr",category)
        viewmodel.topheadLinesCategory.observe(viewLifecycleOwner, Observer {
            it.let {
                if (it.status == "ok") {
                    adapter = TopHeadLinesAdapter()
                    adapter.topheadlinesnewslist = it.articles
                    fragmentBinding?.Recyleviewhomefragment?.layoutManager = LinearLayoutManager(
                        requireContext()
                    )
                    fragmentBinding?.Recyleviewhomefragment?.adapter = adapter
                    fragmentBinding?.newshomeErorrTxt?.visibility = View.GONE
                    fragmentBinding?.newshomeyLoadingPrgsbar?.visibility = View.GONE
                } else {
                    fragmentBinding?.newshomeErorrTxt?.visibility = View.VISIBLE
                    fragmentBinding?.newshomeyLoadingPrgsbar?.visibility = View.GONE
                }
            }
        })
    }








}