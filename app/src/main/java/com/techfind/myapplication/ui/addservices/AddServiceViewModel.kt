package com.techfind.myapplication.ui.addservices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.techfind.myapplication.local.repository.ServiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddServiceViewModel : ViewModel() {

    private val serviceRepository = ServiceRepository()

    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg

    private val dataValidate: MutableLiveData<Boolean> = MutableLiveData()
    val dataValidated: LiveData<Boolean> = dataValidate

    fun validateFields(category: String, long_description: String, short_description: String, service_price: String, years_experience: String) {
        if (category.isEmpty() || long_description.isEmpty() || short_description.isEmpty() || service_price.isEmpty() || years_experience.isEmpty()) {
            msg.value = "Debe ingresar todos los datos"
        } else {
            dataValidate.value = true
        }
    }

    fun saveService(
        category: String,
        long_description: String,
        short_description: String,
        service_price: Int,
        years_experience: Int
    ){
        GlobalScope.launch(Dispatchers.IO) {
            serviceRepository.saveService(category,long_description,short_description,service_price,years_experience)
        }
    }
}
