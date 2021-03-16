package com.android.bankproject.transactions_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.bankproject.R
import com.android.bankproject.commons.BaseFragment
import com.android.bankproject.commons.uicomponents.ErrorDialog
import com.android.bankproject.commons.uicomponents.OkDialog
import com.android.bankproject.databinding.TransactionsFragmentBinding
import com.android.bankproject.transactions_screen.vm.HomeViewModel
import com.android.bankproject.utils.SharedTransactionVM
import com.android.data.models.TransactionDTO
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TransactionsFragment : BaseFragment(),
    CellClickListener {

    private val presenter: HomeViewModel by sharedViewModel()
    private val sharedTransactionVM: SharedTransactionVM by sharedViewModel()

    private var _binding: TransactionsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: TransactionAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = TransactionsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun loadObservers() {
        presenter.transactionList.observe(viewLifecycleOwner) {
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
            adapter =
                TransactionAdapter(
                    it,
                    this
                )
            binding.recyclerView.adapter = adapter
        }

        presenter.showMessage.observe(viewLifecycleOwner) {
            okDialog = activity?.let { activity ->
                OkDialog(activity, it)
            }
            okDialog?.show()
        }

        presenter.showError.observe(viewLifecycleOwner) {
            errorDialog = activity?.let { activity ->
                ErrorDialog(
                    activity,
                    getString(R.string.alert),
                    it,
                    getString(R.string.close)
                )

            }
            errorDialog?.setCancelable(false)
            errorDialog?.show()
        }

        presenter.isLoading.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                } else  {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCellClickListener(transactionDTO: TransactionDTO) {
        sharedTransactionVM.setTransaction(transactionDTO)
        findNavController().navigate(R.id.action_listFragment_to_detailFragment)
    }
}