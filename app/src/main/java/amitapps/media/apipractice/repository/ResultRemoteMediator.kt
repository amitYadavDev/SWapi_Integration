package amitapps.media.apipractice.repository

import amitapps.media.apipractice.data.local.ResultDB
import amitapps.media.apipractice.data.local.ResultRemoteKeys
import amitapps.media.apipractice.data.remote.model.ApiInterface
import amitapps.media.apipractice.data.remote.model.Result
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import org.w3c.dom.Text

@OptIn(ExperimentalPagingApi::class)
class ResultRemoteMediator(
    private val resultAPI: ApiInterface,
    private val resultDatabase: ResultDB
) : RemoteMediator<Int, Result>() {
    val resultDao = resultDatabase.resultDao()
    val resultRemoteKeysDao = resultDatabase.resultKeyDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Result>): MediatorResult {


        return try {


            val currentPage = 1
//                when(loadType) {
//                LoadType.REFRESH -> {
//                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
//                    remoteKeys?.nextPage?.minus(1) ?: 1
//                }
//                LoadType.PREPEND -> {
//                    val remoteKeys = getRemoteKeyForFirstItem(state)
//                    val prevPage = remoteKeys?.prevPage
//                        ?: return MediatorResult.Success(
//                            endOfPaginationReached = remoteKeys != null
//                        )
//                    prevPage
//                }
//                LoadType.APPEND -> {
//                    val remoteKeys = getRemoteKeyForLastItem(state)
//                    val nextPage = remoteKeys?.nextPage
//                        ?: return MediatorResult.Success(
//                            endOfPaginationReached = remoteKeys != null
//                        )
//                    nextPage
//                }
//            }

            val response = resultAPI.getCharactors(currentPage)
            var endOfPagination = false
            if (response == null) endOfPagination = true

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPagination) null else currentPage + 1


            resultDatabase.withTransaction {
                resultDao.addResult(response.results)

                val keys = response.results.map { it ->
                    ResultRemoteKeys(
                        name = it.name,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                resultRemoteKeysDao.addRemoteKeys(keys)

            }
            MediatorResult.Success(endOfPagination)
        } catch (e : Exception) {
            MediatorResult.Error(e)
        }

        // fetch quote from api
        // save these(Result + Remotekeys) in DB
        // logic for states - Refresh, Prepend, Append

    }
    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Result>
    ): ResultRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.name?.let { id ->
                resultRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Result>
    ): ResultRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { quote ->
                resultRemoteKeysDao.getRemoteKeys(id = quote.name)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Result>
    ): ResultRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { quote ->
                resultRemoteKeysDao.getRemoteKeys(id = quote.name)
            }
    }
}