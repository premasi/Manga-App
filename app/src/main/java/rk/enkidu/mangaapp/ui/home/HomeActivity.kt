package rk.enkidu.mangaapp.ui.home


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import rk.enkidu.mangaapp.databinding.ActivityHomeBinding
import rk.enkidu.mangaapp.logic.factory.ViewModelFactory
import rk.enkidu.mangaapp.logic.viewModel.MainViewModel
import rk.enkidu.mangaapp.ui.adapter.AlphaListSeriesAdapter
import rk.enkidu.mangaapp.ui.adapter.FlameListSeriesAdapter
import rk.enkidu.mangaapp.ui.adapter.ListSeriesAdapter

class HomeActivity : AppCompatActivity() {

    private var _binding : ActivityHomeBinding? = null
    private val binding get() = _binding

    //vm
    private lateinit var mainViewModel: MainViewModel

    //adapter
    private lateinit var adapter : ListSeriesAdapter
    private lateinit var adapter2 : AlphaListSeriesAdapter
    private lateinit var adapter3 : FlameListSeriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //loading
        showLoading(true)

        //vm instance
        mainViewModel = ViewModelProvider(this, ViewModelFactory.getInstance())[MainViewModel::class.java]

        //adapter
        setupAdapter()

        //action
        setupAction()

    }

    private fun setupAdapter(){
        adapter = ListSeriesAdapter()
        adapter2 = AlphaListSeriesAdapter()
        adapter3 = FlameListSeriesAdapter()

        //asura
        binding?.rvListSeries?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding?.rvListSeries?.adapter = adapter

        //alpha
        binding?.rvListSeriesAlpha?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding?.rvListSeriesAlpha?.adapter = adapter2

        //flame
        binding?.rvListSeriesFlame?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding?.rvListSeriesFlame?.adapter = adapter3
    }

    private fun setupAsura(){
        mainViewModel.getSeries().observe(this@HomeActivity){
            adapter.submitData(lifecycle, it)
        }
    }

    private fun setupAlpha(){
        mainViewModel.getAlphaSeries().observe(this@HomeActivity){
            adapter2.submitData(lifecycle, it)
        }
    }

    private fun setupFlame(){
        mainViewModel.getFlameSeries().observe(this@HomeActivity){
            adapter3.submitData(lifecycle, it)
        }
    }


    private fun setupAction() {
        lifecycleScope.launch {
            delay(1000)
            showLoading(false)
        }
        setupAsura()
        setupAlpha()
        setupFlame()
    }

    private fun showLoading(isLoading: Boolean){ binding?.pbHome?.visibility = if (isLoading) View.VISIBLE else View.GONE }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}