package com.pberrueco.apiai.ui

import android.view.LayoutInflater
import android.view.View
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

        // Establecer el clic oyente para el ícono
        holder.binding.tvAi.setOnClickListener {
            // Alternar entre expandir y colapsar el texto
            val maxLines = if (holder.binding.tvRequest.maxLines == 3) Int.MAX_VALUE else 3
            holder.binding.tvRequest.maxLines = maxLines
        }

    }

    inner class BaseViewHolder(val binding: ItemBaseBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            // Configurar el clic oyente para todo el elemento de la lista
            binding.root.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            // Manejar el clic en toda la vista de la tarjeta aquí
        }
    }
}

object BaseItemCallback: DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}