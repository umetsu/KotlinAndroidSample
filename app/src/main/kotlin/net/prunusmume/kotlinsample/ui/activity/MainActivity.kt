package net.prunusmume.kotlinsample.ui.activity

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import net.prunusmume.kotlinsample.R
import net.prunusmume.kotlinsample.SampleApplication
import net.prunusmume.kotlinsample.databinding.ActivityMainBinding
import net.prunusmume.kotlinsample.entity.Repo
import net.prunusmume.kotlinsample.network.GithubService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        private val LOG_TAG = MainActivity::class.java.simpleName
    }

    @Inject
    lateinit var githubService: GithubService

    private var mSubscription = Subscriptions.empty()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SampleApplication.appComponent.inject(this)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val adapter = ArrayAdapter<Repo>(this, android.R.layout.simple_list_item_1)
        binding.listView.adapter = adapter

        mSubscription = githubService.repositories("umetsu")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { repositories ->
                            adapter.addAll(repositories)
                        },
                        { error ->
                            Log.e(LOG_TAG, "error : $error")
                        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mSubscription.unsubscribe()
    }
}
