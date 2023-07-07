package com.example.s5_0706

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.s5_0706.Fragment.HomeFragment
import com.example.s5_0706.adapter.HomeListAdapter.HomeListAdapter
import com.example.s5_0706.adapter.HomeListAdapter.LoginActivity
import com.example.s5_0706.databinding.ActivityMainBinding
import com.example.s5_0706.databinding.HomeListBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    lateinit var b:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b= ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        var fm=supportFragmentManager.beginTransaction()
        fm.add(R.id.layout,HomeFragment()).commit()
        b.open.setOnClickListener{
            b.name.text= SqlMethods.User(this).getName(getSharedPreferences("sp",Context.MODE_PRIVATE).getString("email","請登入")!!)
            b.drawer.open()
        }
        if(getSharedPreferences("sp",Context.MODE_PRIVATE).getBoolean("isLogin",false)){
            b.login.text=resources.getString(R.string.logout)
        }else{
            b.login.text=resources.getString(R.string.login)
        }
        b.login.setOnClickListener {
            if(b.login.text==R.string.login.toLocalString()){
                startActivity(Intent(this,LoginActivity::class.java))
            }else{
                var sp=getSharedPreferences("sp",Context.MODE_PRIVATE).edit()
                sp.putBoolean("isLogin",false)
                sp.putString("email","請登入")
                sp.apply()
                b.name.text= SqlMethods.User(this).getName(getSharedPreferences("sp",Context.MODE_PRIVATE).getString("email","請登入")!!)
                if(getSharedPreferences("sp",Context.MODE_PRIVATE).getBoolean("isLogin",false)){
                    b.login.text=R.string.logout.toLocalString()
                }else{
                    b.login.text=R.string.login.toLocalString()
                }
            }
        }

    }

    fun Int.toLocalString(): String {
        return resources.getString(this)
    }
}