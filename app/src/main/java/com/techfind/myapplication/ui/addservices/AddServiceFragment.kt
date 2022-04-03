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

                addServiceViewModel.saveServiceInServer(
                    category,
                    shortDescription,
                    longDescription,
                    yearsExperience,
                    servicePrice
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

