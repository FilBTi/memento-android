package com.puroblast.feature_folders

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.feature_folders.databinding.FragmentHomeBinding
import com.puroblast.feature_folders.di.FoldersComponentViewModel
import com.puroblast.feature_folders.presentation.FoldersViewModel
import com.puroblast.feature_folders.recycler.FoldersAdapter
import dagger.Lazy
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

        val adapter = FoldersAdapter()

        binding.foldersRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.foldersRecyclerView.adapter = adapter
    }
}
