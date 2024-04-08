package com.example.secretxat.di

import android.content.Context
import com.example.secretxat.data.database.DatabaseServiceImpl
import com.example.secretxat.data.network.FirebaseChatService
import com.example.secretxat.domain.DatabaseService
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)   // dona el alcance de la dependencia a nivel de tota l'aplicación
object DataModule {

    @Singleton
    @Provides
    fun providesDatabaseReference() = Firebase.database.reference

    @Singleton
    @Provides
    fun provideFirebaseServices(reference: DatabaseReference) = FirebaseChatService(reference)

    // @ApplicationContext es una anotación que se utiliza para indicar que el contexto proporcionado es el contexto de la aplicación.
    // En este caso, se utiliza para proporcionar el contexto de la aplicación al DataStore.
    // El contexto de la aplicación es un contexto global que está disponible en toda la aplicación.
    // Se puede utilizar para acceder a los recursos de la aplicación, como los archivos de recursos, las preferencias compartidas y la base de datos de la aplicación.
    @Singleton
    @Provides
    fun providesDataStoreServices(@ApplicationContext context: Context): DatabaseService {
        return DatabaseServiceImpl(context)
    }

    @Singleton
    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

}