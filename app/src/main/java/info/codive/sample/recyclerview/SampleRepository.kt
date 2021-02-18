package info.codive.sample.recyclerview

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

/**
 * Repositoryを利用しデータソースを隠蔽する。
 * Roomに値が保存されていない場合はAPIからデータを取得、
 * Roomに値が保存されていて、かつ最後に取得した日時から１時間過ぎていればAPIからデータを取得する、
 * それ以外の場合はRoomから値を取得するなど。
 * APIから取得した場合はRoomにまず保存し、その後RoomからLiveData(Paging)を返す。
 */
class SampleRepository(private val mDao: SampleTableDaoMock) {
    fun getAll(): LiveData<List<SampleData>> {
        return mDao.getAllLiveData()
    }

    @WorkerThread
    suspend fun move(fromPosition: Int, toPosition: Int) {
        val sampleData = mDao.delete(fromPosition)
        mDao.insert(toPosition, sampleData)
    }

    @WorkerThread
    suspend fun delete(position: Int) {
        mDao.delete(position)
    }
}