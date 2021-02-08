package info.codive.sample.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //テストデータ作成
        val listItem = createTestData()

        val recyclerView = findViewById<RecyclerView>(R.id.sample_recycler_view)
        recyclerView.adapter = SampleRecyclerViewAdapter(listItem)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //区切り線
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecoration)
    }

    // テストデータ作成
    private fun createTestData(): List<SampleData> {
        val listItem = mutableListOf<SampleData>()
        for (i in 1..10) {
            listItem.add(SampleData(i.toLong(), "Title$i", "Message$i"))
        }
        return listItem
    }
}