package com.android.bankproject.redirection_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.bankproject.R
import com.android.bankproject.commons.BaseFragment
import com.android.bankproject.databinding.HomeFragmentBinding

class HomeFragment : BaseFragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickTransProfile()
    }

    override fun loadObservers() {
    }

    private fun onClickTransProfile(){
        binding.textView11.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_listFragment);
        }

        binding.textView14.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment);
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}