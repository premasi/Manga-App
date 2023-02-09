package rk.enkidu.mangaapp.di

import rk.enkidu.mangaapp.data.repository.Repository
import rk.enkidu.mangaapp.data.retrofit.ApiConfig

object Injection {
    fun provideRepository() : Repository{
        val apiService = ApiConfig.getApiService()
        return Repository(apiService)
    }
}