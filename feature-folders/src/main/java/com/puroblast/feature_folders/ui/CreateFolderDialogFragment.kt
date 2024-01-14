package com.puroblast.feature_folders.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.puroblast.domain_memento.model.Folder
import com.puroblast.feature_folders.R
import com.puroblast.feature_folders.di.FoldersComponentViewModel
import com.puroblast.feature_folders.presentation.CreateFolderDialogViewModel
import dagger.Lazy
import javax.inject.Inject

class CreateFolderDialogFragment : DialogFragment() {

    @Inject
    internal lateinit var createFolderViewModelFactory: Lazy<CreateFolderDialogViewModel.Factory>

    private val createFolderDialogViewModel: CreateFolderDialogViewModel by viewModels {
        createFolderViewModelFactory.get()
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<FoldersComponentViewModel>().foldersComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return requireActivity().let {
            val builder = MaterialAlertDialogBuilder(
                it,
                R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog
            )
            val inflater = it.layoutInflater
            val view = inflater.inflate(R.layout.fragment_create_folder_dialog, null)
            val editText = view.findViewById<EditText>(R.id.folderEditText)
            builder.setView(view)
                .setMessage(getString(R.string.create_new_folder_text))
                .setPositiveButton(getString(R.string.create_text)) { _, _ ->
                    createFolderDialogViewModel.addFolder(
                        Folder(
                            0,
                            editText.text.toString(),
                            emptyList()
                        )
                    )
                }
                .setNegativeButton(getString(R.string.cancel_text)) { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        }
    }
}
