package com.techfind.myapplication.ui.services

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.techfind.myapplication.local.Add_service
import com.techfind.myapplication.databinding.ServicesFragmentBinding

class ServicesFragment : Fragment() {

    private lateinit var servicesBinding: ServicesFragmentBinding
    private lateinit var servicesViewModel: ServicesViewModel
    private lateinit var servicesAdapter: ServicesAdapter
    private var servicesList: ArrayList<Add_service> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        servicesBinding = ServicesFragmentBinding.inflate(inflater, container, false)
        servicesViewModel = ViewModelProvider(this)[ServicesViewModel::class.java]
        return servicesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        servicesViewModel.loadServicesDone.observe(viewLifecycleOwner, {result->
            onLoadServicesDoneSubscribe(result)
        })

        servicesViewModel.loadServices()

        servicesAdapter = ServicesAdapter(servicesList)

        servicesBinding.booksRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ServicesFragment.requireContext())
            adapter = servicesAdapter
            setHasFixedSize(false)
        }

        servicesBinding.newButton.setOnClickListener {
            findNavController().navigate(ServicesFragmentDirections.actionServicesFragmentToAddServiceFragment())
        }

        servicesBinding.deleteButton.setOnClickListener {
            findNavController().navigate(ServicesFragmentDirections.actionServicesFragmentToDeleteFragment())
        }
    }

    private fun onLoadServicesDoneSubscribe(servicesListLoaded: ArrayList<Add_service>) {
        servicesList = servicesListLoaded
        servicesAdapter.appendItems(servicesList)
    }

}