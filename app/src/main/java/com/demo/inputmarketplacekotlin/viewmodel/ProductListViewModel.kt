package com.demo.inputmarketplacekotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.inputmarketplacekotlin.data.model.AddCartRequest
import com.demo.inputmarketplacekotlin.data.model.BaseResponse
import com.demo.inputmarketplacekotlin.data.repository.ProductListRepository


class ProductListViewModel :
    ViewModel() {

    val productListRepository: ProductListRepository = ProductListRepository();
    fun getProductList(farmerId: Int, pageId: Int): MutableLiveData<BaseResponse> {
        return productListRepository.getProductList(farmerId, pageId)
    }

    fun addCartItem(cartRequest: AddCartRequest): MutableLiveData<BaseResponse> {
        return productListRepository.addCartItem(cartRequest)
    }

}