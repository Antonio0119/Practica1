package com.techfind.myapplication.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.techfind.myapplication.databinding.ProfileFragmentBinding
import com.techfind.myapplication.local.User
import com.techfind.myapplication.ui.addservices.AddServiceFragmentDirections
import com.techfind.myapplication.ui.login.LoginActivity
import com.techfind.myapplication.ui.services.ServicesFragmentDirections

class ProfileFragment : Fragment() {

    private lateinit var profileBinding: ProfileFragmentBinding
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileBinding = ProfileFragmentBinding.inflate(inflater, container, false)
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        return profileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileBinding.logoutButton.setOnClickListener {
            goToLoginActivity()

        }

        /*with(profileBinding) {
            logoutButton.setOnClickListener {
                goToLoginActivity()
            }
        }*/


    }

    private fun goToLoginActivity() {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }


}