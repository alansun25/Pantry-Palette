package com.example.pantrypalette.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipesAPI {
    @GET("/recipes/findByIngredients")
    fun getRecipes(
        @Query("ingredients") ingredients: String,
        @Query("number") number: Number = 25,
        @Query("ranking") ranking: Number = 2,
        @Query("ignorePantry") ignorePantry: Boolean = true,
        @Query("apiKey") apiKey: String = "15b09f702e814d61aa246bea72ed737b"
    ): Call<List<RecipesResult>>

    @GET("/recipes/{id}/information")
    fun getRecipeDetails(
        @Path("id") id: Number,
        @Query("includeNutrition") includeNutrition: Boolean = false,
        @Query("apiKey") apiKey: String = "15b09f702e814d61aa246bea72ed737b"
    ) : Call<RecipeInfoResult>
}