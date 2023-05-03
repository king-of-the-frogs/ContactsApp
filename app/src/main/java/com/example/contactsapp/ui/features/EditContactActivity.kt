package com.example.contactsapp.ui.features

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.contactsapp.data.ContactRepositoryImpl
import com.example.namespace.databinding.ActivityEditBinding
import io.realm.Realm

class EditContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contactName = intent.getStringExtra("contact_name") ?: ""
        val contactSurname = intent.getStringExtra("contact_surname") ?: ""
        val contactNumber = intent.getStringExtra("contact_number") ?: ""

        binding.etName.setText(contactName)
        binding.etSurname.setText(contactSurname)
        binding.etNumber.setText(contactNumber)

        binding.etName.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.etName.postDelayed({
            imm.showSoftInput(binding.etName, InputMethodManager.SHOW_IMPLICIT)
        }, 200)

        binding.btnSave.setOnClickListener {
            val newNumber = binding.etNumber.text.toString()
            val newName = binding.etName.text.toString()
            val newSurname = binding.etSurname.text.toString()
            val contactId = intent.getStringExtra("contact_id") ?: ""
            val contactRepository = ContactRepositoryImpl(Realm.getDefaultInstance())
            contactRepository.updateContact(contactId, newName, newSurname, newNumber)
            finish()
            if (newNumber.isEmpty()) {
                contactRepository.deleteContact(contactId, newName, newSurname, newNumber)

            }
        }
    }
}