package com.techfind.myapplication.ui.delete

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.techfind.myapplication.R
import com.techfind.myapplication.databinding.DeleteFragmentBinding
import com.techfind.myapplication.local.Add_service
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.techfind.myapplication.server.ServiceServer
import com.techfind.myapplication.ui.addservices.AddServiceFragmentDirections

class DeleteFragment : Fragment() {

    private lateinit var deleteBinding: DeleteFragmentBinding
    private lateinit var deleteViewModel: DeleteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        deleteBinding = DeleteFragmentBinding.inflate(inflater, container, false)
        deleteViewModel = ViewModelProvider(this)[DeleteViewModel::class.java]
        return deleteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinner: Spinner = deleteBinding.categorySpinner
        var category = ""
        val lista = resources.getStringArray(R.array.categories_array)
        val adaptador = ArrayAdapter(this.requireContext(),android.R.layout.simple_spinner_item,lista)
        spinner.adapter = adaptador

        spinner.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                category = lista[position].toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                category = ""
            }
        }

        deleteViewModel.findServiceServerDone.observe(viewLifecycleOwner) { result ->
            onFindServiceServerDoneSubscribe(result)
        }

        with(deleteBinding) {
            searchButton.setOnClickListener {
                deleteViewModel.searchService(category)
            }
        }
    }

    private fun onFindServiceServerDoneSubscribe(service: ServiceServer?) {
        if (service == null) {
            Toast.makeText(requireContext(), "Servicio no encontrado", Toast.LENGTH_SHORT).show()
        } else {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Advertencia")
                .setMessage("¿Desea eliminar el servicio "+service.category.toString())
                .setNegativeButton("Cancelar") { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                    deleteViewModel.deleteServiceServer(service)
                    Toast.makeText(requireContext(), "Servicio eliminado exitosamente", Toast.LENGTH_SHORT).show()
                }
                .show()
        }
    }


}