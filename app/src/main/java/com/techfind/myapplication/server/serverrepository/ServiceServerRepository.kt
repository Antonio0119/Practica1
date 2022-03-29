package com.techfind.myapplication.server.serverrepository

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

    suspend fun saveService(
        category: String,
        short_description: String,
        long_description: String,
        experience: Int,
        price: Int,
    ) {

        val documentService = db.collection("services").document()
        val activeUser = auth.currentUser?.uid
        val service = ServiceServer(
            id = documentService.id,
            user_id = activeUser.toString(),
            category = category,
            short_description = short_description,
            long_description = long_description,
            experience = experience,
            price = price,
        )

        db.collection("services").document(documentService.id).set(service).await()

        val document = db.collection("users").document(activeUser.toString()).collection("services").document()
        db.collection("users").document(activeUser.toString()).collection("services").document(document.id).set(service).await()

    }

    suspend fun loadServices(): QuerySnapshot {
        return withContext(Dispatchers.IO) {
            db.collection("services").get().await()
        }
    }

    fun deleteservice(service: ServiceServer) {
        service.id?.let { id ->
            db.collection("services")
                .document(id)
                .delete()
        }
    }
}