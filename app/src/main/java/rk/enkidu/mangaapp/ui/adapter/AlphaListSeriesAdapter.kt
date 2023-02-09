package rk.enkidu.mangaapp.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rk.enkidu.mangaapp.data.response.AlphaSeriesItem
import rk.enkidu.mangaapp.databinding.ListSeriesBinding
import rk.enkidu.mangaapp.ui.detail.DetailActivity

class AlphaListSeriesAdapter : PagingDataAdapter<AlphaSeriesItem, AlphaListSeriesAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(private val binding: ListSeriesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AlphaSeriesItem){
            Glide.with(itemView.context)
                .load(data.mangaCover)
                .into(binding.ivSeriesImage)

            val temp = data.mangaTitle?.length
            if(temp!! > 20){
                binding.tvTitleSeriesText.text = data.mangaTitle.substring(0, 20) + "..."
            } else {
                binding.tvTitleSeriesText.text = data.mangaTitle
            }

            binding.tvDateSeriesText.text = "Scrapdate: ${data.scrapeDate}"

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.DETAIL_TYPE, data.type)
                intent.putExtra(DetailActivity.DETAIL_ID, data.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if(data != null){
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AlphaSeriesItem>(){
            override fun areItemsTheSame(oldItem: AlphaSeriesItem, newItem: AlphaSeriesItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: AlphaSeriesItem, newItem: AlphaSeriesItem): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}