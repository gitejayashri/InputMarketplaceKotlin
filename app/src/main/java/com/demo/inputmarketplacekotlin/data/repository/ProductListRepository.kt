package com.demo.inputmarketplacekotlin.data.repository


import androidx.lifecycle.MutableLiveData
import com.demo.inputmarketplacekotlin.data.api.ApiClient
import com.demo.inputmarketplacekotlin.data.model.AddCartRequest
import com.demo.inputmarketplacekotlin.data.model.BaseResponse
import com.demo.inputmarketplacekotlin.data.model.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListRepository {

    var mProductListMutableLiveData: MutableLiveData<BaseResponse> = MutableLiveData();

    fun getProductList(farmerId: Int, pageId: Int): MutableLiveData<BaseResponse> {
        ApiClient.instance.getProductList(farmerId, pageId)
            .enqueue(object : Callback<BaseResponse> {
                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>,
                ) {
                    mProductListMutableLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {

                    mProductListMutableLiveData.postValue(BaseResponse(Data(status = 500,
                        result = null,
                        message = t.message)))
                }
            })
        return mProductListMutableLiveData
    }

    fun addCartItem(cartRequest: AddCartRequest): MutableLiveData<BaseResponse> {
        ApiClient.instance.addCartItem(cartRequest).enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                mProductListMutableLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                mProductListMutableLiveData.postValue(BaseResponse(Data(status = 500,
                    result = null,
                    message = t.message)))
            }

        })
        return mProductListMutableLiveData
    }
}