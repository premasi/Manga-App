package rk.enkidu.mangaapp.data.retrofit

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import rk.enkidu.mangaapp.data.response.*

interface ApiService {

    @GET("series/")
    suspend fun getListSeries(
        @Header("X-RapidAPI-Key")  auth: String,
        @Header("X-RapidAPI-Host") host: String,
        @Query("provider") provider: String,
        @Query("pages") page: Int,
        @Query("limit") limit: Int
    ) : ListSeriesResponse

    @GET("series/")
    suspend fun getAlphaListSeries(
        @Header("X-RapidAPI-Key")  auth: String,
        @Header("X-RapidAPI-Host") host: String,
        @Query("provider") provider: String,
        @Query("pages") page: Int,
        @Query("limit") limit: Int
    ) : AlphaListSeriesResponse

    @GET("series/")
    suspend fun getFlameListSeries(
        @Header("X-RapidAPI-Key")  auth: String,
        @Header("X-RapidAPI-Host") host: String,
        @Query("provider") provider: String,
        @Query("pages") page: Int,
        @Query("limit") limit: Int
    ) : FlameListSeriesResponse

    @GET("series/{id}/")
    suspend fun getDetailManga(
        @Header("X-RapidAPI-Key")  auth: String,
        @Header("X-RapidAPI-Host") host: String,
        @Path("id") id: String,
        @Query("provider") provider: String
    ) : DetailSeriesResponse

    @GET("series/{id}/chapters/")
    suspend fun getListChapterManga(
        @Header("X-RapidAPI-Key")  auth: String,
        @Header("X-RapidAPI-Host") host: String,
        @Path("id") id: String,
        @Query("provider") provider: String
    ) : ListChaptersResponse

    @GET("series/{id}/chapter/{chapter}/")
    suspend fun getChapterManga(
        @Header("X-RapidAPI-Key")  auth: String,
        @Header("X-RapidAPI-Host") host: String,
        @Path("id") id: String,
        @Path("chapter") chapter: String,
        @Query("provider") provider: String
    ) : ChapterResponse
}