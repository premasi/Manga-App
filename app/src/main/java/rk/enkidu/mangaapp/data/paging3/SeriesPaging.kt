package rk.enkidu.mangaapp.data.paging3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import rk.enkidu.mangaapp.data.response.SeriesItem
import rk.enkidu.mangaapp.data.retrofit.ApiService

class SeriesPaging(private val apiService: ApiService): PagingSource<Int, SeriesItem>() {
    override fun getRefreshKey(state: PagingState<Int, SeriesItem>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SeriesItem> {
        return try {
            val position = params.key ?: 1

            val responseData = apiService.getListSeries("3eba0b1258msh3a63be1292b0d58p1723f4jsn76f521bfdf36", "manga-scrapper.p.rapidapi.com", "asura", position, params.loadSize).data?.series!!
            LoadResult.Page(
                data = responseData,
                prevKey =  if (position == 1) null else position - 1,
                nextKey =  if (responseData.isEmpty() || position == 30) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}