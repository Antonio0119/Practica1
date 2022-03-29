package com.techfind.myapplication.ui.delete

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        deleteViewModel.findServiceServerDone.observe(viewLifecycleOwner) { result ->
            onFindServiceServerDoneSubscribe(result)
        }

        with(deleteBinding) {
            searchButton.setOnClickListener {
                deleteViewModel.searchService(categoryEditText.text.toString())
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
                    deleteBinding.categoryEditText.text?.clear()
                    Toast.makeText(requireContext(), "Servicio eliminado exitosamente", Toast.LENGTH_SHORT).show()
                }
                .show()
        }
    }

    private fun onFindServiceDoneSubscribe(service: Add_service?) {
        if (service == null) {
            Toast.makeText(requireContext(), "Servicio no encontrado", Toast.LENGTH_SHORT).show()
        } else {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.warning_title))
                .setMessage(resources.getString(R.string.delete_service_msg, service.category))
                .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                    deleteViewModel.deleteService(service)
                    deleteBinding.categoryEditText.text?.clear()
                }
                .show()
        }

    }

}