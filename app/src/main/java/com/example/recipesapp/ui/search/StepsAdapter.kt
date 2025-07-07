package com.example.recipesapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.databinding.StepItemBinding
import com.example.recipesapp.domain.search.model.Step

class StepsAdapter(
    private val steps: List<Step>
) : RecyclerView.Adapter<StepsAdapter.StepViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return StepViewHolder(
            StepItemBinding.inflate(layoutInspector, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        holder.bind(steps[position])
    }

    override fun getItemCount(): Int = steps.size

    class StepViewHolder(
        private val binding: StepItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(step: Step) {
            binding.stepNumber.text = String.format("Step %d", step.number)
            binding.description.text = step.step
        }
    }
}