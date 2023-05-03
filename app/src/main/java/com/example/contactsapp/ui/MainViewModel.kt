package com.example.contactsapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactsapp.data.ContactLiveData
import com.example.contactsapp.data.ContactRepository
import com.example.contactsapp.data.module.Contact

class MainViewModel(private val contactRepository: ContactRepository) : ViewModel() {

    val allContacts: ContactLiveData
        get() = getAllContacts() as ContactLiveData

    fun addContact(name: String, surname: String, number: String) {

        contactRepository.addContact(name, surname, number)
    }

    private fun getAllContacts(): MutableLiveData<List<Contact>> {
        val list = ContactLiveData()
        val allContacts = contactRepository.getContact()
        list.value = allContacts.subList(0, allContacts.size)
        return list
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MainViewModel", "MainViewModel -> onCleared")
    }
}