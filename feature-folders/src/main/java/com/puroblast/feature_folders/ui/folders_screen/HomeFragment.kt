package com.puroblast.feature_folders.ui.folders_screen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.mikepenz.fastadapter.select.selectExtension
import com.puroblast.feature_folders.R
import com.puroblast.feature_folders.databinding.FragmentHomeBinding
import com.puroblast.feature_folders.di.FoldersComponentViewModel
import com.puroblast.feature_folders.presentation.folders.FoldersViewModel
import com.puroblast.feature_folders.ui.folders_screen.recycler.FolderItem
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    @Inject
    internal lateinit var foldersViewModelFactory: Lazy<FoldersViewModel.Factory>

    private val foldersViewModel: FoldersViewModel by viewModels {
        foldersViewModelFactory.get()
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<FoldersComponentViewModel>().foldersComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val folderItemAdapter = FastItemAdapter<FolderItem>()
        folderItemAdapter.selectExtension {
            isSelectable = true
            multiSelect = true
            selectOnLongClick = true
        }

        folderItemAdapter.onClickListener = { view, adapter, item, position ->
            val bundle = bundleOf()
            bundle.putString("folderName", item.folderName)
            bundle.putInt("folderId", item.id)
            findNavController().navigate(R.id.action_foldersFragment_to_notesFragment, bundle)
            false
        }

        folderItemAdapter.onLongClickListener = { view, adapter, item, position ->
            Log.d("TAG", "onViewCreated: ${item.folderName} was clicked")
            false
        }

        binding.foldersRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.foldersRecyclerView.adapter = folderItemAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                foldersViewModel.state.collect {
                    state ->
                    val result = FastAdapterDiffUtil.calculateDiff(folderItemAdapter.itemAdapter, state)
                    FastAdapterDiffUtil[folderItemAdapter.itemAdapter] = result
                }
            }
        }

        binding.addButton.setOnClickListener {
            val dialog = CreateFolderDialogFragment()
            dialog.show(parentFragmentManager, "Create folder dialog")
        }
    }
}
