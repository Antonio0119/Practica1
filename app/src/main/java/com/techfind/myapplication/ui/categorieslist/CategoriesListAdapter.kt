package com.techfind.myapplication.ui.categorieslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.squareup.picasso.Picasso
import com.techfind.myapplication.R
import com.techfind.myapplication.databinding.CardViewItemServiceBinding
import com.techfind.myapplication.databinding.CategorieslistFragmentBinding
import com.techfind.myapplication.server.ServiceServer
import com.techfind.myapplication.server.User

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
                //val user: User = db.collection("users").document(service.user_id).toObject<User>()
                categoryTextView.text = service.category
                shortDescriptionTextView.text = service.short_description
                //     Glide.with(context).load(book.urlPicture).into(pictureBookImageView)
                //imagen de prueba
                Picasso.get().load("https://res.cloudinary.com/stchi/image/upload/v1609403400/Main/Tide/en_US/HOW_TO_WASH_CLOTES_How_to_do_Laundry_570x310.png").into(pictureServiceImageView)

            }
        }
    }
}
