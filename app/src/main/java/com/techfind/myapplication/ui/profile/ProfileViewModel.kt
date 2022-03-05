package com.techfind.myapplication.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techfind.myapplication.local.User
import com.techfind.myapplication.repository.TechfindRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    val techfindRepository = TechfindRepository()
}