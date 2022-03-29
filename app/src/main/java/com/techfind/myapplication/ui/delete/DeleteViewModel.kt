package com.techfind.myapplication.ui.delete

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.techfind.myapplication.local.Add_service
import com.techfind.myapplication.local.repository.ServiceRepository

class DeleteViewModel : ViewModel() {

    val serviceRepository = ServiceRepository()

    private val findService: MutableLiveData<Add_service> = MutableLiveData()
    val findServiceDone: LiveData<Add_service> = findService

    fun searchService(category: String) {
        GlobalScope.launch(Dispatchers.IO) {
            Log.d("1","1")
            findService.postValue(serviceRepository.searchService(category))
        }
    }

    fun deleteService(service: Add_service) {
        GlobalScope.launch(Dispatchers.IO) {
            Log.d("2","2")
            serviceRepository.deleteService(service)
        }
    }
}