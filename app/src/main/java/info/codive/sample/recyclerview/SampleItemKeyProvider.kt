package info.codive.sample.recyclerview

import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION

class SampleItemKeyProvider(private val recyclerView: RecyclerView) :
    ItemKeyProvider<Long>(SCOPE_MAPPED) {

    override fun getKey(position: Int): Long {
        return (recyclerView.adapter as SampleRecyclerViewAdapter).getItemId(position)
    }

    override fun getPosition(key: Long): Int {
        val holder = recyclerView.findViewHolderForItemId(key)
//        return holder?.layoutPosition ?: NO_POSITION
        return holder?.adapterPosition ?: NO_POSITION
    }
}