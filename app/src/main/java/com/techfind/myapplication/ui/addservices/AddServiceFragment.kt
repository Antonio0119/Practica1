package com.techfind.myapplication.ui.addservices

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techfind.myapplication.R

class AddServiceFragment : Fragment() {

    companion object {
        fun newInstance() = AddServiceFragment()
    }

    private lateinit var viewModel: AddServiceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_service_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddServiceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}