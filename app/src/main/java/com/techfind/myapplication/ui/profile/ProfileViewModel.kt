package com.techfind.myapplication.ui.profile

import androidx.lifecycle.ViewModel
import com.techfind.myapplication.local.repository.TechfindRepository

class ProfileViewModel : ViewModel() {

    val techfindRepository = TechfindRepository()
}