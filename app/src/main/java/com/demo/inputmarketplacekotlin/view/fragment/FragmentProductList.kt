package com.demo.inputmarketplacekotlin.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.inputmarketplacekotlin.R
import com.demo.inputmarketplacekotlin.data.model.AddCartRequest
import com.demo.inputmarketplacekotlin.data.model.BaseResponse
import com.demo.inputmarketplacekotlin.data.model.Data
import com.demo.inputmarketplacekotlin.data.model.ProductList
import com.demo.inputmarketplacekotlin.databinding.FragmentProductListBinding
import com.demo.inputmarketplacekotlin.utils.Constants
import com.demo.inputmarketplacekotlin.utils.IOUtils
import com.demo.inputmarketplacekotlin.view.activities.MainActivity
import com.demo.inputmarketplacekotlin.view.adapter.AdapterProductList
import com.demo.inputmarketplacekotlin.view.callbacks.PropertyCallbacks
import com.demo.inputmarketplacekotlin.view.callbacks.ToolbarCallbacks
import com.demo.inputmarketplacekotlin.viewmodel.ProductListViewModel
import kotlinx.android.synthetic.main.fragment_product_list.*
import org.json.JSONException
import org.json.JSONObject

class FragmentProductList : Fragment(), PropertyCallbacks {


    lateinit var mParentActivity: MainActivity
    lateinit var mToolbarCallbacks: ToolbarCallbacks
    lateinit var mContext: Context
    lateinit var mAdapterProduct: AdapterProductList
    lateinit var mProductListViewModel: ProductListViewModel

    var mProductList: List<ProductList>? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mParentActivity = context as MainActivity
        this.mToolbarCallbacks = context as ToolbarCallbacks

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val mProductListBinding: FragmentProductListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)
        return mProductListBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext = requireActivity()
        initViews();
    }

    private fun initViews() {

        mToolbarCallbacks.setToolbarTitle(getString(R.string.title_product_list))
        mProductListViewModel = ViewModelProvider(this).get(ProductListViewModel::class.java)

        observProductList();
    }

    private fun observProductList() {
        mProductListViewModel.getProductList(7, 0).observe(viewLifecycleOwner, Observer {
            it
            if (it?.data?.status == 200)
                updateUI(it.data);
            else
                Toast.makeText(mContext, it?.data?.message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun updateUI(data: Data) {
        mProductList = data.result?.pageSet?.productList
        mProductList.let { setProductListAdapter() }
//        setProductListAdapter()
    }


    private fun setProductListAdapter() {
        mAdapterProduct = mProductList?.let { AdapterProductList(mContext, it, this) }!!
        var linearLayoutManager = LinearLayoutManager(mContext)
        rv_product_list.layoutManager = linearLayoutManager
        rv_product_list.hasFixedSize()
        rv_product_list.adapter = mAdapterProduct

    }

    override fun propertyOnClick(position: Int, modelProduct: ProductList) {

    }

    override fun addItemCallback(position: Int, modelProduct: ProductList) {
        IOUtils.myToast("Item Added Successfully", mContext)
        addItemOnServer(modelProduct)
        mAdapterProduct.notifyDataSetChanged()
    }

    override fun removeItemCallback(position: Int, modelProduct: ProductList) {
    }

    override fun deleteItemCallback(position: Int, modelProduct: ProductList) {
    }

    fun addItemOnServer(modelProduct: ProductList) {
        var qty: Int = modelProduct.selectedItem.cartQuantity
        if (qty == 0)
            qty = 1
        else
            qty += 1
        val cartRequest =
            AddCartRequest(Constants.FARMER_ID, modelProduct.selectedItem.productSellerGroupPriceId,
                qty, modelProduct.selectedItem.productPrice)
        mProductListViewModel.addCartItem(cartRequest).observe(viewLifecycleOwner, {
            it
            if (it?.data?.status == 200) {
                updateCartCount(it?.data, modelProduct, qty)
                updateList(modelProduct, qty);
            } else
                Toast.makeText(mContext, it?.data?.message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun updateCartCount(data: Data, modelProduct: ProductList, qty: Int) {

        try {
            val dataJson: JSONObject = data.result
            if (dataJson != null) {
                val resultJson = dataJson.getJSONObject(Constant.RESULT)
                val cartCount = resultJson.getInt(Constant.CART_COUNT)
                if (resultJson.has(Constant.CART_ID)) {
                    val cartId = resultJson.getInt(Constant.CART_ID)
                    product.setCartId(cartId)
                } else {
                    product.setCartId(null)
                }
                mToolbarCallback.updateCartCount(cartCount)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }


    private fun updateList(modelProduct: ProductList, qty: Int) {

        for (attributeValue in modelProduct.productAttribute.productAttributeValueList) {
            if (attributeValue.productSellerGroupPriceId == modelProduct.selectedItem.productSellerGroupPriceId) {
                modelProduct.selectedItem.cartQuantity = qty
                attributeValue.cartQuantity = qty
            }
            break
        }

    }

}
