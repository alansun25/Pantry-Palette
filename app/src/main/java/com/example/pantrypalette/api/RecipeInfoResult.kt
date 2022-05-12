package com.example.pantrypalette.api

data class RecipeInfoResult(
    val id: Number?,
    val title: String?,
    val image: String?,
    val imageType: String?,
    val servings: Number?,
    val readyInMinutes: Number?,
    val license: String?,
    val sourceName: String?,
    val sourceUrl: String?,
    val spoonacularSourceUrl: String?,
    val aggregateLikes: Number?,
    val healthScore: Number?,
    val spoonacularScore: Number?,
    val pricePerServing: Number?,
    val analyzedInstructions: List<Any>?,
    val cheap: Boolean?,
    val creditsText: String?,
    val cuisines: List<Any>?,
    val dairyFree: Boolean?,
    val diets: List<Any>?,
    val gaps: String?,
    val glutenFree: Boolean?,
    val instructions: String?,
    val ketogenic: Boolean?,
    val lowFodmap: Boolean?,
    val occasions: List<Any>?,
    val sustainable: Boolean?,
    val vegan: Boolean?,
    val vegetarian: Boolean?,
    val veryHealthy: Boolean?,
    val veryPopular: Boolean?,
    val whole30: Boolean?,
    val weightWatcherSmartPoints: Number?,
    val dishTypes: List<String>?,
    val extendedIngredients: List<Any>?,
    val summary: String?,
    val winePairing: WinePairing?
)

data class ExtendedIngredients(
    val aisle: String?,
    val amount: Number?,
    val consitency: String?,
    val id: Number?,
    val image: String?,
    val measures: Measures?,
    val meta: List<String>?,
    val name: String?,
    val original: String?,
    val originalName: String?,
    val unit: String?
)

data class Measures(val metric: Metric?, val us: Us?)

data class Metric(val amount: Number?, val unitLong: String?, val unitShort: String?)

data class ProductMatches(
    val id: Number?,
    val title: String?,
    val description: String?,
    val price: String?,
    val imageUrl: String?,
    val averageRating: Number?,
    val ratingCount: Number?,
    val score: Number?,
    val link: String?
)

data class Us(val amount: Number?, val unitLong: String?, val unitShort: String?)

data class WinePairing(
    val pairedWines: List<String>?,
    val pairingText: String?,
    val productMatches: List<ProductMatches>?
)
