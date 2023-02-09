package rk.enkidu.mangaapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rk.enkidu.mangaapp.databinding.ListImageBinding

class ListImageAdapter(private val listData: List<String>): RecyclerView.Adapter<ListImageAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ListImageBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String){
            Glide.with(itemView.context)
                .load(data)
                .into(binding.ivImage)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListImageAdapter.ViewHolder {
        val binding = ListImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size
}