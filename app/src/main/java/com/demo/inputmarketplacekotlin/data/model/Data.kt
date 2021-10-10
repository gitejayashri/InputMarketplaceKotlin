package com.demo.inputmarketplacekotlin.data.model
import com.google.gson.annotations.SerializedName

data class Data (

	@SerializedName("status") val status : Int?,
	@SerializedName("result") val result : Result?,
	@SerializedName("message") val message : String?
)