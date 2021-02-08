package info.codive.sample.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: SampleRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //テストデータ作成
        val listItem = createTestData()

        val recyclerView = findViewById<RecyclerView>(R.id.sample_recycler_view)
        adapter = SampleRecyclerViewAdapter()
        adapter.submitList(listItem)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL) //区切り線
        recyclerView.addItemDecoration(itemDecoration)

        // ItemTouchHelper作成と適用
        val itemTouchHelper = ItemTouchHelper(SampleItemTouchHelperCallback(recyclerView))
        itemTouchHelper.attachToRecyclerView(recyclerView)

        //notifyDataSetChangedを呼び出すテストボタン
        val notifyButton = findViewById<Button>(R.id.notify_button)
        notifyButton.setOnClickListener {
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    // テストデータ作成
    private fun createTestData(): MutableList<SampleData> {
        val listItem = mutableListOf<SampleData>()
        for (i in 1..10) {
            listItem.add(SampleData(i.toLong(), "Title$i", "Message$i"))
        }
        return listItem
    }
}