package com.example.pantrypalette.api

data class RecipesResult(
    val id: Number?,
    val title: String?,
    val image: String?,
    val imageType: String?,
    val usedIngredientCount: Number?,
    val missedIngredientCount: Number?,
    val missedIngredients: List<Ingredients>?,
    val usedIngredients: List<Ingredients>?,
    val unusedIngredients: List<Ingredients>?,
    val likes: Number?
)

data class Ingredients(
    val id: Number?,
    val amount: Number?,
    val unit: String?,
    val unitLong: String?,
    val unitShort: String?,
    val aisle: String?,
    val name: String?,
    val original: String?,
    val originalName: String?,
    val meta: List<String>?,
    val extendedName: String?,
    val image: String?
)

