package amitapps.media.apipractice.repository

import amitapps.media.apipractice.data.local.ResultDB
import amitapps.media.apipractice.data.remote.model.ApiInterface
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import javax.inject.Inject

class ResultRepository @Inject constructor(
    private val resultApi: ApiInterface,
    private val resultDatabase: ResultDB
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getResults() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100
        ),
        remoteMediator = ResultRemoteMediator(resultApi, resultDatabase),
        pagingSourceFactory = {resultDatabase.resultDao().getResult()}
    ).liveData
}