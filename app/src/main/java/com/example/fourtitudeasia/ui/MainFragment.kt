package com.example.fourtitudeasia.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.fourtitudeasia.R
import com.example.fourtitudeasia.adapter.RecipeListAdapter
import com.example.fourtitudeasia.base.BaseFragment
import com.example.fourtitudeasia.util.GridItemDecoration
import com.example.fourtitudeasia.viewmodel.SharedViewModel
import android.widget.ArrayAdapter
import android.widget.AdapterView
import androidx.lifecycle.Observer
import com.example.fourtitudeasia.databinding.FragmentMainBinding
import com.example.fourtitudeasia.model.RecipeTypeModel
import com.example.fourtitudeasia.ui.activity.CreateRecipeActivity
import com.example.fourtitudeasia.util.XmlParser
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : BaseFragment() {
    companion object {
        fun newInstance() = MainFragment()
    }

    var init = ArrayList<RecipeTypeModel>()
    val adapter = RecipeListAdapter(init)
    lateinit var binding: FragmentMainBinding
    var isFirstTime: Boolean = false
    var isAll: Boolean = false
    lateinit var viewmodel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         viewmodel  = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        isFirstTime = true

        getAllAvailableRecipe()
        viewmodel.onClickAddEvent.observe(viewLifecycleOwner, Observer {
            val intent = Intent(context, CreateRecipeActivity::class.java)
            startActivity(intent)
        })

        initSpinnerList()


    }

    private fun initPopulateAdapter() {
        // val adapter = RecipeListAdapter(init)
        recycler_view.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.addItemDecoration(GridItemDecoration(10, 2))

        recycler_view.adapter = adapter

    }

    private fun initSpinnerList() {

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aAdapter = ArrayAdapter(
            context!!,
            R.layout.textview,
            XmlParser.getRecipeType(requireContext())
        )
        // Set layout to use when the list of choices appear
        aAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner.adapter = aAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                if (isFirstTime == true) {
                    isFirstTime = false
                } else {
                    val selectedItem = parent.getItemAtPosition(position).toString()
                    init = ArrayList()
                    if (position == 0) {
                        isAll=true
                        getAllAvailableRecipe()
                    } else {
                        getRecipeByType(selectedItem)
                    }


                }
            } // to close the onItemSelected

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    fun getAllAvailableRecipe() {

        viewmodel.getAll().observe(viewLifecycleOwner, Observer {
            init.addAll(it)
            if (isAll==true){
                adapter.update(init)
                adapter.notifyDataSetChanged()
                isAll=false
            }else{
                initPopulateAdapter()
            }
        })
    }

    fun getRecipeByType(selectedItem: String) {
        viewmodel.getByType(selectedItem).observe(viewLifecycleOwner, Observer {
            init.addAll(it)
            adapter.update(init)
            adapter.notifyDataSetChanged()

        })
    }


}