package amitapps.media.apipractice.di

import amitapps.media.apipractice.data.local.ResultDB
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : ResultDB {
        return Room.databaseBuilder(context, ResultDB::class.java, "resultDB").build()
    }
}