package com.techfind.myapplication.ui.delete

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.techfind.myapplication.local.Add_service
import com.techfind.myapplication.local.repository.ServiceRepository
import com.techfind.myapplication.server.ServiceServer
import com.techfind.myapplication.server.serverrepository.ServiceServerRepository

class DeleteViewModel : ViewModel() {

    val serviceRepository = ServiceRepository()
    val serviceServerRepository = ServiceServerRepository()

    private val findService: MutableLiveData<Add_service> = MutableLiveData()
    val findServiceDone: LiveData<Add_service> = findService

    private val findServiceServer: MutableLiveData<ServiceServer?> = MutableLiveData()
    val findServiceServerDone: LiveData<ServiceServer?> = findServiceServer

    fun searchService(category: String) {
        GlobalScope.launch(Dispatchers.IO) {
            //findBook.postValue(bookRepository.searchBook(nameBook))
            val result = serviceServerRepository.loadServices()
            var isFoundService = false
            for (document in result) {
                val serviceServer: ServiceServer = document.toObject<ServiceServer>()
                if (category == serviceServer.category) {
                    findServiceServer.postValue(serviceServer)
                    isFoundService = true
                }
            }
            if (!isFoundService) findServiceServer.postValue(null)
        }
    }

    fun deleteService(service: Add_service) {
        GlobalScope.launch(Dispatchers.IO) {
            Log.d("2","2")
            serviceRepository.deleteService(service)
        }
    }
    fun deleteServiceServer(service: ServiceServer) {
        GlobalScope.launch(Dispatchers.IO) {
            serviceServerRepository.deleteservice(service)
        }
    }
}