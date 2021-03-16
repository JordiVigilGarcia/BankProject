package com.android.bankproject.commons.uicomponents

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.android.bankproject.R
import com.android.bankproject.databinding.DialogOkBinding
import com.android.data.commons.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OkDialog(
    private val mContext: Context,
    private val mBody: String
) : Dialog(mContext) {
    private lateinit var binding: DialogOkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogOkBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.textView3.text = mBody

        binding.textView4.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                dismiss()
            }
        })
    }

    override fun show() {
        super.show()
        val window = window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}