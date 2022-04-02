package com.techfind.myapplication.ui.categories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.toObject
import com.techfind.myapplication.local.Add_service
import com.techfind.myapplication.local.repository.ServiceRepository
import com.techfind.myapplication.server.ServiceServer
import com.techfind.myapplication.server.serverrepository.ServiceServerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {
    val serviceRepository = ServiceRepository()
    val serviceServerRepository = ServiceServerRepository()

    private val findService: MutableLiveData<Add_service> = MutableLiveData()
    val findServiceDone: LiveData<Add_service> = findService

    private val findServiceServer: MutableLiveData<ServiceServer?> = MutableLiveData()
    val findServiceServerDone: LiveData<ServiceServer?> = findServiceServer



}