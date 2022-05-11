package com.example.pantrypalette

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.pantrypalette.api.IngredientsResult
import com.example.pantrypalette.api.RecipesAPI
import com.example.pantrypalette.api.RecipesResult
import com.example.pantrypalette.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.InputStream
import java.lang.reflect.GenericArrayType

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
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

        // TODO: Add ingredient to recycler view when clicked in adapter
    }
}