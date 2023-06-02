package com.cankarademir.cankarademir_odev6.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.cankarademir.cankarademir_odev6.R
import com.cankarademir.cankarademir_odev6.models.Product

class ProductListViewAdapter(private val context: Activity, private val list: List<Product>) : ArrayAdapter<Product>(context, R.layout.custum_listview_item, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val rootView = context.layoutInflater.inflate(R.layout.custum_listview_item,null, true)

        val r_brand = rootView.findViewById<TextView>(R.id.txtBrand)
        val r_model = rootView.findViewById<TextView>(R.id.txtModel)
        val r_price = rootView.findViewById<TextView>(R.id.txtPrice)
        val r_img = rootView.findViewById<ImageView>(R.id.imgProductThumbnail)

        val product = list[position]

        r_brand.text = product.brand
        r_model.text = product.title
        r_price.text = "Price: ${product.price.toString()}$"

        val imageUrl = product.thumbnail

        Glide.with(rootView).load(imageUrl).into(r_img)

        return rootView
    }
}