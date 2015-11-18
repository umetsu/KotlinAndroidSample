package net.prunusmume.kotlinsample

import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

/**
 * Created by umetsu_kentaro on 15/11/18.
 */
public interface  GithubService {
    @GET("/users/{user}/repos")
    fun repositories(@Path("user") user : String) : Observable<MutableList<Repo>>
}