package com.example.pantrypalette.touch

interface IngredientTouchHelperCallback {
    fun onDismissed(position: Int)
    fun onItemMoved(fromPosition: Int, toPosition: Int)
}