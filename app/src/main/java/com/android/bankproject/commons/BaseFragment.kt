package com.android.bankproject.commons

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.android.bankproject.commons.uicomponents.ErrorDialog
import com.android.bankproject.commons.uicomponents.OkDialog

abstract class BaseFragment: Fragment() {

    var errorDialog : ErrorDialog? = null
    var okDialog : OkDialog? = null

    abstract fun loadObservers()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        errorDialog?.dismiss()
        okDialog?.dismiss()
    }
}