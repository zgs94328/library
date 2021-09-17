package com.zgs.zgslibrarydemo

import retrofit2.Call
import retrofit2.http.GET

/**
 * 作者　: zgs
 * 时间　: 7/20/21
 * 描述　:
 */
interface GithubApi {
    @GET("/users/{user}/repos")
    fun getData(): Call<String>
}