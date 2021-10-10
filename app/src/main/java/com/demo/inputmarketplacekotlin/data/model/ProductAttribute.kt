package com.demo.inputmarketplacekotlin.data.model

import com.google.gson.annotations.SerializedName


data class ProductAttribute (

	@SerializedName("id") val id : Int,
	@SerializedName("productId") val productId : Int,
	@SerializedName("product") val product : String,
	@SerializedName("attributeName") val attributeName : String,
	@SerializedName("productAttributeValueList") val productAttributeValueList : List<ProductAttributeValueList>
)