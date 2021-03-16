package com.android.bankproject.details_transactions.ui

import com.android.bankproject.commons.BaseFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.bankproject.databinding.DetailFragmentBinding
import com.android.bankproject.utils.SharedTransactionVM
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailFragment : BaseFragment() {

    private val sharedTransactionVM: SharedTransactionVM by sharedViewModel()
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val transaction = sharedTransactionVM.transaction.value
        transaction?.let {
            binding.detailTV.text =
                "${transaction.id} ${transaction.description} ${transaction.date} ${transaction.amount} " +
                        "${transaction.fee} ${transaction.total}"
        }
    }


    override fun loadObservers() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}