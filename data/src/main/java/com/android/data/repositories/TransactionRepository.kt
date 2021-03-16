package com.android.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.android.data.commons.BaseRepository
import com.android.data.commons.Constants
import com.android.data.local.BankDatabase
import com.android.data.models.TransactionDTO
import com.android.data.remote.ITransactionAPI
import com.android.data.remote.ResultHandler
import com.android.data.utils.TransactionsUtil

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class TransactionRepository(private val context: Context, private val api: ITransactionAPI, private val bankDB: BankDatabase) :BaseRepository() {
    val mTransactions: LiveData<List<TransactionDTO>> by lazy {
        bankDB.transactionDao().load()
    }
    suspend fun getTransactionsAndSave(): ResultHandler<String> {
        return when (val result = safeApiCall(call = { api.getTransactions() })) {
            is ResultHandler.Success -> {
                result.data.let {

                    var sortedList = result.data.toMutableList()
                        .sortedWith(compareByDescending { it.date })
                    sortedList = TransactionsUtil.filterTransactions(sortedList)

                    bankDB.transactionDao().save(sortedList)
                }
                ResultHandler.Success("Actualizado correctamente")
            }
            is ResultHandler.GenericError -> result
            is ResultHandler.HttpError -> result
            is ResultHandler.NetworkError -> result
        }
    }


    fun deleteTransactions() {
        bankDB.transactionDao().deleteAll()
    }

    fun getName(): String {
        val sharedPref = context.getSharedPreferences(
            Constants.SP_USER, Context.MODE_PRIVATE
        )
        return sharedPref.getString(Constants.PREF_NAME, "") ?: ""
    }

    fun setName(name: String) {
        val sharedPref =
            context.getSharedPreferences(Constants.SP_USER, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(Constants.PREF_NAME, name)
            commit()
        }
    }

    fun getUsername(): String {
        val sharedPref = context.getSharedPreferences(
            Constants.SP_USER, Context.MODE_PRIVATE
        )
        return sharedPref.getString(Constants.PREF_USERNAME, "") ?: ""
    }

    fun setUsername(surname: String) {
        val sharedPref =
            context.getSharedPreferences(Constants.SP_USER, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(Constants.PREF_USERNAME, surname)
            commit()
        }
    }
}