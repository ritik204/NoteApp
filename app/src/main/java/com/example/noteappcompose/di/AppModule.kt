package com.example.noteappcompose.di

import android.content.Context
import androidx.room.Room
import com.example.noteappcompose.data.NoteDataBase
import com.example.noteappcompose.data.NoteDataBaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun providesNotesDao(noteDataBase: NoteDataBase) :NoteDataBaseDao = noteDataBase.noteDao()

    @Singleton
    @Provides
    fun providesAppDataBase(@ApplicationContext context: Context):NoteDataBase
    = Room.databaseBuilder(
        context,
        NoteDataBase::class.java,
        "note_db")
        .fallbackToDestructiveMigration()
        .build()


}