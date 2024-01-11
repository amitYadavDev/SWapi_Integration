package amitapps.media.apipractice.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ResultEntity::class, ResultRemoteKeys::class], version = 1)
abstract class ResultDB : RoomDatabase() {
    abstract fun resultDao(): ResultDao

    abstract fun resultKeyDao(): RemoteKeyDao


}