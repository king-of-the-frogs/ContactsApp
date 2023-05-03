package com.example.contactsapp

import com.example.contactsapp.data.ContactRepositoryImpl
import com.example.contactsapp.data.module.FakeContact
import io.realm.Realm
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun testViewModel() {
        val contactRepository = ContactRepositoryImpl(Realm.getDefaultInstance())

        val phone = "+88005553535"

        val contact = FakeContact(
            name = "Stas",
            surname = "Qmar",
            phone = "88005553535"
        )

        contactRepository.addContacts(contact)
        val list = contactRepository.getAllContacts()
        val lastContact = list.last()

        assertEquals(contact, lastContact)
        assertNotEquals(phone, lastContact.phone)
    }
}