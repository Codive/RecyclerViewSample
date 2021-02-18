package info.codive.sample.recyclerview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SampleViewModel(private val repository: SampleRepository) : ViewModel() {
    val sampleDataListLiveData = repository.getAll()

    //アイテムを移動
    fun moveItem(fromPosition: Int, toPosition: Int) {
        viewModelScope.launch {
            repository.move(fromPosition, toPosition)
        }
    }

    //アイテムを削除
    fun removeItem(position: Int) {
        viewModelScope.launch {
            repository.delete(position)
        }
    }
}