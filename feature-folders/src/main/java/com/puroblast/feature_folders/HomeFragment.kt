package com.puroblast.feature_folders

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.feature_folders.databinding.FragmentHomeBinding
import com.puroblast.feature_folders.recycler.FoldersAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FoldersAdapter()

        binding.foldersRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.foldersRecyclerView.adapter = adapter
    }
}
