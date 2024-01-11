package amitapps.media.apipractice.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ResultRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val prevPage: Int?,
    val nextPage: Int?
)
