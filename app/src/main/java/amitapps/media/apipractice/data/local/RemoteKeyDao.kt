package amitapps.media.apipractice.data.local

import amitapps.media.apipractice.data.remote.model.Result
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface RemoteKeyDao {
    @Query("SELECT * FROM ResultRemoteKeys WHERE name =:id ")
    suspend fun getRemoteKeys(id: String):ResultRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRemoteKeys(remoteKeys: List<ResultRemoteKeys>)

    @Query("DELETE FROM ResultRemoteKeys")
    suspend fun deleteAll()
}