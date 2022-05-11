package com.example.pantrypalette.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pantrypalette.RecipeResult
import com.example.pantrypalette.databinding.ReciperesultBinding

class RecipeAdapter (var context: Context) : ListAdapter<RecipeResult, RecipeAdapter.ViewHolder>(DiffCallback()) {

    var recList = mutableListOf<RecipeResult>()

    inner class ViewHolder(private var binding: ReciperesultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(rec: RecipeResult) {
            binding.recipeTitle.text = rec.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val recBinding = ReciperesultBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(recBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rec = recList[holder.adapterPosition]
        holder.bind(rec)
    }

    class DiffCallback : DiffUtil.ItemCallback<RecipeResult>() {
        override fun areItemsTheSame(oldItem: RecipeResult, newItem: RecipeResult): Boolean {
            return oldItem.title == newItem.title
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: RecipeResult, newItem: RecipeResult): Boolean {
            return oldItem == newItem
        }
    }

}
