package com.example.pantrypalette

import android.os.Bundle
import android.util.Log
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
}