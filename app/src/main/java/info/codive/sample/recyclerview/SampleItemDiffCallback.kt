package info.codive.sample.recyclerview

import androidx.recyclerview.widget.DiffUtil

class SampleItemDiffCallback : DiffUtil.ItemCallback<SampleData>() {
    override fun areItemsTheSame(oldItem: SampleData, newItem: SampleData): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SampleData, newItem: SampleData): Boolean =
        oldItem == newItem
}