package net.prunusmume.kotlinsample.di

import javax.inject.Singleton

import dagger.Component
import net.prunusmume.kotlinsample.ui.activity.MainActivity

/**
 * Created by umetsu_kentaro on 15/10/14.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(activity: MainActivity)
}
