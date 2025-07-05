package com.example.recipesapp.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.databinding.StepItemBinding

@SuppressLint("NotifyDataSetChanged")
class MethodAdapter(
    private var steps: List<String> = emptyList(),
    private val clickListener: (String) -> Unit
) : RecyclerView.Adapter<MethodViewHolder>() {

    fun updateStepsList(items: List<String>) {
        steps = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MethodViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return MethodViewHolder(
            StepItemBinding.inflate(layoutInspector, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MethodViewHolder, position: Int) {
        holder.bind(steps[position], position)
        holder.itemView.setOnClickListener {
            clickListener(steps[position])
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return steps.size
    }

}