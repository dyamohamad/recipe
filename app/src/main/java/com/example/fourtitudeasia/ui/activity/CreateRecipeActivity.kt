package com.example.fourtitudeasia.ui.activity

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isEmpty
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.request.RequestOptions
import com.example.fourtitudeasia.R
import com.example.fourtitudeasia.`interface`.onClick
import com.example.fourtitudeasia.adapter.AddIngredientStepAdapter
import com.example.fourtitudeasia.adapter.AddStepAdapter
import com.example.fourtitudeasia.databinding.ActivityCreateRecipeBinding
import com.example.fourtitudeasia.model.RecipeTypeModel
import com.example.fourtitudeasia.util.DateUtil
import com.example.fourtitudeasia.util.PermissionUtil
import com.example.fourtitudeasia.util.RequestCode
import com.example.fourtitudeasia.util.XmlParser
import com.example.fourtitudeasia.viewmodel.CreateRecipeViewModel
import com.work.cyberbully.ui.main.GlideApp
import kotlinx.android.synthetic.main.activity_create_recipe.*
import kotlinx.android.synthetic.main.content_create_recipe.*
import kotlinx.android.synthetic.main.field.*
import kotlinx.android.synthetic.main.field.view.*


class CreateRecipeActivity : AppCompatActivity() {
    /** Declaring an ArrayAdapter to set items to ListView  */
    var list = ArrayList<String>()
    var listStep = ArrayList<String>()
    var arLis = ArrayList<String>()
    var adapter = AddIngredientStepAdapter(list)
    var adapterStep = AddStepAdapter(listStep)
    var GALLERY_REQUEST_CODE = 100
     var image:Uri?=null
    var selectedItem=""
    var selectedPos:Int=0
    val PERMISSIONCODE=100
    lateinit var  viewmodel:CreateRecipeViewModel
    lateinit var binding: ActivityCreateRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)

        val actionBar = supportActionBar

        // Set the action bar title, subtitle and elevation
        if(actionBar!=null){
            actionBar!!.setDisplayHomeAsUpEnabled(true)
            actionBar!!.setDisplayShowHomeEnabled(true)
        }


        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_recipe)

         viewmodel = ViewModelProviders.of(this).get(CreateRecipeViewModel::class.java)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        setSupportActionBar(toolbar)


        initSpinnerList()

        viewmodel.onClickAddEvent.observe(this, Observer {

            PermissionUtil.checkPermission(this@CreateRecipeActivity,
                RequestCode.PERMISSION_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE) {
                pickFromGallery()
            }

        })

        viewmodel.getStatus().observe(this, Observer {

          Toast.makeText(applicationContext,"success",Toast.LENGTH_LONG).show()
        })



        viewmodel.onClickAddIngredient.observe(this, Observer {

            addIngredientList()

        })


        recycler_add.layoutManager = LinearLayoutManager(applicationContext)
        recycler_add.adapter = adapter
        recycler_add.setHasFixedSize(true)

        recycler_add_Steps.layoutManager = LinearLayoutManager(applicationContext)
        recycler_add_Steps.adapter = adapterStep
        recycler_add_Steps.setHasFixedSize(true)



    }


    fun addIngredientList() {

        showCreateCategoryDialog()
    }



    private fun pickFromGallery() {
        //Create an Intent with action as ACTION_PICK
        val intent = Intent(Intent.ACTION_PICK)
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.type = "image/*"
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        val mimeTypes = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        // Launching the Intent
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                GALLERY_REQUEST_CODE -> {
                    var selectedImage = data!!.data
                    val options = RequestOptions()
                    options.fitCenter()

                    GlideApp.with(applicationContext)
                        .load(selectedImage)
                        .apply(options)
                        .into(img)

                    image =selectedImage
                }
            }
        }
    }

    private fun initSpinnerList() {


        arLis.addAll(XmlParser.getRecipeType(applicationContext))
        arLis.removeAt(0)
        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aAdapter = ArrayAdapter(
            applicationContext,
            R.layout.textview, arLis
        )

        // Set layout to use when the list of choices appear
        aAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner.adapter = aAdapter
        spinner.prompt = "Select Category"
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                selectedItem= parent.getItemAtPosition(position).toString()

            } // to close the onItemSelected

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_create_recipe, menu)

        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_save -> {
                //save in
                // db//insert
                saveCreatedData()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun saveCreatedData(){
        if(!tv_name.editText!!.text.toString().isEmpty() && image!=null&& list.size>0&& listStep.size>0){

            val recipeModel=RecipeTypeModel(DateUtil.dateId()+tv_name.editText!!.text.toString(),selectedItem,tv_name.editText!!.text.toString(),image.toString(),list,listStep)
            viewmodel.insertObjectCreated(recipeModel)
        }else{
            Toast.makeText(applicationContext,"Please fill in the fill",Toast.LENGTH_LONG).show()
        }
    }


    fun showCreateCategoryDialog() {
        val context = this
        val builder = AlertDialog.Builder(context)

        val view = layoutInflater.inflate(R.layout.field, null)


        val arr = ArrayList<String>()
        arr.add("Ingredient")
        arr.add("Step")
        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aAdapter = ArrayAdapter(
            applicationContext,
            R.layout.textview, arr
        )

        val newCategory = view.categoryEditText

        // Set layout to use when the list of choices appear
        aAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        view.spinner_category.adapter = aAdapter
        view.spinner_category.prompt = "Select Category"
        view.spinner_category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                if(position==0){
                    newCategory.setHint(getString(R.string.add))
                }else{
                    newCategory.setHint(getString(R.string.add_step))
                }
                selectedPos=position
            } // to close the onItemSelected

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        builder.setView(view)

        // set up the ok button
        builder.setPositiveButton(android.R.string.ok) { dialog, p1 ->
            val newCategory = view.categoryEditText.text.toString()
            var isValid = true
            if (newCategory!!.isBlank()) {
                categoryEditText.error = getString(R.string.please_fill)
                isValid = false
            }

            if (isValid) {
                if(selectedPos==0){
                    list.add(newCategory)
                    adapter!!.update(list)
                    adapter!!.notifyDataSetChanged()
                }else{
                    listStep.add(newCategory)
                    adapterStep!!.update(listStep)
                    adapterStep!!.notifyDataSetChanged()
                }

            }

            if (isValid) {
                dialog.dismiss()
            }
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, p1 ->
            dialog.cancel()
        }

        builder.show()
    }





}
