package com.example.pantrypalette

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.pantrypalette.adapters.RecipeAdapter
import com.example.pantrypalette.api.RecipesAPI
import com.example.pantrypalette.api.RecipesResult
import com.example.pantrypalette.databinding.ActivityRecipeListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeListBinding
    private lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val recipesAPI = retrofit.create(RecipesAPI::class.java)

        // Find recipes from API based on user's ingredients
        val call = recipesAPI.getRecipes(
            intent.getStringExtra(MainActivity.INGREDIENTS).toString(),
            3
        )

        call.enqueue(object : Callback<List<RecipesResult>> {
            override fun onResponse(
                call: Call<List<RecipesResult>>,
                response: Response<List<RecipesResult>>
            ) {
                try {
                    val body = response.body()

                    // TODO: Update adapter and frontend with response

                    // This is how you get recipe name from API response:
                    // body?.get(0)?.title.toString()
                    // Note: The response is an array of recipes, which is why
                    // there is a "get(0)". We want all the returned recipes so
                    // ideally you would loop through them all to get the data you
                    // want to display.

//                    if (body != null) {
//                        for (recipe in body) {
//                            val recRes: RecipeResult = RecipeResult("Title")
//                            binding.recipeTitle.text = body?.get(0)?.title.toString()
//                        }
//                    }

                } catch (t: Throwable) {
                    // TODO
                }
            }

            override fun onFailure(call: Call<List<RecipesResult>>, t: Throwable) {
                try {
                    // TODO
                } catch (t: Throwable) {
                    Log.e("Error", "Failure")
                }
            }
        })
    }

    private fun initRecyclerView() {
        adapter = RecipeAdapter(this)
        binding.recyclerRecipes.adapter = adapter
    }

}