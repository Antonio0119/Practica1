package com.techfind.myapplication.ui.categorieslist

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

    //private val loadServices : MutableLiveData<ArrayList<Book>> = MutableLiveData()
   // val loadBooksDone: LiveData<ArrayList<Book>> = loadBooks

    private val loadServicesFromServer : MutableLiveData<ArrayList<ServiceServer>> = MutableLiveData()
    val loadServicesFromServerDone: LiveData<ArrayList<ServiceServer>> = loadServicesFromServer

    /*fun loadServices() {
        GlobalScope.launch(Dispatchers.IO) {
            loadServices.postValue(bookRepository.loadBooks())
        }
    }*/

    fun loadServicesFromServer() {
        serviceList.clear()
        GlobalScope.launch(Dispatchers.IO){
            val querySnapshot = serviceServerRepository.loadServices()
            for (document in querySnapshot) {
                val serviceServer: ServiceServer = document.toObject<ServiceServer>()
                serviceList.add(serviceServer)
            }
            loadServicesFromServer.postValue(serviceList)
        }
    }


}