package com.example.pantrypalette

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.pantrypalette.adapters.RecipeAdapter
import com.example.pantrypalette.api.RecipeInfoResult
import com.example.pantrypalette.api.RecipesAPI
import com.example.pantrypalette.api.RecipesResult
import com.example.pantrypalette.databinding.ActivityRecipeInfoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecipeInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeInfoBinding.inflate(layoutInflater)
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
        val call = recipesAPI.getRecipeDetails(
            intent.getStringExtra(RecipeAdapter.RECIPE)!!.toInt(),
            false
        )

        call.enqueue(object : Callback<RecipeInfoResult> {
            override fun onResponse(
                call: Call<RecipeInfoResult>,
                response: Response<RecipeInfoResult>
            ) {
                try {
                    val body = response.body()

                    Log.d("RESPONSE", "${body?.title}")

                    binding.tvRecipeTitle.text = body?.title
                    binding.tvSummary.text = body?.summary
                    Glide.with(this@RecipeInfoActivity)
                        .load(body?.image)
                        .into(binding.imgRecipe)

                } catch (t: Throwable) {
                    // TODO
                }
            }

            override fun onFailure(call: Call<RecipeInfoResult>, t: Throwable) {
                try {
                    // TODO
                } catch (t: Throwable) {
                    Log.e("Error", "Failure")
                }
            }
        })
    }
}