package com.cankarademir.cankarademir_odev6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.cankarademir.cankarademir_odev6.configs.Util
import com.cankarademir.cankarademir_odev6.services.DummyService

class UrunDetayActivity : AppCompatActivity() {

    lateinit var dummyService: DummyService
    lateinit var imgDetail: ImageView
    lateinit var txtDetailModel: TextView
    lateinit var txtDetailDesc: TextView
    lateinit var txtDetailPrice: TextView
    lateinit var txtDetailDiscount: TextView
    lateinit var txtDetailStock: TextView
    lateinit var detailRating: TextView
    lateinit var btnAddCart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urun_detay)
        initView()
        fillView()
    }
    private fun initView() {
        imgDetail = findViewById(R.id.imgDetail)
        txtDetailModel = findViewById(R.id.txtDetailModel)
        txtDetailDesc = findViewById(R.id.txtDetailDesc)
        txtDetailPrice = findViewById(R.id.txtDetailPrice)
        txtDetailDiscount = findViewById(R.id.txtDetailDiscount)
        txtDetailStock = findViewById(R.id.txtDetailStock)
        detailRating = findViewById(R.id.detailRating)
    }

    private fun fillView() {
        Glide.with(this).load(Util.choosen!!.thumbnail).into(imgDetail)
        txtDetailModel.text = Util.choosen!!.title
        txtDetailDesc.text = Util.choosen!!.description
        txtDetailPrice.text = "Price: ${Util.choosen!!.price.toString()}"
        txtDetailDiscount.text = "Discount: ${Util.choosen!!.discountPercentage.toString()}$"
        txtDetailStock.text = "Stock: ${Util.choosen!!.stock.toString()}"
        detailRating.text = "Rating: ${Util.choosen!!.rating.toString()}"
    }

}
