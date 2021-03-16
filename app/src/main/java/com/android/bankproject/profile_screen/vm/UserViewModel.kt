package com.android.bankproject.profile_screen.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.bankproject.commons.BaseViewModel
import com.android.data.repositories.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val repository: TransactionRepository): BaseViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private val _username = MutableLiveData<String>()
    val username: LiveData<String>
        get() = _username

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _name.postValue(repository.getName())
            _username.postValue(repository.getUsername())
        }
    }

    fun setName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setName(name)
        }
    }

    fun setUsername(surname: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setUsername(surname)
        }
    }
}