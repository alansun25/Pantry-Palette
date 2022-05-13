package com.example.pantrypalette.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pantrypalette.MainActivity
import com.example.pantrypalette.data.AppDatabase
import com.example.pantrypalette.data.Ingredient
import com.example.pantrypalette.touch.IngredientTouchHelperCallback
import com.example.pantrypalette.databinding.IngredientBinding
import kotlin.concurrent.thread

class IngredientAdapter(var context: Context) :
    ListAdapter<Ingredient, IngredientAdapter.ViewHolder>(IngredientDiffCallback()),
    IngredientTouchHelperCallback {

    inner class ViewHolder(private var binding: IngredientBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ingr: Ingredient) {
            binding.tvIngrName.text = ingr.name
            binding.btnDel.setOnClickListener { deleteIngr(this.adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = IngredientBinding.inflate(
            LayoutInflater.from(context),
            parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    fun addIngredient(ingr: Ingredient) {
        thread {
            AppDatabase.getInstance(context).ingredientDao().addIngredient(ingr)
            val ingredients = AppDatabase.getInstance(context).ingredientDao().getAll()

            (context as MainActivity).runOnUiThread {
                this.submitList(ingredients)
            }
        }
    }

    fun deleteIngr(idx: Int) {
        thread {
            AppDatabase.getInstance(context).ingredientDao().deleteIngredient(getItem(idx))
            val ingredients = AppDatabase.getInstance(context).ingredientDao().getAll()

            (context as MainActivity).runOnUiThread {
                this.submitList(ingredients)
            }
        }
    }

    override fun onDismissed(position: Int) {
        deleteIngr(position)
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        notifyItemMoved(fromPosition, toPosition)
    }
}

class IngredientDiffCallback : DiffUtil.ItemCallback<Ingredient>() {
    override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
        return oldItem.name == newItem.name
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
        return oldItem == newItem
    }
}