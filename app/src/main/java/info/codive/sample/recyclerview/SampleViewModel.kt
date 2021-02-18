package info.codive.sample.recyclerview

import androidx.lifecycle.ViewModel

class SampleViewModel(private val repository: SampleRepository) : ViewModel() {
    val sampleDataListLiveData = repository.getAll()
}