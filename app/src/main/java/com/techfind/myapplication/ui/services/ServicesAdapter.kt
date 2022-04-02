package com.techfind.myapplication.ui.services

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techfind.myapplication.R
import com.techfind.myapplication.databinding.CardViewItemServiceBinding
import com.techfind.myapplication.server.ServiceServer

class ServicesAdapter(

    private val servicesList: ArrayList<ServiceServer>
) : RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view_item_service, parent, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        Log.d("1",servicesList.toString())
        val service = servicesList[position]
        holder.bind(service)
    }


    override fun getItemCount(): Int = servicesList.size

    fun appendItems(newList: ArrayList<ServiceServer>) {
        servicesList.clear()
        servicesList.addAll(newList)
        notifyDataSetChanged()
    }

    class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardViewItemServiceBinding.bind(itemView)
        fun bind(service: ServiceServer) {
            with(binding){
                categoryTextView.text = service.category
                shortDescriptionTextView.text = service.short_description
            }
        }
    }
}