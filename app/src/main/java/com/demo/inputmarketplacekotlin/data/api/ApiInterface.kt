package com.demo.inputmarketplacekotlin.data.api

import com.demo.inputmarketplacekotlin.data.model.AddCartRequest
import com.demo.inputmarketplacekotlin.data.model.BaseResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("api/v1/product/GetProducts")
    fun getProductList(
        @Field("farmerId") farmerId: Int,
        @Field("pageId") pageId: Int,
    ): Call<BaseResponse>

    @POST("api/v1/product/SaveCartItem")
    fun addCartItem(cartRequest: AddCartRequest): Call<BaseResponse>
}