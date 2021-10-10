package com.demo.inputmarketplacekotlin.view.callbacks

import com.demo.inputmarketplacekotlin.data.model.ProductList

interface PropertyCallbacks {

    fun propertyOnClick(position: Int, modelProduct: ProductList)
    fun addItemCallback(position: Int, modelProduct: ProductList)
    fun removeItemCallback(position: Int, modelProduct: ProductList)
    fun deleteItemCallback(position: Int, modelProduct: ProductList)

}