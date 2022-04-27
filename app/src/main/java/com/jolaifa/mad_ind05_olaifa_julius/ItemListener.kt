package com.jolaifa.mad_ind05_olaifa_julius

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class ItemListener(var clickListener: (title: String) -> Unit) {
    fun onClick(data: StateData) = clickListener(data.name)
}

interface StateListener {
    fun onClick(data: StateData)
}

sealed class DataItem{
    abstract val title: String

    data class StateItem(val data: StateData): DataItem() {
        override val title: String
            get() = data.name
    }
}

class DiffCallBack: DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.title == newItem.title
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }

}