package com.example.noteappcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.noteappcompose.model.Note
import com.example.noteappcompose.utils.DateConverter
import com.example.noteappcompose.utils.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class,UUIDConverter::class)
abstract class NoteDataBase :RoomDatabase(){

    abstract fun noteDao():NoteDataBaseDao
}