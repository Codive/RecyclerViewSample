package info.codive.sample.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: SampleRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.sample_recycler_view)
        adapter = SampleRecyclerViewAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL) //区切り線
        recyclerView.addItemDecoration(itemDecoration)

        // ViewModel設定(LiveDataの監視)
        val sampleTableDao = SampleTableDaoMock()
        val repository = SampleRepository(sampleTableDao)
        val viewModelFactory = SampleViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(SampleViewModel::class.java)

        viewModel.sampleDataListLiveData.observe(this) {
            adapter.submitList(it)
        }

        // ItemTouchHelper作成と適用
        val itemTouchHelper = ItemTouchHelper(SampleItemTouchHelperCallback(viewModel))
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}