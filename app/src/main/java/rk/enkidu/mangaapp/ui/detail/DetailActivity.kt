package rk.enkidu.mangaapp.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import rk.enkidu.mangaapp.R
import rk.enkidu.mangaapp.data.Result
import rk.enkidu.mangaapp.data.response.ChapterSeriesItem
import rk.enkidu.mangaapp.data.response.DetailData
import rk.enkidu.mangaapp.databinding.ActivityDetailBinding
import rk.enkidu.mangaapp.logic.factory.ViewModelFactory
import rk.enkidu.mangaapp.logic.viewModel.MainViewModel
import rk.enkidu.mangaapp.ui.adapter.ListChapterAdapter
import rk.enkidu.mangaapp.ui.chapter.ChapterActivity

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding

    //viewModel
    private lateinit var mainViewModel: MainViewModel

    //adapter
    private lateinit var adapter : ListChapterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val type = intent.getStringExtra(DETAIL_TYPE)
        val id = intent.getStringExtra(DETAIL_ID)

        Log.v("mangaId", id.toString())
        Log.v("mangaType", type.toString())

        //adapter
        adapter = ListChapterAdapter()
        binding?.rvListChapter?.layoutManager = LinearLayoutManager(this)

        //vm
        mainViewModel = ViewModelProvider(this, ViewModelFactory.getInstance())[MainViewModel::class.java]

        //setup action
        setupAction(id!!, type!!)
    }

    private fun setupAction(id: String, provider: String) {
        mainViewModel.getDetailManga(id, provider).observe(this@DetailActivity){
            when(it){
                is Result.Error -> {
                    showLoading(false)
                    Toast.makeText(this@DetailActivity, getString(R.string.failed_to_load), Toast.LENGTH_SHORT).show()
                }
                Result.Loading -> showLoading(true)
                is Result.Success -> {
                    showLoading(false)
                    val data = it.data
                    showData(data)
                }
            }
        }

        mainViewModel.getListChapter(id, provider).observe(this@DetailActivity){
            when(it){
                is Result.Error -> Toast.makeText(this@DetailActivity, getString(R.string.chapter_failed_to_load), Toast.LENGTH_SHORT).show()
                Result.Loading -> {

                }
                is Result.Success -> {
                    val data = it.data
                    adapter.setListChapter(data)
                    binding?.rvListChapter?.adapter = adapter

                    adapter.setOnItemClickCallback(object : ListChapterAdapter.OnItemClickCallback {
                        override fun onItemClicked(data: ChapterSeriesItem) {
                            val intent = Intent(this@DetailActivity, ChapterActivity::class.java)
                            intent.putExtra(ChapterActivity.EXTRA_CHAPTER, data)
                            intent.putExtra(ChapterActivity.EXTRA_ID, id)
                            intent.putExtra(ChapterActivity.EXTRA_PROVIDER, provider)
                            startActivity(intent)
                        }
                    })
                }
            }
        }
    }

    fun showData(data: DetailData){
        Glide.with(this@DetailActivity)
            .load(data.mangaCover)
            .into(binding?.ivCover!!)

        binding?.tvMangaTitle?.text = data.mangaTitle
        binding?.tvDesc?.text = data.mangaSynopsis
        binding?.tvScrapdate?.text = data.scrapeDate
    }

    private fun showLoading(isLoading: Boolean){ binding?.pbDetail?.visibility = if (isLoading) View.VISIBLE else View.GONE }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object{
        const val DETAIL_TYPE = "detail_type"
        const val DETAIL_ID = "detail_id"
    }
}