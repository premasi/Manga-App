package rk.enkidu.mangaapp.ui.chapter

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import rk.enkidu.mangaapp.R
import rk.enkidu.mangaapp.data.Result
import rk.enkidu.mangaapp.data.response.ChapterSeriesItem
import rk.enkidu.mangaapp.databinding.ActivityChapterBinding
import rk.enkidu.mangaapp.logic.factory.ViewModelFactory
import rk.enkidu.mangaapp.logic.viewModel.MainViewModel
import rk.enkidu.mangaapp.ui.adapter.ListImageAdapter

class ChapterActivity : AppCompatActivity() {

    private var _binding: ActivityChapterBinding? = null
    private val binding get() = _binding

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityChapterBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val id = intent.getStringExtra(EXTRA_ID)
        val chapter = intent.getParcelableExtra<ChapterSeriesItem>(EXTRA_CHAPTER)
        val type = intent.getStringExtra(EXTRA_PROVIDER)


        mainViewModel = ViewModelProvider(this, ViewModelFactory.getInstance())[MainViewModel::class.java]

        Log.v("mangaIdChapter", id.toString())
        Log.v("mangaChapter", chapter.toString())
        Log.v("mangaTypeChapter", type.toString())

        mainViewModel.getChapter(id!!, chapter?.id!!, type!!).observe(this@ChapterActivity){
            Log.v("ini adalah it", it.toString())
            when(it){
                is Result.Error -> {
                    showLoading(false)
                    Toast.makeText(this@ChapterActivity, getString(R.string.chapter_failed_to_load), Toast.LENGTH_SHORT).show()
                }
                Result.Loading -> showLoading(true)
                is Result.Success -> {

                    showLoading(false)
                    showData(it.data)
                }
            }
        }

    }

    private fun showData(items: List<String>){
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT ||
            applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE ||
            applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_UNDEFINED
        ) {
                //recycle view
                binding?.rvChapter?.layoutManager = LinearLayoutManager(this)
                val listImageAdapter = ListImageAdapter(items)
                binding?.rvChapter?.adapter = listImageAdapter
        }
    }

    private fun showLoading(isLoading: Boolean){ binding?.pbChapter?.visibility = if (isLoading) View.VISIBLE else View.GONE }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object{
        const val EXTRA_CHAPTER = "extra_chapter"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_PROVIDER = "extra_provider"
    }
}