package com.example.pantrypalette

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pantrypalette.api.RecipesAPI
import com.example.pantrypalette.api.RecipesResult
import com.example.pantrypalette.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // TODO: Put the list of recipes returned by the response in a separate activity
    override fun onResume() {
        super.onResume()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val recipesAPI = retrofit.create(RecipesAPI::class.java)

        val call = recipesAPI.getRecipes(
            // TODO: Make this based on user query (only ingredients).
            "chicken,cheese,strawberry",
            3
        )

        call.enqueue(object : Callback<List<RecipesResult>> {
            override fun onResponse(
                call: Call<List<RecipesResult>>,
                response: Response<List<RecipesResult>>
            ) {
                try {
                    val body = response.body()

                    // TODO: Update frontend with response

                    // binding.recipeName.text = body?.get(0)?.title.toString() + "," + body?.get(1)?.title.toString()
                    // binding.usedIngred.text = body?.get(0)?.usedIngredients?.get(0)?.name.toString() + "," + body?.get(0)?.usedIngredients?.get(1)?.name.toString()
                } catch (t: Throwable) {
                    binding.recipeName.text = "$t"
                }
            }

            override fun onFailure(call: Call<List<RecipesResult>>, t: Throwable) {
                try {
                    binding.recipeName.text = "$t"
                } catch (t: Throwable) {
                    Log.e("Error", "Failure")
                }
            }
        })
    }
}