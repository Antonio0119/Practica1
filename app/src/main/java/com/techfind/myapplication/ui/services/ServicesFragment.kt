package com.techfind.myapplication.ui.services

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.techfind.myapplication.databinding.ServicesFragmentBinding
import com.techfind.myapplication.server.ServiceServer

class ServicesFragment : Fragment() {

    private lateinit var servicesBinding: ServicesFragmentBinding
    private lateinit var servicesViewModel: ServicesViewModel
    private lateinit var servicesAdapter: ServicesAdapter
    private var servicesListFromServer: ArrayList<ServiceServer> = ArrayList()    //firebase


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

        servicesViewModel.loadServicesFromServerDone.observe(viewLifecycleOwner) {result->
            onLoadServicesFromServerDoneSubscribe(result)
        }


        servicesViewModel.loadServicesFromServer()

        servicesAdapter = ServicesAdapter(servicesListFromServer)

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


    private fun onLoadServicesFromServerDoneSubscribe(serviceListFromServerLoaded: ArrayList<ServiceServer>) {
        servicesListFromServer = serviceListFromServerLoaded
        servicesAdapter.appendItems(servicesListFromServer)
    }


}