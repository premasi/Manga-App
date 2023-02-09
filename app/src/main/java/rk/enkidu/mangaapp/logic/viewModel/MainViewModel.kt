package rk.enkidu.mangaapp.logic.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import rk.enkidu.mangaapp.data.repository.Repository
import rk.enkidu.mangaapp.data.response.AlphaSeriesItem
import rk.enkidu.mangaapp.data.response.FlameSeriesItem
import rk.enkidu.mangaapp.data.response.SeriesItem

class MainViewModel(private val repository: Repository): ViewModel() {
    fun getSeries(): LiveData<PagingData<SeriesItem>>{
        return repository.getAllSeries().cachedIn(viewModelScope)
    }

    fun getAlphaSeries(): LiveData<PagingData<AlphaSeriesItem>>{
        return repository.getAllAlphaSeries().cachedIn(viewModelScope)
    }

    fun getFlameSeries(): LiveData<PagingData<FlameSeriesItem>>{
        return repository.getAllFlameSeries().cachedIn(viewModelScope)
    }

    fun getDetailManga(id: String, provider: String) = repository.getDetailManga(id, provider)

    fun getListChapter(id: String, provider: String) = repository.getListChapter(id, provider)

    fun getChapter(id: String, chapter: String, provider: String) = repository.getChapter(id, chapter, provider)
}