package amitapps.media.apipractice.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nullable

@Entity(tableName = "ResultEntity")
data class ResultEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)