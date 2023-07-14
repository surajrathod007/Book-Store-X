package com.surajrathod.bookstore.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.surajrathod.bookstore.BaseActivity
import com.surajrathod.bookstore.R
import com.surajrathod.bookstore.adapter.ProductsAdapter
import com.surajrathod.bookstore.databinding.FragmentBooksBinding
import com.surajrathod.bookstore.ui.activities.HomeActivity
import com.surajrathod.bookstore.utils.Result
import com.surajrathod.bookstore.viewmodel.BooksViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException


@AndroidEntryPoint
class BooksFragment : Fragment() {

    lateinit var bindings: FragmentBooksBinding
    lateinit var vm: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_books, container, false)
        bindings = FragmentBooksBinding.bind(view)
        vm = ViewModelProvider(this).get(BooksViewModel::class.java)
        loadProducts()
        setupObservers()
        (activity as HomeActivity).setToolBarTitle("Products")
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        return view
    }

    private fun setupObservers() {
        vm.products.observe(viewLifecycleOwner) {
            when(it){
                is Result.Success->{
                    bindings.rvBooks.adapter = ProductsAdapter(it.data!!,findNavController())
                }
                is Result.Failure->{
                    Toast.makeText(requireContext(),it.msg,Toast.LENGTH_LONG).show()
                }
                is Result.Loading->{

                }
            }
        }
        vm.loading.observe(viewLifecycleOwner){
            if(it){
                (activity as HomeActivity).showProgress()
            }else{
                (activity as HomeActivity).hideProgress()
            }
        }
//        vm._localProducts.observe(viewLifecycleOwner){
//            (activity as BaseActivity).showToast("${it?.get(0)?.toString()}",true)
//        }
        vm.msg.observe(viewLifecycleOwner){
            (activity as BaseActivity).showToast("$it",true)
        }
        vm._localProducts.observe(viewLifecycleOwner){
            (activity as BaseActivity).showToast("${it?.get(0)?.toString()}",true)
        }
    }

    private fun loadProducts() {
        vm.loadProducts()
    }


}