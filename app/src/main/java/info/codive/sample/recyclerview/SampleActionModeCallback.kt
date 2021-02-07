package info.codive.sample.recyclerview

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.selection.SelectionTracker

class SampleActionModeCallback(
    private val tracker: SelectionTracker<Long>,
    private val itemList: MutableList<SampleData>,
    private val adapter: SampleRecyclerViewAdapter
) : ActionMode.Callback {
    var actionMode: ActionMode? = null

    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        mode.setTitle(R.string.title_active)
        mode.menuInflater.inflate(R.menu.active_menu, menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
        return false //特に何もなければfalseを返す
    }

    //ActionModeでのメニューボタン押下処理
    override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete -> {
                itemList.removeAll {
                    tracker.selection.contains(it.id)
                }
                adapter.notifyDataSetChanged() //Adapterのデータも更新する
                tracker.clearSelection()
                actionMode?.finish()
                true
            }
            else -> {
                false
            }
        }
    }

    override fun onDestroyActionMode(mode: ActionMode) {
        actionMode = null

        // fixme:戻るボタンを押下するとonDestroyActionModeが実行される。ActionModeでは戻るボタンの名前がandroid.R.id.homeではなく、ハンドリング方法が不明なのでここで選択項目があれば選択解除している
        if (tracker.hasSelection()) tracker.clearSelection()
    }
}