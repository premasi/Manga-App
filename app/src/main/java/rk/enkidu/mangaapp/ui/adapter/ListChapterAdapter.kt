package rk.enkidu.mangaapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rk.enkidu.mangaapp.data.response.ChapterSeriesItem
import rk.enkidu.mangaapp.databinding.ListChapterBinding

class ListChapterAdapter: RecyclerView.Adapter<ListChapterAdapter.ViewHolder>() {

    private val list = ArrayList<ChapterSeriesItem>()

    fun setListChapter(data : List<ChapterSeriesItem>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ListChapterBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ChapterSeriesItem){
            binding.tvNumChapter.text = data.chapterNumber
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListChapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(list[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = list.size

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ChapterSeriesItem)
    }

}