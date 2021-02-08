package info.codive.sample.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var tracker: SelectionTracker<Long>
    private lateinit var adapter: SampleRecyclerViewAdapter

    private val actionModeCallback: SampleActionModeCallback by lazy {
        SampleActionModeCallback(adapter)
    }

    private val selectionObserver = object : SelectionTracker.SelectionObserver<Long>() {
        override fun onSelectionChanged() {
            super.onSelectionChanged()
            when {
                //ActionMode起動
                (tracker.hasSelection() && actionModeCallback.actionMode == null) -> {
                    actionModeCallback.actionMode = startSupportActionMode(actionModeCallback)
                }
                //ActionMode終了
                (!tracker.hasSelection() && actionModeCallback.actionMode != null) -> {
                    actionModeCallback.actionMode?.finish()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //テストデータ作成
        val itemList = createTestData()

        val recyclerView = findViewById<RecyclerView>(R.id.sample_recycler_view)
        adapter = SampleRecyclerViewAdapter()
        adapter.submitList(itemList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //区切り線
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecoration)

        tracker = SelectionTracker.Builder(
            "selectionId",
            recyclerView,
            SampleItemKeyProvider(recyclerView),
            SampleItemDetailsLookup(recyclerView),
            StorageStrategy.createLongStorage()
        )
            .withSelectionPredicate(SelectionPredicates.createSelectAnything()) //複数行選択可
            .build()

        tracker.addObserver(selectionObserver)
        (recyclerView.adapter as SampleRecyclerViewAdapter).tracker = tracker

        savedInstanceState?.apply {
            tracker.onRestoreInstanceState(this)
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        tracker.onSaveInstanceState(outState)
    }
}