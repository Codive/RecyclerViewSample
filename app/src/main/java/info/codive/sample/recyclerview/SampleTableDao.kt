package info.codive.sample.recyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

//Database DAO Mock
class SampleTableDaoMock {
    //メモリ上にテストデータを保持する
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
        if (data.value == null) data.postValue(createTestData()) //テストデータ作成
        return data
    }

    fun insert(position: Int, sampleData: SampleData) {
        val list = data.value!!.toMutableList()
        list.add(position, sampleData)
        data.value = list //ここでのLiveDataの中身の変更をobserveで検知する
    }

    fun delete(position: Int): SampleData {
        val list = data.value!!.toMutableList()
        val sampleData = list.removeAt(position)
        data.value = list //ここでのLiveDataの中身の変更をobserveで検知する
        return sampleData
    }
}