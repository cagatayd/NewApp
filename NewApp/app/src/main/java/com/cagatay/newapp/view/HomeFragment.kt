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
    private   var selectedcountry="tr"


    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewmodel=ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        val binding =FragmentHomeBinding.bind(view)
        fragmentBinding=binding




        lifecycleScope.launchWhenCreated {
           viewmodel.gettopheadlinenews(selectedcountry)
        }

        observeLiveData()

        setCategoryAdapter()

    }


    private fun setCategoryAdapter(){

        categoryAdapter = CategoryAdapter(this) /// on click listenerı bu sınıfta implemente ettğim için this dedim

        val categoryList = arrayListOf<String>()
        categoryList.add("general")
        categoryList.add("business")
        categoryList.add("entertainment")
        categoryList.add("science")
        categoryList.add("sports")
        categoryList.add("technology")




        categoryAdapter.categoryList = categoryList
        fragmentBinding?.categoryRecyclerview?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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
                    adapter.notifyDataSetChanged()
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

//                return true /// metodun işini bitirir ondan view model çalışmaz

                }

            R.id.selected_Argentina -> {
                selectedcountry = "ar"



            }

            R.id.selected_Australia -> {
                selectedcountry = "au"



            }
            R.id.selected_Austria->{
                selectedcountry="at"


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

    override fun onClick(v: View, category: String) { /// bastıktan sonra ana adapterı günceliyoruz   category: String içi adapterda doluyor

        viewmodel.getHeadLinesByCategory(selectedcountry, category) //
        viewmodel.topheadLinesCategory.observe(viewLifecycleOwner, Observer {
            it.let {
                if (it.status == "ok") {
                    adapter = TopHeadLinesAdapter() // seçilen kategoriye ait api den gelen haberlerin yeniden ekrana basılması için adapter nesnesini oluşturduj
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