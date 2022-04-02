package com.techfind.myapplication.ui.categorieslist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.techfind.myapplication.databinding.CategorieslistFragmentBinding
import com.techfind.myapplication.server.ServiceServer

class CategoriesListFragment : Fragment() {

    private lateinit var categorieslistBinding: CategorieslistFragmentBinding
    private lateinit var categorieslistViewModel: CategoriesListViewModel
    private lateinit var categorieslistAdapter: CategoriesListAdapter
   // private var booksList: ArrayList<Book> = ArrayList()    //room
    private var servicesListFromServer: ArrayList<ServiceServer> = ArrayList()    //firebase

    private val args: CategoriesListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        categorieslistBinding = CategorieslistFragmentBinding.inflate(inflater, container, false)
        categorieslistViewModel = ViewModelProvider(this)[CategoriesListViewModel::class.java]
        return categorieslistBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //throw RuntimeException("Test Crash") // Force a crash
      //  pruebaViewModel.loadBooksDone.observe(viewLifecycleOwner) { result ->
       //     onLoadBooksDoneSubscribe(result)
       // }

        categorieslistBinding.pruebaTextView.text = args.category

        categorieslistViewModel.loadServicesFromServerDone.observe(viewLifecycleOwner) { result ->
            onLoadServicesFromServerDoneSubscribe(result)
        }

        //listViewModel.loadBooks()
        categorieslistViewModel.loadServicesFromServer()
        categorieslistAdapter = CategoriesListAdapter(servicesListFromServer)

        categorieslistBinding.servicesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@CategoriesListFragment.requireContext())
            adapter = categorieslistAdapter
            setHasFixedSize(false)
        }


    }

    /*private fun onServiceItemClicked(service: ServiceServer) {
        findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragmentToPruebaFragment(service))
    }*/

    private fun onLoadServicesFromServerDoneSubscribe(servicesListFromServerLoaded: ArrayList<ServiceServer>) { //firebase
        servicesListFromServer = servicesListFromServerLoaded
        categorieslistAdapter.appendItems(servicesListFromServer)
    }




}