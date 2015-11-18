package net.prunusmume.kotlinsample.di

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import net.prunusmume.kotlinsample.network.GithubService
import net.prunusmume.kotlinsample.SampleApplication
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory

/**
 * Created by umetsu_kentaro on 15/10/14.
 */
@Module
class AppModule(private val mApplication: SampleApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    fun provideGithubService(retrofit: Retrofit): GithubService {
        return retrofit.create(GithubService::class.java)
    }
}
