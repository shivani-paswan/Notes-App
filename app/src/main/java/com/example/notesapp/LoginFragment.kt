package com.example.notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.notesapp.databinding.FragmentLoginBinding
import com.example.notesapp.model.UserRequest
import com.example.notesapp.utiles.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private  val  viewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=   inflater.inflate(R.layout.fragment_login, container, false)
        val login_btn=view.findViewById<Button>(R.id.btn_login)
        val singup=view.findViewById<TextView>(R.id.btn_sign_up)

        singup.setOnClickListener{
            findNavController().navigate(R.id.action_registrationsFragment_to_loginFragment)
        }
        login_btn.setOnClickListener(){
//            viewModel.loginUser(UserRequest("ADMIN@JEENA","CRITICARE@123","0.0.1","06-02-2023","06-02-2023",""))

//            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
        return view

        // Inflate the layout for this fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.userResponseLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
             is NetworkResult.Success->{
                 // token
                 findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
             }
                is NetworkResult.Error->{}
                is NetworkResult.Loading -> {}
            }
        })
    }
}
