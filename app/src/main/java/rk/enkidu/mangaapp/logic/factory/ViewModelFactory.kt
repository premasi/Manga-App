package rk.enkidu.mangaapp.logic.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rk.enkidu.mangaapp.data.repository.Repository
import rk.enkidu.mangaapp.di.Injection
import rk.enkidu.mangaapp.logic.viewModel.MainViewModel

class ViewModelFactory(private val repository: Repository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository())
            }.also { instance = it }
    }
}