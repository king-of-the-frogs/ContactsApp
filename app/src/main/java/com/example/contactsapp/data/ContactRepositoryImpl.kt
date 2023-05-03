package com.example.contactsapp.data

import com.example.contactsapp.data.module.Contact
import io.realm.Realm
import io.realm.kotlin.deleteFromRealm
import java.util.*

class ContactRepositoryImpl(private val realm: Realm) : ContactRepository {
    override fun addContact(name: String, surname: String, number: String) {
        realm.executeTransaction {
            it.createObject(Contact::class.java, UUID.randomUUID().toString()).apply {
                this.name = name
                this.surname = surname
                this.number = number
            }
        }
    }

    override fun getContact(): List<Contact> {
        return realm.where(Contact::class.java).findAll()
    }

    override fun getContactById(id: String): Contact? {
        return realm.where(Contact::class.java).equalTo("id", id).findFirst()
    }

    override fun updateContact(id: String, name: String, surname: String, number: String) {
        val contact = getContactById(id)
        if (contact != null) {
            realm.executeTransaction {
                contact.name = name
                contact.surname = surname
                contact.number = number
            }
        }
    }
    override fun deleteContact(id: String, name: String, surname: String, number: String) {
        val contact = getContactById(id)
        if (contact != null) {
            realm.executeTransaction {
                contact.deleteFromRealm()
            }
        }
    }
}