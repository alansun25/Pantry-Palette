package com.example.pantrypalette.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IngredientDAO {
    @Query("SELECT * FROM ingredientList")
    fun getAll() : LiveData<List<Ingredient>>

    @Insert
    fun addIngredient(ingr: Ingredient)

    @Delete
    fun deleteIngredient(ingr: Ingredient)
}