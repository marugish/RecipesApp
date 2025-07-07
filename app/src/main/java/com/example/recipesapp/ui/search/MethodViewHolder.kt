package com.example.recipesapp.ui.search

import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.databinding.StepItemBinding

class MethodViewHolder(
    private val binding: StepItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: String, position: Int) {
        binding.description.text = model // необходимо понять, какое название указать
        binding.stepNumber.text = position.toString()
    }
}

