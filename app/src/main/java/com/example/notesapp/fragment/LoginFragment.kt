package com.example.notesapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.notesapp.AuthViewModel
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentLoginBinding
import com.example.notesapp.databinding.FragmentRegistrationsBinding
import com.example.notesapp.model.UserRequest
import com.example.notesapp.utiles.NetworkResult
import com.example.notesapp.utiles.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    lateinit var binding:FragmentLoginBinding
    private  val  viewModel by viewModels<AuthViewModel>()
     @Inject
    lateinit var  tokenManager:TokenManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentLoginBinding.bind(view)

        binding.btnLogin.setOnClickListener{
            val validationResult=validateUserInput()
            if(validationResult.first){
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
              viewModel.registerUser(UserRequest("eve.holt@reqres.in","cityslicka"))

            }else{
                binding.txtError.text=validationResult.second
            }

        }

        binding.btnSignUp.setOnClickListener{
            val validationResult=validateUserInput()
            if(validationResult.first){
                findNavController().navigate(R.id.action_registrationsFragment_to_loginFragment)
//                viewModel.registerUser(UserRequest("eve.holt@reqres.in","pistol"))

            }else{
                binding.txtError.text=validationResult.second
            }
        }
        bindObserver()



    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view=   inflater.inflate(R.layout.fragment_login, container, false)
//        val login_btn=view.findViewById<Button>(R.id.btn_login)
//        val singup=view.findViewById<TextView>(R.id.btn_sign_up)
//        val email=view.findViewById<EditText>(R.id.txt_email)
//        val password=view.findViewById<EditText>(R.id.txt_password)
//        singup.setOnClickListener{
//            findNavController().navigate(R.id.action_registrationsFragment_to_loginFragment)
//        }
//        login_btn.setOnClickListener(){
////            viewModel.loginUser(UserRequest("ADMIN@JEENA","CRITICARE@123","0.0.1","06-02-2023","06-02-2023",""))
//
//            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
//
//
//        }
//        return view
//
//        // Inflate the layout for this fragment
//    }

    private fun getUserRequest(): UserRequest {

        val emaiAddress=binding.txtEmail.text.toString()
        val password=binding.txtPassword.text.toString()
        return UserRequest(emaiAddress,password)

    }

    private  fun validateUserInput(): Pair<Boolean, String> {
        val userRequest=getUserRequest()
        return  viewModel.validateCredential(userRequest.email,userRequest.password)
    }
    private fun bindObserver() {
        viewModel.userResponseLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is NetworkResult.Success->{
                    // token
                    tokenManager.saveToken(it.data !!.token)

                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                }
                is NetworkResult.Error->{}
                is NetworkResult.Loading -> {}
            }
        })
    }


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewModel.userResponseLiveData.observe(viewLifecycleOwner, Observer {
//            when(it){
//             is NetworkResult.Success->{
//                 // token
//                 findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
//             }
//                is NetworkResult.Error->{}
//                is NetworkResult.Loading -> {}
//            }
//        })
//    }
}
