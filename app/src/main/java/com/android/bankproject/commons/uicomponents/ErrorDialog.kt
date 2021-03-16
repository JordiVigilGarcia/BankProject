package com.android.bankproject.commons.uicomponents

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.android.bankproject.databinding.DialogErrorBinding

class ErrorDialog(context: Context,
                  val mTitle: String,
                  val mBody: String,
                  val mButton: String?,
): Dialog(context) {
    private lateinit var binding: DialogErrorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogErrorBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.dialogErrorTitle.text = mTitle
        binding.dialogErrorBody.text = mBody
        if (mButton == null) {
            binding.dialogErrorButon.visibility = View.GONE
        } else {
            binding.dialogErrorButon.text = mButton
            binding.dialogErrorButon.setOnClickListener(object : View.OnClickListener{
                override fun onClick(p0: View?) {
                    dismiss()
                }
            })
        }
    }
    override fun show() {
        super.show()
        val window = window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
    fun setTitle(title : String){
        binding.dialogErrorTitle.text = title
    }
}