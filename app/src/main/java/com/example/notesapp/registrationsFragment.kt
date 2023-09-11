package com.example.notesapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.notesapp.databinding.FragmentRegistrationsBinding
import com.example.notesapp.model.UserRequest
import com.example.notesapp.utiles.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class registrationsFragment : Fragment(R.layout.fragment_registrations) {
    private lateinit var binding: FragmentRegistrationsBinding
    private  val  viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentRegistrationsBinding.bind(view)
//        binding.btn_login.setOnClickListener {
//        findNavController().navigate(R.id.action_registrationsFragment_to_loginFragment)
        viewModel.userResponseLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is NetworkResult.Success->{
                    // token
                    findNavController().navigate(R.id.action_registrationsFragment_to_loginFragment)
                }
                is NetworkResult.Error->{}
                is NetworkResult.Loading -> {}
            }
        })

//        }
        binding.btnLogin.setOnClickListener{
//            findNavController().navigate(R.id.action_registrationsFragment_to_loginFragment)
        viewModel.registerUser(UserRequest("eve.holt@reqres.in","pistol"))
        }

        binding.btnSignUp.setOnClickListener{
            findNavController().navigate(R.id.action_registrationsFragment_to_mainFragment)
        }
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding=null
//    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//     val view=   inflater.inflate(R.layout.fragment_registrations, container, false)
//        val back_txt=view.findViewById<TextView>(R.id.text_reDirect)
//        back_txt.setOnClickListener(){
//            findNavController().navigate(R.id.action_registrationsFragment_to_loginFragment)
//        }
//        return view
//
//    }

}