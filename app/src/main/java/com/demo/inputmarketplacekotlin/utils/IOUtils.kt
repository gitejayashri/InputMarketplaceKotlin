package com.demo.inputmarketplacekotlin.utils

import android.content.Context
import android.widget.Toast
import java.sql.Time
import java.text.DecimalFormat

object IOUtils  {

     fun formatPrice( value: Double) : String
    {
        val formatter:DecimalFormat= DecimalFormat("###,###,##0")
        return formatter.format(value)
    }

    fun myToast(s: String, mContext: Context) {
        Toast.makeText(mContext,s,Toast.LENGTH_SHORT).show()
    }
}