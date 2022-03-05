package com.techfind.myapplication.ui.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techfind.myapplication.local.Add_service
import com.techfind.myapplication.repository.ServiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ServicesViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val servicesRepository = ServiceRepository()

    private val loadServices : MutableLiveData<ArrayList<Add_service>> = MutableLiveData()
    val loadServicesDone: LiveData<ArrayList<Add_service>> = loadServices

    fun loadServices() {
        GlobalScope.launch(Dispatchers.IO) {
            loadServices.postValue(servicesRepository.loadServices())
        }
    }
}
