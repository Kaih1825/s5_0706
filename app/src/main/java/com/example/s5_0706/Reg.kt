package com.example.s5_0706

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.TextClock
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.core.widget.addTextChangedListener
import com.example.s5_0706.adapter.HomeListAdapter.LoginActivity
import com.example.s5_0706.databinding.ActivityRegBinding

class Reg : AppCompatActivity() {
    lateinit var b:ActivityRegBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b=ActivityRegBinding.inflate(layoutInflater)
        setContentView(b.root)

        var adapter=ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,resources.getStringArray(R.array.country))
        b.country.setText(resources.getStringArray(R.array.country)[0])
        b.country.setAdapter(adapter)

        b.email.addTextChangedListener{
            var email=b.email.text.toString()
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
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

        b.pwd2.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (b.pwd.text.toString() != b.pwd2.text.toString()) {
                        b.pwd2.error = "兩次密碼不同"
                    }
                }
                return true
            }
        })

        b.country.setOnItemClickListener { parent, view, position, id ->
            if(resources.getStringArray(R.array.country).indexOf(b.country.text.toString())==1){
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("en-US"))
            }else{
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("zh-TW"))
            }
            b.country.setText("")
            Log.e("TAG", "", )
        }



    }
}