package com.surajrathod.bookstore.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.surajrathod.bookstore.MainActivity
import com.surajrathod.bookstore.R
import com.surajrathod.bookstore.databinding.FragmentLoginBinding
import com.surajrathod.bookstore.ui.activities.HomeActivity
import com.surajrathod.bookstore.utils.DummyPreferenceHelper
import com.surajrathod.bookstore.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var vm: AuthViewModel
    @Inject
    lateinit var prefs: DummyPreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(this).get(AuthViewModel::class.java)


        binding.btnSignUp.setOnClickListener {
            (activity as MainActivity).showAlert("Do you want to register?"){
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
        binding.btnLogin.setOnClickListener {
            if (vm.isLogin(
                    binding.edEmail.text.toString(),
                    binding.edPassword.text.toString().toInt()
                )
            ) {
                prefs.setUserLoginStatus(true)
                Toast.makeText(requireContext(), "Logged In", Toast.LENGTH_SHORT).show()
                startActivity(Intent(requireContext(), HomeActivity::class.java))
            }
        }
        setupToolBar()
        return binding.root
    }

    private fun setupToolBar() {
        with(activity as MainActivity){
            setToolBarTitle("Login")
        }
    }

}