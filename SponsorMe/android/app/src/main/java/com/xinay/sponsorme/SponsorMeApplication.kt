package com.xinay.sponsorme

class SponsorMeApplication : Application() {

    val dataRepository: DataRepository by lazy {
        val settingsFactory = PlatformSettings(applicationContext)
        KotlinConfDataRepository(END_POINT, getUserId(), settingsFactory)
    }

    override fun onCreate() {
        super.onCreate()

        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            println(throwable)
            throwable.printStackTrace()
            throwable?.cause?.printStackTrace()
        }

        // Initialize Mapbox
        Mapbox.getInstance(this, BuildConfig.MAPBOX_ACCESS_TOKEN)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun getUserId(): String = "android-" + UUID.randomUUID().toString()
}