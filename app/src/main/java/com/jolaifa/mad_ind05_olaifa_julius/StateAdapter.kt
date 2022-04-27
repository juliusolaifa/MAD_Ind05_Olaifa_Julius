package com.jolaifa.mad_ind05_olaifa_julius

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jolaifa.mad_ind05_olaifa_julius.databinding.StateLayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StateAdapter(val clickListener: StateListener): ListAdapter<DataItem, StateAdapter.StateViewHolder>(DiffCallBack()) {
    private val adapterCorountineScope = CoroutineScope(Dispatchers.Default)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateViewHolder {
        return StateViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: StateViewHolder, position: Int) {
        val stateItem = getItem(position) as DataItem.StateItem
        holder.bind(stateItem.data, clickListener)
    }

    fun addItemAndSubmit(list: List<StateData>) {
        adapterCorountineScope.launch {
            val items = list.let {
                list.map { DataItem.StateItem(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    class StateViewHolder(val binding: StateLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(stateData: StateData, clickListener: StateListener) {
            binding.stateData = stateData
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): StateViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = StateLayoutBinding.inflate(layoutInflater, parent, false)
                return StateViewHolder(binding)
            }
        }
    }
}