package com.techfind.myapplication.ui.categories

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.techfind.myapplication.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {

    private lateinit var categoriesBinding: FragmentCategoriesBinding
    private lateinit var categoriesViewModel: CategoriesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        categoriesBinding = FragmentCategoriesBinding.inflate(inflater, container, false)
        categoriesViewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        return categoriesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoriesBinding.laundryCardView.setOnClickListener {

            val category = categoriesBinding.laundryTextView.text.toString()
            findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragmentToCategoriesListFragment(category))
        }
    }


}