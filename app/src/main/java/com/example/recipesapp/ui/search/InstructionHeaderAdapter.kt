package com.example.recipesapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.databinding.InstructionHeaderItemBinding

class InstructionHeaderAdapter(
    private val title: String
) : RecyclerView.Adapter<InstructionHeaderAdapter.HeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return HeaderViewHolder(
            InstructionHeaderItemBinding.inflate(layoutInspector, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(title)
    }

    override fun getItemCount(): Int = 1

    class HeaderViewHolder(
        private val binding: InstructionHeaderItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String) {
            binding.instructionTitle.text = title
        }
    }
}