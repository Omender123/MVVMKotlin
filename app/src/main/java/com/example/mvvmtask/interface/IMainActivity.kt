package com.example.mymftcustomer

import android.content.Context
import android.view.View

interface IMainActivity {

    fun showMessage(message: String)
    fun showProgress(isShowing : Boolean)

    fun getContext() : Context
    fun hideSoftKeyboard(view : View)
    fun showSoftKeyboard(view : View)

}