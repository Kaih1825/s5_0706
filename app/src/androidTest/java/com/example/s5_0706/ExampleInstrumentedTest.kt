package com.example.s5_0706

import android.content.Context
import android.content.SharedPreferences
import android.graphics.ColorSpace.match
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.*
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.not

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.w3c.dom.Text
import java.util.regex.Pattern.matches


@RunWith(AndroidJUnit4::class)
class startTesting {
    val device=UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    val context=InstrumentationRegistry.getInstrumentation().context
    var dn=device.currentPackageName

    @Before
    fun setUp(){
        launch(MainActivity::class.java)
        dn=device.currentPackageName
    }

    @Test
    fun home(){
        device.findObject(UiSelector().text("活動展示")).run {
            assertEquals("",waitForExists(1000),true)
        }
        device.findObject(UiSelector().resourceId("$dn:id/open")).run {
            click()
        }
        device.findObject(UiSelector().text("請登入")).run {
            var sp=context.getSharedPreferences("sp",Context.MODE_PRIVATE)
            assertEquals("",waitForExists(1000),!sp.getBoolean("isLogin",false))
        }
    }

    @Test
    fun login(){
        var sp=context.getSharedPreferences("sp",Context.MODE_PRIVATE)
        if(!sp.getBoolean("isLogin",false)){
            device.findObject(UiSelector().resourceId("$dn:id/open")).click()
            device.findObject(UiSelector().resourceId("$dn:id/login")).click()
            device.findObject(UiSelector().resourceId("$dn:id/email")).run {
                click()
                setText("aa123.com")
                onView(withId(R.id.email)).check(ViewAssertions.matches(hasErrorText("格式錯誤")))
                clearTextField()
                waitForExists(1000)
                setText("aa123@mail.com")
                onView(withId(R.id.email)).check(ViewAssertions.matches(not(hasErrorText("格式錯誤"))))
            }
        }
    }
}