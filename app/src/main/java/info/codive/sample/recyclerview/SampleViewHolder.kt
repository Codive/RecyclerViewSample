package info.codive.sample.recyclerview

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SampleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.findViewById(R.id.title)
    val image: ImageView = view.findViewById(R.id.image)
    val message: TextView = view.findViewById(R.id.message)
    val button: Button = view.findViewById(R.id.button)
}