package com.demo.inputmarketplacekotlin.data.model

import com.google.gson.annotations.SerializedName

data class Result (

	@SerializedName("pageSet") val pageSet : PageSet
)