package com.example.contactsapp.data

import com.example.contactsapp.data.module.Contact
import com.example.contactsapp.data.module.FakeContact

interface ContactRepository {
    fun addContact(name: String, surname: String, number: String)
    fun getContact(): List<Contact>
    fun getContactById(id: String): Contact?
    fun updateContact(id: String, name: String, surname: String, number: String)
    fun deleteContact(id: String, name: String, surname: String, number: String)
    fun addContacts(contact: FakeContact)
    fun getAllContacts(): MutableList<FakeContact>
}
