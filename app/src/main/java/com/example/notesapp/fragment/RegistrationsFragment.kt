package com.example.notesapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.notesapp.AuthViewModel
import com.example.notesapp.R
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



        binding.btnLogin.setOnClickListener{
            val validationResult=validateUserInput()
            if(validationResult.first){
//                findNavController().navigate(R.id.action_registrationsFragment_to_loginFragment)
//              viewModel.registerUser(UserRequest("eve.holt@reqres.in","pistol"))

            }else{
                binding.txtError.text=validationResult.second
            }

        }

        binding.btnSignUp.setOnClickListener{
            findNavController().navigate(R.id.action_registrationsFragment_to_mainFragment)
        }
         bindObserver()
    }

    private fun getUserRequest():UserRequest{
        val emaiAddress=binding.txtEmail.text.toString()
        val password=binding.txtPassword.text.toString()
        val username=binding.txtUsername.text.toString()
        return UserRequest(emaiAddress,password,username)

    }
@SuppressLint("SuspiciousIndentation")
private  fun validateUserInput(): Pair<Boolean, String> {
  val userRequest=getUserRequest()
    return  viewModel.validateCredential(userRequest.email,userRequest.password,userRequest.username)
}
    private fun bindObserver() {
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