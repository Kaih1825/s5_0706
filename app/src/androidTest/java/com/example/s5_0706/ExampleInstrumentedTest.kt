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
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import com.google.android.material.internal.ContextUtils.getActivity
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
                waitForExists(1000)
                setText("aa123@mail.com")
                onView(withId(R.id.email)).check(ViewAssertions.matches(not(hasErrorText("格式錯誤"))))
            }
            device.findObject(UiSelector().resourceId("$dn:id/pwd")).run {
                click()
                setText("AAAAAAAA")
                onView(withId(R.id.pwd)).check(ViewAssertions.matches(hasErrorText("格式錯誤")))

                setText("aaaaaaaa")
                onView(withId(R.id.pwd)).check(ViewAssertions.matches(hasErrorText("格式錯誤")))

                setText("123456789")
                onView(withId(R.id.pwd)).check(ViewAssertions.matches(hasErrorText("格式錯誤")))

                setText("Aa12345678")
                onView(withId(R.id.pwd)).check(ViewAssertions.matches(hasErrorText("格式錯誤")))

                setText("Aa12")
                onView(withId(R.id.pwd)).check(ViewAssertions.matches(hasErrorText("格式錯誤")))


                setText("AAAAAAAAa1234567/89***89898")
                onView(withId(R.id.pwd)).check(ViewAssertions.matches(hasErrorText("格式錯誤")))

                setText("Aa123456!!")
                onView(withId(R.id.pwd)).check(ViewAssertions.matches(not(hasErrorText("格式錯誤"))))

            }

            device.findObjects(By.res("$dn:id/text_input_end_icon"))[0].click()
            device.findObject(UiSelector().text("Aa123456!!")).run {
                assertEquals("",waitForExists(5000),true)
            }

            device.findObjects(By.text("登入"))[1].click()

            device.findObject(UiSelector().text("登入失敗")).run {
                assertEquals(waitForExists(1000),true)
            }

        }
    }
}