package com.puroblast.feature_auth

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.feature_auth.databinding.FragmentAuthBinding

class AuthFragment : Fragment(R.layout.fragment_auth) {

    private val binding by viewBinding(FragmentAuthBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.linkTextView.setOnClickListener {
            if (binding.linkTextView.text.toString() == getString(R.string.register_text)) {
                binding.joinButton.setText(R.string.register_text)
                binding.linkTextView.setText(R.string.join_underlined_text)
                binding.accountTextView.setText(R.string.do_you_have_account_text)
            } else {
                binding.joinButton.setText(R.string.join_text)
                binding.linkTextView.setText(R.string.register_underlined_text)
                binding.accountTextView.setText(R.string.dont_you_have_account_text)
            }
        }

        binding.joinButton.setOnClickListener {
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://com.puroblast.feature_folders/homeFragment".toUri())
                .build()
            findNavController().navigate(request)
        }
    }
}
