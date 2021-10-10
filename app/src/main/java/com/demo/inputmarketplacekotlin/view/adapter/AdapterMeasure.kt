package com.demo.inputmarketplacekotlin.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.demo.inputmarketplacekotlin.R
import com.demo.inputmarketplacekotlin.data.model.ProductAttributeValueList
import java.lang.String

class AdapterMeasure(
    context: Context,
    resourceId: Int,
    textviewId: Int,
    list: List<ProductAttributeValueList>,
) :
    ArrayAdapter<ProductAttributeValueList?>(context, resourceId, textviewId, list) {
    var inflater: LayoutInflater

    init {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowItem: ProductAttributeValueList? = getItem(position)
        val rowView: View = inflater.inflate(R.layout.item_spinner_text, null, true)
        val txtTitle = rowView.findViewById<View>(R.id.tv_title) as TextView
        if (rowItem != null) {
            txtTitle.setText(String.valueOf(rowItem.attributeValue))
        }
        return rowView
    }

}