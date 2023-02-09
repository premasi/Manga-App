package rk.enkidu.mangaapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import rk.enkidu.mangaapp.data.paging3.AlphaSeriesPaging
import rk.enkidu.mangaapp.data.paging3.FlameSeriesPaging
import rk.enkidu.mangaapp.data.paging3.SeriesPaging
import rk.enkidu.mangaapp.data.response.*
import rk.enkidu.mangaapp.data.retrofit.ApiService

class Repository(private val apiService: ApiService) {
    fun getAllSeries() : LiveData<PagingData<SeriesItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
//                enablePlaceholders = true,
//                maxSize = 3,
//                prefetchDistance = 5,
                initialLoadSize = 30
            ),
            pagingSourceFactory = {
                SeriesPaging(apiService)
            }
        ).liveData
    }

    fun getAllAlphaSeries() : LiveData<PagingData<AlphaSeriesItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
//                enablePlaceholders = true,
//                maxSize = 3,
//                prefetchDistance = 5,
                initialLoadSize = 30
            ),
            pagingSourceFactory = {
                AlphaSeriesPaging(apiService)
            }
        ).liveData
    }

    fun getAllFlameSeries() : LiveData<PagingData<FlameSeriesItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
//                enablePlaceholders = true,
//                maxSize = 3,
//                prefetchDistance = 5,
                initialLoadSize = 30
            ),
            pagingSourceFactory = {
                FlameSeriesPaging(apiService)
            }
        ).liveData
    }

    fun getDetailManga(id: String, provider: String): LiveData<rk.enkidu.mangaapp.data.Result<DetailData>> = liveData {
        emit(rk.enkidu.mangaapp.data.Result.Loading)
        try{
            val service = apiService.getDetailManga("3eba0b1258msh3a63be1292b0d58p1723f4jsn76f521bfdf36", "manga-scrapper.p.rapidapi.com",  id, provider).data
            val detail = DetailData(
                service.mangaCover,
                service.scrapeDate,
                service.type,
                service.mangaSynopsis,
                service.mangaTitle,
                service.id,
                service.mangaShortUrl,
                service.mangaUrl
            )

            emit(rk.enkidu.mangaapp.data.Result.Success(detail))

        } catch (e: Exception){
            emit(rk.enkidu.mangaapp.data.Result.Error(e.message.toString()))
        }
    }

    fun getListChapter(id: String, provider: String): LiveData<rk.enkidu.mangaapp.data.Result<List<ChapterSeriesItem>>> = liveData {
        try {
            val service = apiService.getListChapterManga("3eba0b1258msh3a63be1292b0d58p1723f4jsn76f521bfdf36", "manga-scrapper.p.rapidapi.com",  id, provider).data
            val data = service?.series
            val chapter = data?.map{
                ChapterSeriesItem(
                   it.chapterOrder,
                   it.chapterNextSlug,
                   it.scrapeDate,
                   it.chapterPrevSlug,
                   it.chapterShortUrl,
                   it.type,
                   it.chapterTitle,
                   it.chapterNumber,
                   it.chapterUrl,
                   it.id,
                   it.chapterContent,
                   it.chapterDate)
            }
            emit(rk.enkidu.mangaapp.data.Result.Success(chapter!!))
        } catch (e: Exception){
            emit(rk.enkidu.mangaapp.data.Result.Error(e.message.toString()))
        }
    }

    fun getChapter(id: String, chapter: String, provider: String): LiveData<rk.enkidu.mangaapp.data.Result<List<String>>> = liveData {
        emit(rk.enkidu.mangaapp.data.Result.Loading)
        try{
            val service = apiService.getChapterManga("3eba0b1258msh3a63be1292b0d58p1723f4jsn76f521bfdf36", "manga-scrapper.p.rapidapi.com",  id, chapter,provider).data?.chapterContent
            emit(rk.enkidu.mangaapp.data.Result.Success(service!!))
        } catch (e: Exception){
            emit(rk.enkidu.mangaapp.data.Result.Error(e.message.toString()))
        }
    }
}