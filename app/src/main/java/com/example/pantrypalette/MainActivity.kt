package com.example.pantrypalette

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.pantrypalette.adapters.IngredientAdapter
import com.example.pantrypalette.api.RecipesAPI
import com.example.pantrypalette.api.RecipesResult
import com.example.pantrypalette.databinding.ActivityMainBinding
import com.example.pantrypalette.touch.IngredientRecyclerTouchCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.InputStream
import java.lang.reflect.GenericArrayType

class MainActivity : AppCompatActivity() {

    companion object {
        const val INGREDIENTS = "INGREDIENTS"
    }

    lateinit var binding : ActivityMainBinding
    private lateinit var adapter: IngredientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Read list of all ingredients
        val inputStream: InputStream = resources.openRawResource(R.raw.ingredients_list)
        val allIngredients = mutableListOf<String>()

        // Create ingredient list for auto-complete
        inputStream.bufferedReader().forEachLine {
            val ingredient = it.split(";")[0]
            allIngredients.add(ingredient)
        }

        // Set auto-complete adapter
        val ingredientAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            allIngredients
        )
        binding.actvIngredient.setAdapter(ingredientAdapter)

        binding.actvIngredient.setOnItemClickListener { adapterView, view, position, id ->
            // Get selected ingredient and clear search bar upon selection
            val ingredient = adapterView.getItemAtPosition(position).toString()
            binding.actvIngredient.text.clear()
            adapter.addIngredient(Ingredient(ingredient))
            adapter.submitList(adapter.selectedIngredients)
        }

        initRecyclerView()

        binding.btnFindRecipes.setOnClickListener {
            val intentDetails = Intent()

            val selectedIngredients = adapter.selectedIngredients.joinToString()

            intentDetails.setClass(this, RecipeListActivity::class.java)
            intentDetails.putExtra(INGREDIENTS, selectedIngredients)
            startActivity(intentDetails)
        }
    }

    private fun initRecyclerView() {
        adapter = IngredientAdapter(this)
        binding.recyclerIngredients.adapter = adapter

        val touchCallbackList = IngredientRecyclerTouchCallback(adapter)
        val itemTouchHelper = ItemTouchHelper(touchCallbackList)
        itemTouchHelper.attachToRecyclerView(binding.recyclerIngredients)
    }
}