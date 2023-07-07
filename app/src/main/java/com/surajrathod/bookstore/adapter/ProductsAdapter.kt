package com.surajrathod.bookstore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.surajrathod.bookstore.R
import com.surajrathod.bookstore.databinding.ProductItemLayoutBinding
import com.surajrathod.bookstore.model.Products

class ProductsAdapter(val products: Products , val navController: NavController? = null) :
    RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    class ProductsViewHolder(val binding: ProductItemLayoutBinding) : ViewHolder(binding.root) {
        val txtProductTitle = binding.txtProductTitle
        val imgProduct = binding.imgProduct
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            ProductItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val p = products[position]

        with(holder) {
            txtProductTitle.text = p.title
            Glide.with(holder.imgProduct.context).load(p.image).into(imgProduct)
            itemView.setOnClickListener {
                if(navController!=null){
                    navController.navigate(R.id.action_booksFragment_to_blankDetailsFragment)
                }
            }
        }
    }
}