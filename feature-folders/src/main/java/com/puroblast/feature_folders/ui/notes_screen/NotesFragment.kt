package com.puroblast.feature_folders.ui.notes_screen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.mikepenz.fastadapter.select.selectExtension
import com.puroblast.feature_folders.R
import com.puroblast.feature_folders.databinding.FragmentNotesBinding
import com.puroblast.feature_folders.di.FoldersComponentViewModel
import com.puroblast.feature_folders.presentation.notes.NotesViewModel
import com.puroblast.feature_folders.ui.notes_screen.recycler.NoteItem
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesFragment : Fragment(R.layout.fragment_notes) {

    private val binding by viewBinding(FragmentNotesBinding::bind)

    @Inject
    internal lateinit var notesViewModelFactory: Lazy<NotesViewModel.Factory>

    private val notesViewModel: NotesViewModel by viewModels {
        notesViewModelFactory.get()
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<FoldersComponentViewModel>().foldersComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val folderId = requireArguments().getInt("folderId")
        binding.notesToolbar.title = requireArguments().getString("folderName")

        notesViewModel.loadNotes(folderId)

        val noteItemAdapter = FastItemAdapter<NoteItem>()
        noteItemAdapter.selectExtension {
            isSelectable = true
            multiSelect = true
            selectOnLongClick = true
        }

        binding.notesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.notesRecyclerView.adapter = noteItemAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                notesViewModel.state.collect { state ->
                    val result =
                        FastAdapterDiffUtil.calculateDiff(noteItemAdapter.itemAdapter, state)
                    FastAdapterDiffUtil[noteItemAdapter.itemAdapter] = result
                }
            }
        }

        binding.addButton.setOnClickListener {
            val dialog = CreateNoteDialogFragment(folderId)
            dialog.show(parentFragmentManager, "Create folder dialog")
        }
    }
}
