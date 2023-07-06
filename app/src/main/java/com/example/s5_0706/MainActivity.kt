package com.example.s5_0706

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            b.drawer.open()
        }
        b.login.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}