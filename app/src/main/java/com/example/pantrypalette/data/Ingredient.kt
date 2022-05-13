package com.example.pantrypalette.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "ingredientList")
data class Ingredient(
    @PrimaryKey(autoGenerate = true) var itemId: Long?,
    val name: String
) : Serializable
