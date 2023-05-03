package com.example.contactsapp.presenter

import com.example.contactsapp.data.module.Contact

interface MainAction {

    fun onAddContact(contacts: List<Contact>)
}