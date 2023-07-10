package com.bclccasino.game

import android.app.Application
import com.onesignal.OneSignal

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        OneSignal.initWithContext(this)
        OneSignal.setAppId("d3747a46-1539-43ff-ac55-784bc31d402b")
    }
}