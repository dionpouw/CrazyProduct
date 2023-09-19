package com.jefflete.crazyproducts.core.utils

import androidx.recyclerview.widget.DiffUtil
import com.jefflete.crazyproducts.core.domain.model.Product

class ProductDiffCallback(
    private val oldList: ArrayList<Product>,
    private val newList: List<Product>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].productId == newList[newItemPosition].productId

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}