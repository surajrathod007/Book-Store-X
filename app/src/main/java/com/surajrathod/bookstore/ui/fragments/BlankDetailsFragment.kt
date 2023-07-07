package com.surajrathod.bookstore.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.surajrathod.bookstore.R
import com.surajrathod.bookstore.databinding.FragmentBlankDetailsBinding
import com.surajrathod.bookstore.model.ProductItem
import com.surajrathod.bookstore.ui.activities.HomeActivity

class BlankDetailsFragment : Fragment() {

    private val args by navArgs<BlankDetailsFragmentArgs>()
    lateinit var binding : FragmentBlankDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_blank_details, container, false)
        binding = FragmentBlankDetailsBinding.bind(view)
        setupProduct(args.product)
        setupToolBar()

        return binding.root
    }

    private fun setupToolBar() {
        var toolbar = (activity as HomeActivity).toolbar
        (activity as HomeActivity).setToolBarTitle(args.product.category)
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //(activity as HomeActivity).setSupportActionBar(toolbar)
    }

    private fun setupProduct(product: ProductItem) {
        with(binding){
            txtProductDetailsTitle.text = product.title
            txtProductDetailsDesc.text = product.description
            Glide.with(requireContext()).load(product.image).into(imgProductDetail)
        }
    }



}