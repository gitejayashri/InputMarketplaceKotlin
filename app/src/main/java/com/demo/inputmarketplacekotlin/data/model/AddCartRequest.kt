package com.demo.inputmarketplacekotlin.data.model

class AddCartRequest(
    val farmerId: Int,
    val productSellerGroupPriceId: Int,
    val qty: Int,
    val price: Double,
) {
}