package com.techfind.myapplication.server.serverrepository

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.techfind.myapplication.server.ServiceServer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ServiceServerRepository {
    val db = Firebase.firestore
    val auth = Firebase.auth
    val activeUser = auth.currentUser?.uid

    suspend fun saveService(
        category: String,
        short_description: String,
        long_description: String,
        experience: Int,
        price: Int,
        urlPicture: String
    ) {
        val documentService = db.collection("services").document()
        val service = ServiceServer(
            id = documentService.id,
            user_id = activeUser.toString(),
            category = category,
            short_description = short_description,
            long_description = long_description,
            experience = experience,
            price = price,
            urlPicture = urlPicture
        )
        Log.d("User",auth.currentUser?.email.toString())
        db.collection("services").document(documentService.id).set(service).await()
        Log.d("User",service.user_id.toString())
        db.collection("users").document(service.user_id.toString()).collection("services").document(documentService.id).set(service).await()

    }

    suspend fun loadServices(): QuerySnapshot {
        return withContext(Dispatchers.IO) {
            db.collection("services").get().await()
        }
    }

    suspend fun deleteservice(service: ServiceServer) {
        service.id?.let { id ->
            db.collection("services")
                .document(id)
                .delete()
                .await()

            db.collection("users")
                .document(activeUser.toString())
                .collection("services")
                .document(id)
                .delete()
                .await()
        }

    }
}