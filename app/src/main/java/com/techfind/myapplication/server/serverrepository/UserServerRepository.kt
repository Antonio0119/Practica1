package com.techfind.myapplication.server.serverrepository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.techfind.myapplication.server.User
import kotlinx.coroutines.tasks.await

class UserServerRepository {
    val db = Firebase.firestore
    val auth = Firebase.auth
    val activeUser = auth.currentUser?.uid

    suspend fun saveUser(
        name: String,
        email: String,
        password: String,
        document: Int,
        cel_number: Int,
    ) {


        val documentUser = db.collection("users").document()
        val user = User(
            uid = documentUser.id,
            name = name,
            email = email,
            password = password,
            document = document,
            cel_number = cel_number,
        )
        db.collection("users").document(documentUser.id).set(user).await()
    }

}