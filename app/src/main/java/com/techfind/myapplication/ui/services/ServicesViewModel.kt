package com.techfind.myapplication.ui.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.techfind.myapplication.server.ServiceServer
import com.techfind.myapplication.server.serverrepository.ServiceServerRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ServicesViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val serviceServerRepository = ServiceServerRepository()

    private var servicesList: ArrayList<ServiceServer> = ArrayList()

    private val loadServicesFromServer : MutableLiveData<ArrayList<ServiceServer>> = MutableLiveData()
    val loadServicesFromServerDone: LiveData<ArrayList<ServiceServer>> = loadServicesFromServer

    val auth = Firebase.auth
    val activeUser = auth.currentUser?.uid

    fun loadServicesFromServer() {
        servicesList.clear()
        GlobalScope.launch(Dispatchers.IO){
            val querySnapshot = serviceServerRepository.loadServices()
            for (document in querySnapshot) {
                val serviceServer: ServiceServer = document.toObject<ServiceServer>()
                if (serviceServer.user_id.toString() == activeUser.toString()) {
                    servicesList.add(serviceServer)
                }
            }
            loadServicesFromServer.postValue(servicesList)
        }
    }
}