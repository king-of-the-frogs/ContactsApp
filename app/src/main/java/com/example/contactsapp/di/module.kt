package com.example.contactsapp.di


import com.example.contactsapp.data.ContactRepository
import com.example.contactsapp.data.ContactRepositoryImpl
import com.example.contactsapp.presenter.Presenter
import com.example.contactsapp.ui.MainViewModel
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<Realm> {
        Realm.init(androidApplication())

        val configuration = RealmConfiguration.Builder()
            .name("todo.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()

        Realm.setDefaultConfiguration(configuration)

        Realm.getDefaultInstance()
    }

    single<ContactRepository> {
        ContactRepositoryImpl(realm = get())
    }

    viewModel {
        MainViewModel(contactRepository = get())
    }

    single {
        Presenter(contactRepository = get())
    }
}