package com.cankarademir.cankarademir_odev6

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.cankarademir.cankarademir_odev6.configs.ApiClient
import com.cankarademir.cankarademir_odev6.models.User
import com.cankarademir.cankarademir_odev6.models.UserData
import com.cankarademir.cankarademir_odev6.services.DummyService
import com.cankarademir.cankarademir_odev6.configs.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
// Hocam sınav haftası ve hastalığım dolayısıyla çok çalışamadım bu kısımlara internetten kaynak araştırmalarıyla ve dersten hatırladığım
// kadrıyla yaptım eksiklerim ve yanlışlarımı mazur görün çalışma tempomu dahada arttıracağım.

class MainActivity : AppCompatActivity() {

    lateinit var editTextTextEmailAddress: EditText
    lateinit var editTextTextPassword: EditText
    lateinit var loginButton: Button
    lateinit var dummyService: DummyService

    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("users", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        dummyService = ApiClient.getClient().create(DummyService::class.java)

        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress)
        editTextTextPassword = findViewById(R.id.editTextNumberPassword)
        loginButton = findViewById(R.id.buttonLogin)


        val btnOnClickListener = View.OnClickListener {
            val username = editTextTextEmailAddress.text.toString()
            val pass = editTextTextPassword.text.toString()
            val User = User(username,pass)

            if(username==""||pass==""){
                val toast = Toast.makeText(applicationContext, "Lütfen Kullanıcı Adı veya Şifreyi Boş Bırakmayın", Toast.LENGTH_LONG)
                toast.show()
            }
            else {
            dummyService.login(User).enqueue(object : Callback<UserData> {
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                    val User = response.body()
                    Log.d("status", response.code().toString())
                    if (User != null) {
                        Util.user = User
                        Log.d("User", User.toString())

                        editor.putString("username", User.username)
                        editor.putString("firstName", User.firstName)
                        editor.commit()

                        val intent = Intent(this@MainActivity, UrunListesiActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
                override fun onFailure(call: Call<UserData>, t: Throwable) {
                    Log.e("login", t.toString())
                    Toast.makeText(this@MainActivity, "Internet or Server Fail", Toast.LENGTH_LONG).show()
                }
            })
        }
        }

        loginButton.setOnClickListener(btnOnClickListener)
    }
}