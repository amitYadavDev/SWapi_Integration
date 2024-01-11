package amitapps.media.apipractice.data.local

import amitapps.media.apipractice.data.remote.model.Result
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ResultDao {
    @Query("SELECT * FROM ResultEntity")
    fun getResult(): PagingSource<Int, Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addResult(result: List<Result>)

    @Query("DELETE FROM ResultEntity")
    suspend fun deleteAll()
}