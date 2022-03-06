package com.techfind.myapplication.repository

import com.techfind.myapplication.Techfind
import com.techfind.myapplication.local.Add_service
import com.techfind.myapplication.local.Add_serviceDAO
import java.sql.Types.NULL

class ServiceRepository {

    suspend fun saveService(
        category: String,
        long_description: String,
        short_description: String,
        service_price: Int,
        years_experience: Int

    ) {
        val service = Add_service(
            service_id = NULL,
            category = category,
            long_description = long_description,
            short_description = short_description,
            service_price = service_price,
            years_experience = years_experience,
        )

        val addServiceDAO: Add_serviceDAO = Techfind.database.Add_serviceDAO()
        addServiceDAO.Service(service)
    }

    suspend fun loadServices(): ArrayList<Add_service> {
        val serviceDao: Add_serviceDAO = Techfind.database.Add_serviceDAO()
        val servicesList: ArrayList<Add_service> = serviceDao.loadServices() as ArrayList<Add_service>
        return servicesList
    }

    suspend fun searchService(category: String): Add_service {
        val serviceDao: Add_serviceDAO = Techfind.database.Add_serviceDAO()
        val service = serviceDao.searchService(category)
        return service
    }

    suspend fun deleteService(service: Add_service) {
        val serviceDao: Add_serviceDAO = Techfind.database.Add_serviceDAO()
        serviceDao.deleteService(service)
    }
}