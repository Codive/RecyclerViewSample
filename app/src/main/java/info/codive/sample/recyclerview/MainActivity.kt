package info.codive.sample.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: SampleRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.sample_recycler_view)
        adapter = SampleRecyclerViewAdapter(SampleItemDiffCallback())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //区切り線
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecoration)

        // ViewModel設定(LiveDataの監視)
        val sampleTableDao = SampleTableDaoMock()
        val repository = SampleRepository(sampleTableDao)
        val viewModelFactory = SampleViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(SampleViewModel::class.java)

        viewModel.sampleDataListLiveData.observe(this) {
            adapter.submitList(it)
        }
    }
}