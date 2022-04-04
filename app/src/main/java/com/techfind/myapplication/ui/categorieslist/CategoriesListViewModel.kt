package com.techfind.myapplication.ui.categorieslist

import android.content.pm.PackageManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.toObject
import com.techfind.myapplication.server.ServiceServer
import com.techfind.myapplication.server.serverrepository.ServiceServerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoriesListViewModel : ViewModel() {
    //val bookRepository = BookRepository()
    val serviceServerRepository = ServiceServerRepository()

    private var serviceList: ArrayList<ServiceServer> = ArrayList()

    private val loadServicesFromServer : MutableLiveData<ArrayList<ServiceServer>> = MutableLiveData()
    val loadServicesFromServerDone: LiveData<ArrayList<ServiceServer>> = loadServicesFromServer


    fun loadServicesFromServer(category: String) {
        serviceList.clear()
        GlobalScope.launch(Dispatchers.IO){
            val querySnapshot = serviceServerRepository.loadServices()
            for (document in querySnapshot) {
                val serviceServer: ServiceServer = document.toObject<ServiceServer>()
                if (serviceServer.category.toString() == category) {
                    serviceList.add(serviceServer)
                }
            }
            loadServicesFromServer.postValue(serviceList)
        }
    }




}