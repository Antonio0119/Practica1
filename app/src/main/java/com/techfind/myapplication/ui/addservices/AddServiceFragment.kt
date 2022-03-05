package com.techfind.myapplication.ui.addservices

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.techfind.myapplication.databinding.AddServiceFragmentBinding
import com.techfind.myapplication.ui.services.ServicesFragmentDirections

class AddServiceFragment : Fragment() {

    private lateinit var addServiceBinding: AddServiceFragmentBinding
    private lateinit var addServiceViewModel: AddServiceViewModel

    companion object {
        fun newInstance() = AddServiceFragment()
    }

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

        addServiceViewModel.msgDone.observe(viewLifecycleOwner, { result ->
            onMsgDoneSubscribe(result)
        })

        addServiceViewModel.dataValidated.observe(viewLifecycleOwner, { result ->
            onDataValidatedSubscribe(result)
        })

        with(addServiceBinding) {
            saveButton.setOnClickListener {
                addServiceViewModel.validateFields(
                    categoryEditText.text.toString(),
                    longDescriptionEditText.text.toString(),
                    shortDescriptionEditText.text.toString(),
                    servicePriceEditText.text.toString(),
                    yearsExperienceEditText.text.toString()
                )
                findNavController().navigate(AddServiceFragmentDirections.actionAddServiceFragmentToServicesFragment())
            }
        }
    }

    private fun onDataValidatedSubscribe(result: Boolean?) {
        with(addServiceBinding) {
            val category = categoryEditText.text.toString()
            val longDescription = longDescriptionEditText.text.toString()
            val shortDescription = shortDescriptionEditText.text.toString()
            val servicePrice = servicePriceEditText.text.toString().toInt()
            val yearsExperience = yearsExperienceEditText.text.toString().toInt()

            addServiceViewModel.saveService(category, longDescription, shortDescription, servicePrice, yearsExperience)
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

