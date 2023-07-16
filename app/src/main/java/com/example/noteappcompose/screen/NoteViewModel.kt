package com.example.noteappcompose.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappcompose.model.Note
import com.example.noteappcompose.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    private var _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNote().distinctUntilChanged().collect{
                _noteList.value = it
            }

        }
    }

    fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }

    fun removeNote(note:Note) = viewModelScope.launch { repository.deleteNote(note) }

    suspend fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }

    suspend fun deleteAllNote() = viewModelScope.launch { repository.deleteAllNote() }


}