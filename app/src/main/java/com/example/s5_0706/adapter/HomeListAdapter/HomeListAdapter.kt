package com.example.s5_0706.adapter.HomeListAdapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.s5_0706.databinding.HomeListBinding
import org.json.JSONArray
import org.json.JSONObject

class HomeListAdapter(var context:Context,var jsonArray: JSONArray):BaseAdapter() {
    override fun getCount(): Int {
        return jsonArray.length()
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var b=HomeListBinding.inflate((context as Activity).layoutInflater)
        try{
            b.date.text=(jsonArray[position] as JSONObject).getString("日期")
            b.title.text=(jsonArray[position] as JSONObject).getString("標題")
        }catch(ex:Exception){
            b.date.text=(jsonArray[position] as JSONObject).getString("Date")
            b.title.text=(jsonArray[position] as JSONObject).getString("Title")
        }

        return b.root
    }
}