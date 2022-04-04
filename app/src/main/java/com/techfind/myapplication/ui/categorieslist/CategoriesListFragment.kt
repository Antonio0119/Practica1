package com.techfind.myapplication.ui.categorieslist

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.techfind.myapplication.databinding.CategorieslistFragmentBinding
import com.techfind.myapplication.server.ServiceServer
import com.techfind.myapplication.server.User
import com.techfind.myapplication.server.serverrepository.UserServerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoriesListFragment : Fragment() {

    private lateinit var categorieslistBinding: CategorieslistFragmentBinding
    private lateinit var categorieslistViewModel: CategoriesListViewModel
    private lateinit var categorieslistAdapter: CategoriesListAdapter
   // private var booksList: ArrayList<Book> = ArrayList()    //room
    private var servicesListFromServer: ArrayList<ServiceServer> = ArrayList()    //firebase
    val db = Firebase.firestore

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

        categorieslistBinding.pruebaTextView.text = args.category

        categorieslistViewModel.loadServicesFromServerDone.observe(viewLifecycleOwner) { result ->
            onLoadServicesFromServerDoneSubscribe(result)
        }

        categorieslistViewModel.loadServicesFromServer(args.category)
        categorieslistAdapter = CategoriesListAdapter(servicesListFromServer, onItemClicked = {onServiceClicked(it)})

        categorieslistBinding.servicesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@CategoriesListFragment.requireContext())
            adapter = categorieslistAdapter
            setHasFixedSize(false)
        }


    }

    private fun onServiceClicked(service: ServiceServer) {
        GlobalScope.launch(Dispatchers.IO) {
            val user = UserServerRepository()
            val uid = service.user_id.toString()
            val phone = user.getUserData(uid)
            val i = Intent(Intent.ACTION_VIEW)
            Log.d("jaja",phone.toString())
            val url = "https://api.whatsapp.com/send?phone=57".plus(phone)
            i.setPackage("com.whatsapp")
            i.setData(Uri.parse(url))
            startActivity(i)
            //servicesListFromServer.get(servicesListFromServer.indexOf(service)).user_id

        }
    }

    private fun onLoadServicesFromServerDoneSubscribe(servicesListFromServerLoaded: ArrayList<ServiceServer>) { //firebase
        servicesListFromServer = servicesListFromServerLoaded
        categorieslistAdapter.appendItems(servicesListFromServer)
    }




}