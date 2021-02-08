package info.codive.sample.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.selection.SelectionTracker

class SampleRecyclerViewAdapter() :
    ListAdapter<SampleData, SampleViewHolder>(SampleItemDiffCallback()) {
    lateinit var tracker: SelectionTracker<Long>

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.listitem_sample, parent, false)
        val viewHolder = SampleViewHolder(listItem)

        viewHolder.run {
            image.setImageResource(R.mipmap.ic_launcher)
            button.setOnClickListener {
                Toast.makeText(button.context, message.text, Toast.LENGTH_SHORT).show()
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        val id = getItemId(position)

        holder.run {
            title.text = getItem(position).title
            message.text = getItem(position).message
            container.isActivated = tracker.isSelected(id)
        }
    }

    //デフォルトでは固定値を返すので上書き
    override fun getItemId(position: Int): Long {
        return getItem(position).id
    }
}