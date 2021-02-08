package info.codive.sample.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter

class SampleRecyclerViewAdapter() :
    ListAdapter<SampleData, SampleViewHolder>(SampleItemDiffCallback()) {

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
            title.text = getItem(position).title
            message.text = getItem(position).message
        }
    }

    //アイテムを移動
    fun moveItem(fromPosition: Int, toPosition: Int) {
        val newList = currentList.toMutableList() //Mutableなリストを作成
        val item = newList.removeAt(fromPosition) //データをリストから削除
        newList.add(toPosition, item) //削除したデータを指定された位置に挿入
        submitList(newList) //データを更新
    }

    //アイテムを削除
    fun removeItem(position: Int) {
        val newList = currentList.toMutableList() //Mutableなリストを作成
        newList.removeAt(position) //データを削除
        submitList(newList) //データを更新
    }
}