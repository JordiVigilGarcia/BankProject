package com.android.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.data.commons.Constants

@Entity(tableName = Constants.TABLE_TRANSACTIONS)
data class TransactionDTO(
    @PrimaryKey val id: String,
    var date: String,
    val amount: String,
    val description: String?,
    val fee: String?,
    var total: String?
)