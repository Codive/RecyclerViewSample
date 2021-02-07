package info.codive.sample.recyclerview

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView

/**
 * https://developer.android.com/reference/androidx/recyclerview/selection/ItemDetailsLookup
 */
class SampleItemDetailsLookup(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {
    override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? {
        return recyclerView.findChildViewUnder(e.x, e.y)?.let {
            (recyclerView.getChildViewHolder(it) as SampleViewHolder).getItemDetails()
        }
    }
}