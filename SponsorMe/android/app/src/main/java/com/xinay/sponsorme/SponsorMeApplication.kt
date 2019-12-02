package com.xinay.sponsorme

import android.app.Application
import android.content.Context
import com.xinay.sponsorme.api.END_POINT
import com.xinay.sponsorme.storage.PlatformSettings
import com.xinay.sponsorme.presentation.DataRepository
import java.util.*

class SponsorMeApplication : Application() {

    val dataRepository: DataRepository by lazy {
        val settingsFactory = PlatformSettings(applicationContext)
        SponsorMeDataRepository(END_POINT, getUserId(), settingsFactory)
    }

    override fun onCreate() {
        super.onCreate()

        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            println(throwable)
            throwable.printStackTrace()
            throwable?.cause?.printStackTrace()
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        //MultiDex.install(this)
    }

    private fun getUserId(): String = "android-" + UUID.randomUUID().toString()
}