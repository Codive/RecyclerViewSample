package info.codive.sample.recyclerview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.codive.sample.recyclerview.SampleRepository
import kotlinx.coroutines.launch

class SampleViewModel(private val repository: SampleRepository) : ViewModel() {
    val sampleArray = repository.getAll()// MutableLiveData<List<SampleEntity>>()

    //アイテムを移動
    fun moveItem(fromPosition: Int, toPosition: Int) {
//        viewModelScope.launch {
//            repository.update(fromPosition, toPosition)
//        }
        val newList = sampleArray.value?.toMutableList() //Mutableなリストを作成
        val item = newList?.removeAt(fromPosition) //データをリストから削除
        newList?.add(toPosition, item!!) //削除したデータを指定された位置に挿入
    }

    //アイテムを削除
    fun removeItem(position: Int) {
        viewModelScope.launch {
            repository.delete(position)
        }
    }
}