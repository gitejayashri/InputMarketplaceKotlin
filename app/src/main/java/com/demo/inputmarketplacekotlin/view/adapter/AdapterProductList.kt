package com.demo.inputmarketplacekotlin.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.inputmarketplacekotlin.R
import com.demo.inputmarketplacekotlin.data.model.ProductAttributeValueList
import com.demo.inputmarketplacekotlin.data.model.ProductList
import com.demo.inputmarketplacekotlin.databinding.ItemProductBinding
import com.demo.inputmarketplacekotlin.utils.IOUtils
import com.demo.inputmarketplacekotlin.view.callbacks.PropertyCallbacks


class AdapterProductList(
    private val mContext: Context,
    private val mProductList: List<ProductList>,
    private val mPropertyCallbacks: PropertyCallbacks,
) : RecyclerView.Adapter<AdapterProductList.ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
//        val itemProductListBinding =
//            LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
//        return ProductViewHolder(itemProductListBinding)
        val binding: ItemProductBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
            R.layout.item_product,
            parent,
            false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(mProductList.get(position))
        val modelProduct: ProductList = mProductList.get(position)
        setSpinnerData(holder, mProductList.get(position));

//        holder.binding.tvPrice.text = IOUtils.formatPrice(modelProduct.selectedItem.productPrice)
        holder.spMeasure.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                var selectedMeasure: ProductAttributeValueList =
                    p0?.selectedItem as ProductAttributeValueList
                modelProduct.selectedItem = selectedMeasure;
                holder.bind(modelProduct)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        holder.btnAddProduct.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {


            }
        })

        val clickListener = View.OnClickListener { view ->

            when (view.getId()) {
                R.id.btn_add_product -> AddNewProduct(position, modelProduct)
                R.id.iv_plus -> AddQuantity(position, modelProduct)
                R.id.iv_minus -> RemoveQuantity(position, modelProduct)
            }
        }
        holder.btnAddProduct.setOnClickListener(clickListener)
    }

    private fun AddNewProduct(position: Int, modelProduct: ProductList) {

        val qty = modelProduct.selectedItem.cartQuantity + 1
        modelProduct.selectedItem.cartQuantity=qty
        mPropertyCallbacks.addItemCallback(position, modelProduct)
    }

    private fun AddQuantity(position: Int, modelProduct: ProductList) {

        val qty = modelProduct.selectedItem.cartQuantity + 1
        mPropertyCallbacks.addItemCallback(position, modelProduct)
    }

    private fun RemoveQuantity(position: Int, modelProduct: ProductList) {

        val qty = modelProduct.selectedItem.cartQuantity - 1
        if (qty <= 0)
            mPropertyCallbacks.deleteItemCallback(position, modelProduct)
        else
            mPropertyCallbacks.removeItemCallback(position, modelProduct)
    }

    private fun setSpinnerData(holder: ProductViewHolder, modelProduct: ProductList) {
        val measureList: List<ProductAttributeValueList> =
            modelProduct.productAttribute.productAttributeValueList
        val adapter = AdapterMeasure(mContext,
            R.layout.item_spinner_text, R.id.tv_title, measureList)
        holder.spMeasure.setAdapter(adapter)

        if (modelProduct.selectedItem != null) {
            for (measure in measureList) {
                if (modelProduct.selectedItem.productSellerGroupPriceId == measure.productSellerGroupPriceId) {
                    modelProduct.selectedItem.cartQuantity = measure.cartQuantity
                    modelProduct.selectedItem.productSellerGroupPriceId =
                        measure.productSellerGroupPriceId
                    holder.spMeasure.setSelection(measureList.indexOf(measure))
                    break
                }
            }
        } else {
            modelProduct.selectedItem = measureList.get(0);
            holder.spMeasure.setSelection(0)
        }
    }

    override fun getItemCount() = mProductList.size

    class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var spMeasure: Spinner = binding.spinnerMeasure
        var btnAddProduct: Button = binding.btnAddProduct
        var ivPlus: ImageView = binding.ivPlus
        var ivMinus: ImageView = binding.ivMinus

        fun bind(data: ProductList) {
            binding.product = data
            binding.executePendingBindings()
        }

    }
}