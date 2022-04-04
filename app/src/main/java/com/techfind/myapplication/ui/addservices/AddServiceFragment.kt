package com.techfind.myapplication.ui.addservices

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
import com.techfind.myapplication.databinding.AddServiceFragmentBinding

class AddServiceFragment : Fragment() {

    private lateinit var addServiceBinding: AddServiceFragmentBinding
    private lateinit var addServiceViewModel: AddServiceViewModel


    private lateinit var viewModel: AddServiceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addServiceBinding = AddServiceFragmentBinding.inflate(inflater,container,false)
        addServiceViewModel =ViewModelProvider(this).get(AddServiceViewModel::class.java)
        return addServiceBinding.root
    }

    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddServiceViewModel::class.java)
        // TODO: Use the ViewModel
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinner: Spinner = addServiceBinding.categorySpinner
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


        addServiceViewModel.msgDone.observe(viewLifecycleOwner, { result ->
            onMsgDoneSubscribe(result)
        })

        addServiceViewModel.dataValidated.observe(viewLifecycleOwner, { result ->
            onDataValidatedSubscribe(result,category)
        })

        with(addServiceBinding) {
            saveButton.setOnClickListener {
                addServiceViewModel.validateFields(
                    category,
                    longDescriptionEditText.text.toString(),
                    shortDescriptionEditText.text.toString(),
                    servicePriceEditText.text.toString(),
                    yearsExperienceEditText.text.toString()
                )
            }
        }
    }

    private fun onDataValidatedSubscribe(result: Boolean?, category: String) {
        if (result == true) {
            with(addServiceBinding) {
                val category = category
                val longDescription = longDescriptionEditText.text.toString()
                val shortDescription = shortDescriptionEditText.text.toString()
                val servicePrice = servicePriceEditText.text.toString().toInt()
                val yearsExperience = yearsExperienceEditText.text.toString().toInt()

                val urlPicture = when (category){
                    "Plomería" -> "https://firebasestorage.googleapis.com/v0/b/techfind-1ea2e.appspot.com/o/plomeria.jpg?alt=media&token=ce29d5d3-218f-4314-afae-ea13eeab2d18"
                    "Limpieza" -> "https://firebasestorage.googleapis.com/v0/b/techfind-1ea2e.appspot.com/o/limpieza.jpg?alt=media&token=0038b60d-6cc4-4bfa-9f62-16a9349d1f3e"
                    "Cerrajería" -> "https://firebasestorage.googleapis.com/v0/b/techfind-1ea2e.appspot.com/o/Cerrajeria.jpg?alt=media&token=8015a024-478d-4150-b232-b654b81cab12"
                    "Carpintería" -> "https://firebasestorage.googleapis.com/v0/b/techfind-1ea2e.appspot.com/o/carpinteria.jpg?alt=media&token=89a04498-c36d-481a-a671-7ec094a3e36c"
                    "Control plagas" -> "https://firebasestorage.googleapis.com/v0/b/techfind-1ea2e.appspot.com/o/control%20plagas.jpg?alt=media&token=8442f097-9538-4d3c-9409-4310ec0a5fcf"
                    "Electricidad" -> "https://firebasestorage.googleapis.com/v0/b/techfind-1ea2e.appspot.com/o/electricidad.jpg?alt=media&token=462ab6d5-d9be-4172-a4fe-90d25fe7d982"
                    "Lavandería" -> "https://firebasestorage.googleapis.com/v0/b/techfind-1ea2e.appspot.com/o/lavanderia.jpg?alt=media&token=19b63b54-f358-455d-9ca2-d4f6bccef777"
                    "Mecánica" -> "https://firebasestorage.googleapis.com/v0/b/techfind-1ea2e.appspot.com/o/mecanica.jpg?alt=media&token=802241d1-bdf9-4cd3-945c-49278bc1a628"
                    "Pintura" -> "https://firebasestorage.googleapis.com/v0/b/techfind-1ea2e.appspot.com/o/pintura.jpg?alt=media&token=ee9bef58-1822-416a-a2e9-09ef733630dc"

                    else -> {""}
                }


                addServiceViewModel.saveServiceInServer(
                    category,
                    shortDescription,
                    longDescription,
                    yearsExperience,
                    servicePrice,
                    urlPicture
                )
                findNavController().navigate(AddServiceFragmentDirections.actionAddServiceFragmentToServicesFragment())

            }
        }
    }

    private fun onMsgDoneSubscribe(msg: String?) {
        Toast.makeText(
            requireContext(),
            msg,
            Toast.LENGTH_SHORT
        ).show()
    }

}

