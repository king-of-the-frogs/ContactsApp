package com.example.contactsapp.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.data.module.Contact
import com.example.contactsapp.ui.features.EditContactActivity
import com.example.namespace.databinding.ItemContactBinding

class ContactsAdapter :
    ListAdapter<Contact, ContactsAdapter.MyViewHolder>(MyDiffUtil) {

    object MyDiffUtil : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class MyViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact?) {
            binding.tvNameAndSurname.text = "${contact?.name} ${contact?.surname}"
            binding.tvNumber.text = contact?.number
            binding.btnEdit.setOnClickListener {
                val intent = Intent(binding.root.context, EditContactActivity::class.java)
                intent.putExtra("contact_id", contact?.id)
                intent.putExtra("contact_name", contact?.name)
                intent.putExtra("contact_surname", contact?.surname)
                intent.putExtra("contact_number", contact?.number)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
    }

    fun setData(allContacts: List<Contact>) {
        this.submitList(allContacts)
        notifyDataSetChanged()
    }
}