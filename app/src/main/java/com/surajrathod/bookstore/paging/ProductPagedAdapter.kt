package com.surajrathod.bookstore.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.surajrathod.bookstore.adapter.ProductsAdapter
import com.surajrathod.bookstore.databinding.ProductItemLayoutBinding
import com.surajrathod.bookstore.model.ProductItem
import com.surajrathod.bookstore.ui.fragments.BooksFragmentDirections

class ProductPagedAdapter : PagingDataAdapter<ProductItem,ProductPagedAdapter.ProductsPagedViewHolder>(
    COMPARATOR){

    class ProductsPagedViewHolder(private val binding: ProductItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val txtProductTitle = binding.txtProductTitle
        val imgProduct = binding.imgProduct
    }

    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<ProductItem>(){
            override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
                return oldItem.id ==newItem.id
            }

            override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
                return oldItem==newItem
            }

        }
    }

    override fun onBindViewHolder(holder: ProductsPagedViewHolder, position: Int) {
        val p = getItem(position)
        with(holder) {
            if (p != null) {
                txtProductTitle.text = p.title
            }
            if (p != null) {
                Glide.with(holder.imgProduct.context).load(p.image).into(imgProduct)
            }

            itemView.setOnClickListener {
//                val action = BooksFragmentDirections.actionBooksFragmentToBlankDetailsFragment(p)
//                if(navController!=null){
//                    navController.navigate(action)
//                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsPagedViewHolder {
        return ProductsPagedViewHolder(
            ProductItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}