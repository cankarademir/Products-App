package com.cankarademir.cankarademir_odev6.services

import com.cankarademir.cankarademir_odev6.models.AllProducts
import com.cankarademir.cankarademir_odev6.models.Product
import com.cankarademir.cankarademir_odev6.models.UserData
import com.cankarademir.cankarademir_odev6.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DummyService {

    @POST("/auth/login")
    fun login( @Body User: User): Call<UserData>

    @GET("/products")
    fun getProducts(@Query("limit") limit: Int): Call<AllProducts>


    @GET("/products/search")
    fun searchProduct(@Query("q") q : String) : Call<AllProducts>
}