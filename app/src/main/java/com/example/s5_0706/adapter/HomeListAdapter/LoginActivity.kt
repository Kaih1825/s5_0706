package com.example.s5_0706.adapter.HomeListAdapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.example.s5_0706.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var b:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.email.addTextChangedListener{
            var email=b.email.text.toString()
            if(!Regex("^[A-Z,a-z,0-9]+@[A-z,a-z,0-9]+\\.[A-z,a-z,0-9]{2,4}$").matches(email)){
                b.email.error="格式錯誤"
            }else{
                b.email.error=null
            }
        }

        b.pwd.addTextChangedListener {
            var pwd=b.pwd.text.toString()
            if(pwd.length<8 || pwd.length>15 || pwd.contains(Regex("[\\s]")) || !(pwd.contains(Regex("[A-Z]")) && pwd.contains(Regex("[a-z]")) && pwd.contains(Regex("[0-9]")) && pwd.contains(Regex("[\\W]")))){
                b.pwd.error="格式錯誤"
            }else{
                b.pwd.error=null
            }
        }
    }
}