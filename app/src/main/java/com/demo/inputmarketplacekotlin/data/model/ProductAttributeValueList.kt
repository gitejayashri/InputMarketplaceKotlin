package com.demo.inputmarketplacekotlin.data.model

import com.google.gson.annotations.SerializedName




data class ProductAttributeValueList (

	@SerializedName("id") val id : Int,
	@SerializedName("productAttributeId") val productAttributeId : Int,
	@SerializedName("productAttribute") val productAttribute : String,
	@SerializedName("attributeValue") val attributeValue : String,
	@SerializedName("productPrice") val productPrice : Double,
	@SerializedName("cartQuantity") var cartQuantity : Int,
	@SerializedName("cartId") val cartId : String,
	@SerializedName("productSellerGroupPriceId") var productSellerGroupPriceId : Int,
	@SerializedName("sequence") val sequence : Int
)
{
	override fun toString(): String {
		return attributeValue
	}
}