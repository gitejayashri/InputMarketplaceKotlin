package com.demo.inputmarketplacekotlin.data.model

import android.util.Base64
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.demo.inputmarketplacekotlin.R
import com.google.gson.annotations.SerializedName

data class ProductList(

    @SerializedName("id") val id: Int? = null,
    @SerializedName("manufacturerId") val manufacturerId: Int? = null,
    @SerializedName("manufacturer") val manufacturer: String? = null,
    @SerializedName("productsCategoryId") val productsCategoryId: Int? = null,
    @SerializedName("productsCategory") val productsCategory: String? = null,
    @SerializedName("productHSNId") val productHSNId: Int? = null,
    @SerializedName("productHSN") val productHSN: Int? = null,
    @SerializedName("productDescription") val productDescription: String? = null,
    @SerializedName("productUPCCode") val productUPCCode: String,
    @SerializedName("productEANCode") val productEANCode: String,
    @SerializedName("productInformation") val productInformation: String,
    @SerializedName("productDescriptionDevnagri") val productDescriptionDevnagri: String,
    @SerializedName("isDecimal") val isDecimal: Boolean,
    @SerializedName("isActive") val isActive: Boolean,
    @SerializedName("sellerId") val sellerId: Int,
    @SerializedName("sellerName") val sellerName: String,
    @SerializedName("cartId") val cartId: String,
    @SerializedName("cartCount") val cartCount: Int,
    @SerializedName("productImage") val productImage: String,
    @SerializedName("productAttribute") val productAttribute: ProductAttribute,
    @SerializedName("productSeller") val productSeller: String,
    @SerializedName("selectedItem") var selectedItem: ProductAttributeValueList,
    @SerializedName("productsInformationKeyValuesList") val productsInformationKeyValuesList: String,
    @SerializedName("productBrandList") val productBrandList: List<String>,
    @SerializedName("productsImagesList") val productsImagesList: String,
)

@BindingAdapter("imageUrl")
fun setImageUrl(imgView: ImageView, base64: String?) {

    base64?.let {
        val decodeString: ByteArray = Base64.decode(it, Base64.DEFAULT)

        Glide.with(imgView.context)
            .load(decodeString)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.default_placeholder_gallery)
                    .error(R.drawable.default_placeholder_gallery))
            .into(imgView)
    }
}
