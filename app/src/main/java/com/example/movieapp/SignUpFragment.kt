package com.example.movieapp

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.databinding.FragmentSignUpBinding
import com.example.movieapp.viewmodel.AuthState
import com.example.movieapp.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val authViewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignUpBinding.bind(view)

        observeAuth()

        binding.tvSkip.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
        }

        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (!validateInput(email, password)) return@setOnClickListener

            authViewModel.signUp(email, password)
        }

        binding.tvGoToSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            binding.etEmail.error = "Enter email"
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.error = "Enter valid email"
            return false
        }

        if (password.isEmpty()) {
            binding.etPassword.error = "Enter password"
            return false
        }

        if (password.length < 6) {
            binding.etPassword.error = "Password must be at least 6 characters"
            return false
        }

        return true
    }

    private fun observeAuth() {
        authViewModel.authState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is AuthState.Loading -> {
                    binding.btnSignUp.isEnabled = false
                    binding.btnSignUp.text = "Signing up..."
                }
                is AuthState.Success -> {
                    binding.btnSignUp.isEnabled = true
                    binding.btnSignUp.text = "Sign up"
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
                }
                is AuthState.Error -> {
                    binding.btnSignUp.isEnabled = true
                    binding.btnSignUp.text = "Sign up"
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    binding.btnSignUp.isEnabled = true
                    binding.btnSignUp.text = "Sign up"
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}