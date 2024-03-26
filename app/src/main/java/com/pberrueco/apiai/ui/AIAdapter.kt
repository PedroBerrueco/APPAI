package com.pberrueco.apiai.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pberrueco.apiai.databinding.ItemBaseBinding

class AIAdapter(private val iaNames: Array<String>) : ListAdapter<String, AIAdapter.BaseViewHolder>(BaseItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBaseBinding.inflate(layoutInflater, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = getItem(position)
        // Establecer el texto del ítem
        holder.binding.tvRequest.text = item // El texto directo del string
        // Obtener el nombre de la IA correspondiente a esta posición
        val iaName = iaNames[position]
        // Establecer el texto del nombre de la IA
        holder.binding.tvAi.text = iaName
    }

    inner class BaseViewHolder (val binding: ItemBaseBinding): RecyclerView.ViewHolder(binding.root)
}

object BaseItemCallback: DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}