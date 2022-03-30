package com.techfind.myapplication.ui.profile

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.techfind.myapplication.databinding.ProfileFragmentBinding
import com.techfind.myapplication.ui.login.LoginActivity

class ProfileFragment : Fragment() {

    private lateinit var profileBinding: ProfileFragmentBinding
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

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

        profileViewModel.msgDone.observe(viewLifecycleOwner) { result ->
            Toast.makeText(requireContext(),result.toString(),Toast.LENGTH_SHORT).show()
        }

        profileViewModel.statusDone.observe(viewLifecycleOwner){ result ->
            changeInformationInServer(result)
        }
        auth = Firebase.auth
        with(profileBinding) {
            logoutButton.setOnClickListener {
                auth.signOut()
                goToLoginActivity()
            }
            saveButton.setOnClickListener {
                val name = editnameEditText.text.toString()
                val email = editemailEditText.text.toString()
                val password = editpasswordEditText.text.toString()
                val document = editdocumentEditText.text.toString()
                val number = editnumberEditText.text.toString()
                profileViewModel.validation(name,email,password,document,number)
            }
        }
    }

    private fun changeInformationInServer(result: ArrayList<String>?) {
        if (result != null) {
            for (element in result) {
                if (element == "name"){
                    db.collection("users")
                        .document(auth.currentUser?.uid.toString())
                        .update("name"
                            ,profileBinding.editnameEditText.text.toString())
                }
                if (element == "email"){
                    val email = profileBinding.editemailEditText.text.toString()
                    // need to sign user in immediately before updating the email
                    auth.signInWithEmailAndPassword("currentEmail","currentPassword")
                        .addOnCompleteListener() { task ->
                            if (task.isSuccessful) {
                                // Sign in success now update email
                                auth.currentUser!!.updateEmail(email)
                                    .addOnCompleteListener{ task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(requireContext(),"Se ha podido cambiar el correo exitosamente ",Toast.LENGTH_SHORT).show()
                                        }else{
                                            Toast.makeText(requireContext(),"No se ha podido cambiar el correo ",Toast.LENGTH_SHORT).show()
                                        }
                                    }
                            } else {
                                // sign in failed
                            }
                        }

                    db.collection("users")
                        .document(auth.currentUser?.uid.toString())
                        .update("email"
                            ,profileBinding.editemailEditText.text.toString())

                }
                if (element == "password"){
                    auth.currentUser?.updatePassword(
                        profileBinding.editpasswordEditText.text.toString())
                    db.collection("users")
                        .document(auth.currentUser?.uid.toString())
                        .update("password"
                            ,profileBinding.editpasswordEditText.text.toString())
                }
                if (element == "document"){
                    db.collection("users")
                        .document(auth.currentUser?.uid.toString())
                        .update("document"
                            ,profileBinding.editdocumentEditText.text.toString().toInt())
                }
                if (element == "number"){
                    db.collection("users")
                        .document(auth.currentUser?.uid.toString())
                        .update("cel_number"
                            ,profileBinding.editnumberEditText.text.toString().toInt())
                }
            }
        }
        else{
            Log.d("1","Vacio")
        }
    }

    private fun goToLoginActivity() {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }


}