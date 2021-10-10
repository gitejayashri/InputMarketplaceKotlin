package com.demo.inputmarketplacekotlin.view.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    fun <T : View?> bind(id: Int): T {
        return findViewById(id)
    }

    fun <T : View?> bindViews(view: View, id: Int): T {
        return view.findViewById(id)
    }

    fun getText(inputEditText: EditText): String? {
        return inputEditText.text.toString().trim { it <= ' ' }
    }
}