package com.example.s5_0706.adapter.HomeListAdapter

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.s5_0706.MainActivity
import com.example.s5_0706.R
import com.example.s5_0706.Reg
import com.example.s5_0706.SqlMethods
import com.example.s5_0706.databinding.ActivityLoginBinding
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    lateinit var b:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.email.addTextChangedListener{
            var email=b.email.text.toString()
            if(!Regex("^[A-Z,a-z,0-9]+@[A-z,a-z,0-9]+\\.[A-z,a-z,0-9]{2,4}$").matches(email)){
                b.email.error= R.string.wrongFormat.toLocalString()
            }else{
                b.email.error=null
            }
        }

        b.pwd.addTextChangedListener {
            var pwd=b.pwd.text.toString()
            if(pwd.length<8 || pwd.length>15 || pwd.contains(Regex("[\\s]")) || !(pwd.contains(Regex("[A-Z]")) && pwd.contains(Regex("[a-z]")) && pwd.contains(Regex("[0-9]")) && pwd.contains(Regex("[\\W]")))){
                b.pwd.error=R.string.wrongFormat.toLocalString()
            }else{
                b.pwd.error=null
            }
        }

        b.login.setOnClickListener {
            if(b.pwd.text.toString().isNotEmpty() && b.pwd.error==null && b.email.text.toString().isNotEmpty() && b.email.error==null){
                if(SqlMethods.User(this).login(b.email.text.toString(),b.pwd.text.toString())){
                    startActivity(Intent(this,MainActivity::class.java))
                }else{
                    b.login.text=R.string.loginError.toLocalString()
                    b.login.setTextColor(Color.RED)
                }
            }
        }

        b.reg.setOnClickListener {
            startActivity(Intent(this, Reg::class.java))
        }
    }

    fun Int.toLocalString(): String {
        return resources.getString(this)
    }
}