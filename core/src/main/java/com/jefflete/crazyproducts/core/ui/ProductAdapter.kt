package com.jefflete.crazyproducts.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jefflete.crazyproducts.core.R
import com.jefflete.crazyproducts.core.databinding.ItemListProductBinding
import com.jefflete.crazyproducts.core.domain.model.Product

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ListViewHolder>() {

    private var listData = ArrayList<Product>()
    var onItemClick: ((Product) -> Unit)? = null

    fun setData(newListData: List<Product>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list_product, parent, false)
    )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListProductBinding.bind(itemView)
        fun bind(data: Product) {
            with(binding) {
                Glide.with(itemView.context).load(data.thumbnail).into(ivThumbnail)
                tvName.text = data.title
                tvPrice.text = itemView.context.getString(R.string.product_price, data.price)
                if (data.isFavorite) {
                    Glide.with(itemView.context).load(R.drawable.baseline_favorite_24)
                        .into(favoriteState)
                } else {
                    Glide.with(itemView.context).load(R.drawable.baseline_favorite_border_24)
                        .into(favoriteState)
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}