package net.prunusmume.kotlinsample

import android.app.Application

/**
 * Created by umetsu_kentaro on 15/11/18.
 */
public class SampleApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}