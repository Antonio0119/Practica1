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

        with(categoriesBinding) {
            laundryCardView.setOnClickListener {
                val category = categoriesBinding.laundryTextView.text.toString()
                gotolist(category)
            }
            carpentryCardView.setOnClickListener {
                val category = carpentryTextView.text.toString()
                gotolist(category)
            }
            locksmithCardView.setOnClickListener {
                val category = locksmithTextView.text.toString()
                gotolist(category)
            }
            pestcontrolCardView.setOnClickListener {
                val category = pestcontrolTextView.text.toString()
                gotolist(category)
            }
            electricianCardView.setOnClickListener {
                val category = electricianTextView.text.toString()
                gotolist(category)
            }
            plumbingCardView.setOnClickListener {
                val category = plumbingTextView.text.toString()
                gotolist(category)
            }
            cleaningCardView.setOnClickListener {
                val category = cleaningTextView.text.toString()
                gotolist(category)
            }
            paintCardView.setOnClickListener {
                val category = paintTextView.text.toString()
                gotolist(category)
            }
            mechanicCardView.setOnClickListener {
                val category = mechanicTextView.text.toString()
                gotolist(category)
            }
        }
    }

    private fun gotolist(category: String) {
        findNavController().navigate(
            CategoriesFragmentDirections.actionCategoriesFragmentToCategoriesListFragment(
                category
            )
        )
    }


}