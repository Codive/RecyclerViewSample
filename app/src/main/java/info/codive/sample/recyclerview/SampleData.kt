package info.codive.sample.recyclerview

class SampleData(
    val id: Long,
    val title: String,
    val message: String,
) {
    override fun equals(other: Any?): Boolean {
        return if (other != null) {
            this.id == (other as SampleData).id
        } else {
            false
        }
    }
}