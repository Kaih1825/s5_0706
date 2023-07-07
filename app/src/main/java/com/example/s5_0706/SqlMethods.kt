package com.example.s5_0706

import android.content.Context
import android.database.sqlite.SQLiteDatabase

class SqlMethods {
    class User(var context: Context){
        var db:SQLiteDatabase = context.openOrCreateDatabase("db.db",Context.MODE_PRIVATE,null)

        init {
            try{
                db.execSQL("CREATE TABLE User(email TEXT PRIMARY KEY,pwd TEXT,name TEXT,countrySelection INTEGER)")
            }catch (ex:java.lang.Exception){}
        }

        fun insert(email:String,pwd:String,name:String,countrySelection:Int){
            db.execSQL("INSERT INTO User VALUES('$email','$pwd','$name',$countrySelection)")
            var sp=context.getSharedPreferences("sp",Context.MODE_PRIVATE).edit()
            sp.putBoolean("isLogin",true)
            sp.putString("email",email)
            sp.apply()
        }

        fun login(email: String,pwd:String): Boolean {
            var cursor=db.rawQuery("SELECT * FROM User WHERE email='$email'",null)
            if(cursor.count==0) return false
            cursor.moveToFirst()
            if(cursor.getString(1)==pwd){
                var sp=context.getSharedPreferences("sp",Context.MODE_PRIVATE).edit()
                sp.putBoolean("isLogin",true)
                sp.putString("email",email)
                sp.apply()
                return true
            }
            return false
        }

        fun getName(email: String): String {
            var cursor=db.rawQuery("SELECT * FROM User WHERE email='$email'",null)
            if(cursor.count==0){
                return context.resources.getString(R.string.pleaseLogin)
            }
            cursor.moveToFirst()
            return cursor.getString(2)
        }
    }


}