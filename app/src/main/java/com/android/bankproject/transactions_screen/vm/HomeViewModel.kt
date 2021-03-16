package com.android.bankproject.transactions_screen.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.bankproject.commons.BaseViewModel
import com.android.data.models.TransactionDTO
import com.android.data.remote.ResultHandler
import com.android.data.repositories.TransactionRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: TransactionRepository) : BaseViewModel() {

    val transactionList: LiveData<List<TransactionDTO>> = repository.mTransactions

    fun fetchTransactions() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = repository.getTransactionsAndSave()) {
                is ResultHandler.Success -> {
                    showMessage(result.data)
                }
                else -> {
                    setShowError(result)
                }
            }
            _isLoading.postValue(false)
        }
    }

    fun clearList() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTransactions()
        }
    }
}