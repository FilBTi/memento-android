package com.puroblast.memento

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.memento.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,
            findNavController(R.id.appFragmentContainerView)
        )

        findNavController(R.id.appFragmentContainerView).addOnDestinationChangedListener {
                _, destination, _ ->
            if (destination.id in listOf(
                    com.puroblast.feature_folders.R.id.questionDetailsFragment,
                    com.puroblast.feature_folders.R.id.repeatQuestionsFragment,
                    com.puroblast.feature_auth.R.id.authFragment
            )) {
                binding.bottomNavigationView.visibility = View.GONE
            } else {
                binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }
}
