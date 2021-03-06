package info.codive.sample.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class SampleRecyclerViewAdapter(private val itemList: List<SampleData>) :
    RecyclerView.Adapter<SampleViewHolder>() {

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
        holder.run {
            title.text = itemList[position].title
            message.text = itemList[position].message
        }
    }

    override fun getItemCount(): Int = itemList.size
}