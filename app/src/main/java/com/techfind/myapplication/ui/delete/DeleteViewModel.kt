package com.techfind.myapplication.ui.delete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.techfind.myapplication.local.Add_service
import com.techfind.myapplication.repository.ServiceRepository

class DeleteViewModel : ViewModel() {

    val serviceRepository = ServiceRepository()

    private val findService: MutableLiveData<Add_service> = MutableLiveData()
    val findServiceDone: LiveData<Add_service> = findService

    fun searchService(category: String) {
        GlobalScope.launch(Dispatchers.IO) {
            findService.postValue(serviceRepository.searchService(category))
        }
    }

    fun deleteService(service: Add_service) {
        GlobalScope.launch(Dispatchers.IO) {
            serviceRepository.deleteService(service)
        }
    }
}