package com.example.contactsapp.ui.features

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.contactsapp.data.module.Contact
import com.example.contactsapp.presenter.MainAction
import com.example.contactsapp.presenter.Presenter
import com.example.contactsapp.ui.MainActivity
import com.example.namespace.databinding.ActivityAddBinding
import com.example.namespace.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject


class AddContactActivity : AppCompatActivity(), MainAction {

    private lateinit var binding: ActivityAddBinding

    private val presenter: Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.initAction(this)

        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            with(binding) {
                presenter.addContact(
                    name = etName.text.toString(),
                    surname = etSurname.text.toString(),
                    number = etNumber.text.toString()
                )
            }
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onAddContact(contacts: List<Contact>) {
        Toast.makeText(this, contacts.last().name, Toast.LENGTH_SHORT).show()
    }
}