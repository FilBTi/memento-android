package com.puroblast.feature_folders.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.puroblast.domain_memento.model.Note
import com.puroblast.domain_memento.repository.NoteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateNoteDialogViewModel(
    private val noteRepository: NoteRepository
) : ViewModel() {

    fun addNote(note: Note) {
        viewModelScope.launch {
            noteRepository.createNote(note)
        }
    }

    class Factory @Inject constructor(
        private val noteRepository: NoteRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            require(modelClass == CreateNoteDialogViewModel::class.java)
            return CreateNoteDialogViewModel(noteRepository) as T
        }
    }
}
