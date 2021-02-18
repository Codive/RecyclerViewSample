package info.codive.sample.recyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

//Database DAO Mock
class SampleTableDaoMock {
    private val data = MutableLiveData<List<SampleData>>()

    // テストデータ作成
    private fun createTestData(): MutableList<SampleData> {
        val listItem = mutableListOf<SampleData>()
        for (i in 1..10) {
            listItem.add(SampleData(i.toLong(), "Title$i", "Message$i"))
        }
        return listItem
    }

    fun getAllLiveData(): LiveData<List<SampleData>> {
        data.postValue(createTestData()) //テストデータ作成
        return data
    }

    fun insert(position: Int, sampleData: SampleData) {
        //TOOD:
    }

    fun update(sampleData: SampleData) {
        //TOOD:
    }

    fun delete(position: Int): SampleData? {
        val list = data.value?.toMutableList()
        return list?.removeAt(position)
    }
}