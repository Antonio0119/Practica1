package com.techfind.myapplication.server.serverrepository

import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.techfind.myapplication.server.ServiceServer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ServiceServerRepository {
    val db = Firebase.firestore

    fun saveService(
        category: String,
        description: String,
        experience: Int,
        price: Int,
        score: Int
    ) {

        val documentService = db.collection("services").document()

        val service = ServiceServer(
            id = documentService.id,
            category = category,
            description = description,
            experience = experience,
            price = price,
            score = score,
        )

        db.collection("services").document(documentService.id).set(service)
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