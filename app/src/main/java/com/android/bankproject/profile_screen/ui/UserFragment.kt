package com.android.bankproject.profile_screen.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.android.bankproject.R
import com.android.bankproject.commons.BaseFragment
import com.android.bankproject.databinding.UserFragmentBinding
import com.android.bankproject.profile_screen.vm.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment: BaseFragment() {

    private val presenter: UserViewModel by viewModel()

    private var _binding: UserFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = UserFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uploadNameandUsername()
    }

    override fun loadObservers() {
        presenter.name.observe(viewLifecycleOwner) {
            it?.let { name ->
                binding.editTextTextPersonName.setText(name)

                if (name.isEmpty()){
                    binding.textView8.setText(resources.getString(R.string.emptyname))
                }else{
                    binding.textView8.setText(name)
                }


            }
        }

        presenter.username.observe(viewLifecycleOwner) {
            it?.let { username ->
                binding.editTextTextPersonName2.setText(username)
                if (username.isEmpty()){
                    binding.textView6.setText(resources.getString(R.string.emptyusernname))
                }else{
                    binding.textView6.setText(username)
                }
            }
        }
    }

    private fun uploadNameandUsername(){
        binding.editTextTextPersonName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                presenter.setName(s.toString())
            }

        })

        binding.editTextTextPersonName2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                presenter.setUsername(s.toString())
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}