package com.techfind.myapplication.ui.categorieslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.techfind.myapplication.R
import com.techfind.myapplication.databinding.CardViewItemServiceBinding
import com.techfind.myapplication.databinding.CategorieslistFragmentBinding
import com.techfind.myapplication.server.ServiceServer

class CategoriesListAdapter(

    private val servicesList: ArrayList<ServiceServer>

   // private val onItemClicked: (ServiceServer) -> Unit
) : RecyclerView.Adapter<CategoriesListAdapter.ServiceViewHolder>() {

    //private val servicesList: MutableList<ServiceServer> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view_item_service, parent, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = servicesList[position]
        holder.bind(service)
       // holder.itemView.setOnClickListener { onItemClicked(servicesList[position]) }
    }

    override fun getItemCount(): Int = servicesList.size

    fun appendItems(newList: ArrayList<ServiceServer>) {
        servicesList.clear()
        servicesList.addAll(newList)
        notifyDataSetChanged()
    }


    class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardViewItemServiceBinding.bind(itemView)
        private val context = binding.root
        fun bind(service: ServiceServer) {
            with(binding) {

                categoryTextView.text = service.category
                shortDescriptionTextView.text = service.short_description

                Glide.with(context).load(service.urlPicture).into(pictureServiceImageView)
                //imagen de prueba
                //Picasso.get().load("https://res.cloudinary.com/stchi/image/upload/v1609403400/Main/Tide/en_US/HOW_TO_WASH_CLOTES_How_to_do_Laundry_570x310.png").into(pictureServiceImageView)


          /*      if(service.category == "Carpinteria"){
                    categoryTextView.text = service.category
                    shortDescriptionTextView.text = service.short_description
                    //     Glide.with(context).load(book.urlPicture).into(pictureBookImageView)
                    Picasso.get().load("https://res.cloudinary.com/stchi/image/upload/v1609403400/Main/Tide/en_US/HOW_TO_WASH_CLOTES_How_to_do_Laundry_570x310.png").into(pictureServiceImageView)

                } else {
                    categoryTextView.text = null
                    shortDescriptionTextView.text = null
                    //     Glide.with(context).load(book.urlPicture).into(pictureBookImageView)
                    Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/9/99/White_Background_%28To_id_screen_dust_during_cleanup%29.jpg").into(pictureServiceImageView)
                }*/

            }
        }
    }
}
