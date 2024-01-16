package com.puroblast.feature_folders.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.puroblast.domain_memento.model.Note
import com.puroblast.domain_memento.repository.NoteRepository
import com.puroblast.feature_folders.ui.notes_screen.recycler.NoteItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesViewModel(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<NoteItem>())
    val state = _state.asStateFlow()

    fun loadNotes(folderId: Int) {
        viewModelScope.launch {
            noteRepository.observeNotes(folderId).collect { notes: List<Note> ->
                val noteItems = notes
                    .map { NoteItem(it.id, it.name) }
                _state.value = noteItems
            }
        }
    }

    class Factory @Inject constructor(
        private val noteRepository: NoteRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            require(modelClass == NotesViewModel::class.java)
            return NotesViewModel(noteRepository) as T
        }
    }
}
