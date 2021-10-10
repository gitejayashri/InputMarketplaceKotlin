package com.demo.inputmarketplacekotlin.data.model

import com.google.gson.annotations.SerializedName



data class PageSet (

	@SerializedName("page") val page : Int,
	@SerializedName("count") val count : Int,
	@SerializedName("totalPages") val totalPages : Int,
	@SerializedName("totalCount") val totalCount : Int,
	@SerializedName("productList") val productList : List<ProductList>
)