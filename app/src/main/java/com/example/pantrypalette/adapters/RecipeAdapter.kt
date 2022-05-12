package com.example.pantrypalette.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pantrypalette.Ingredient
import com.example.pantrypalette.RecipeInfoActivity
import com.example.pantrypalette.RecipeListActivity
import com.example.pantrypalette.api.RecipesResult
import com.example.pantrypalette.databinding.RecipeResultBinding
import retrofit2.Callback

class RecipeAdapter(var context: Context) : ListAdapter<RecipesResult, RecipeAdapter.ViewHolder>(RecipeDiffCallback()) {

    companion object {
        const val RECIPE = "RECIPE"
    }

    val recipes = mutableListOf<RecipesResult>()

    inner class ViewHolder(private var binding: RecipeResultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(rec: RecipesResult) {
            val title = rec.title?.split(" ")?.joinToString(separator = " ", transform = String::capitalize)
            binding.recipeTitle.text = title
            Glide.with(context)
                .load(rec.image)
                .into(binding.recipeImg)

            binding.itemRecipe.setOnClickListener {
                val intentDetails = Intent()
                intentDetails.setClass(context, RecipeInfoActivity::class.java)
                intentDetails.putExtra(RECIPE, rec.id.toString())
                context.startActivity(intentDetails)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val recBinding = RecipeResultBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(recBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    fun addRecipes(r: List<RecipesResult>) {
        recipes.addAll(r)
        //notifyItemInserted(recipes.indexOf(r))
    }

    fun clear() {
        recipes.clear()
    }
}

class RecipeDiffCallback : DiffUtil.ItemCallback<RecipesResult>() {
    override fun areItemsTheSame(oldItem: RecipesResult, newItem: RecipesResult): Boolean {
        return oldItem.title == newItem.title
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: RecipesResult, newItem: RecipesResult): Boolean {
        return oldItem == newItem
    }
}
