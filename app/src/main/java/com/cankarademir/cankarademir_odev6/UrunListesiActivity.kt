package com.cankarademir.cankarademir_odev6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import com.cankarademir.cankarademir_odev6.adapter.ProductListViewAdapter
import com.cankarademir.cankarademir_odev6.configs.ApiClient
import com.cankarademir.cankarademir_odev6.configs.Util
import com.cankarademir.cankarademir_odev6.models.AllProducts
import com.cankarademir.cankarademir_odev6.models.Product
import com.cankarademir.cankarademir_odev6.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UrunListesiActivity : AppCompatActivity() {
    lateinit var dummyService: DummyService
    lateinit var productListView: ListView
    lateinit var araBtn: ImageButton
    lateinit var aramaEditText:EditText
    var productList= mutableListOf<Product>()
    lateinit var  customAdapter: ArrayAdapter<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urun_listesi)
        productListView = findViewById(R.id.productListView)

        dummyService = ApiClient.getClient().create(DummyService::class.java)
        dummyService.getProducts(10).enqueue(
            object : Callback<AllProducts> {
                override fun onResponse(
                    call: Call<AllProducts>,
                    response: Response<AllProducts>
                ) {
                    val products = response.body()
                    if (products != null) {
                        for (product in products!!.products) {
                            productList.add(product)
                        }
                        val adapter: ProductListViewAdapter =
                            ProductListViewAdapter(this@UrunListesiActivity, productList)
                        productListView.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<AllProducts>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(this@UrunListesiActivity, "Service Failure", Toast.LENGTH_LONG)
                        .show()
                }
            }
        )

        //araBtn.setOnClickListener(btnAraOnClickListener)

        productListView.setOnItemClickListener { adapterView, view, i, l ->
            Util.choosen = productList[i]
            val intent = Intent(this, UrunDetayActivity::class.java)
            startActivity(intent)
        }
    }

    val btnAraOnClickListener=View.OnClickListener {
        val aramaText=aramaEditText.text.toString()
        dummyService.searchProduct(aramaText).enqueue(object :Callback<AllProducts>{
            override fun onResponse(call: Call<AllProducts>, response: Response<AllProducts>) {
                Log.d("searchProduct",response.body().toString())
                productList=productList
                productList.clear()
                for(product in response.body()!!.products){
                    productList.add(product)
                }
                customAdapter.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<AllProducts>, t: Throwable) {
                Log.e("eroor","server error")
            }

        })

    }

}




