package com.zgs.zgslibrarydemo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.zgs.baselibrary.widget.SuperLayout
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.reactivestreams.Subscription
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.Flow

class MainActivity : AppCompatActivity() {

    var myLiveData = MutableLiveData<Any>()


    var liveData = MutableLiveData<String>()

    companion object {
        val handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        ll_1.setOnClickListener {
//            println("-----------1")
//        }

//        ll_3.setOnClickListener {
//            println("-----------3")
//        }
//        findViewById<SuperLayout>(R.id.item_click).setOnClickListener {
//            ARouter.getInstance().build("/main/home").navigation()
//        }
//        startActivity(Intent(this, MainActivity.javaClass))
////        handler.sendMessage(Message())
        val retrofit = Retrofit.Builder().baseUrl("wwww.baidu.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val githubApi = retrofit.create(GithubApi::class.java)
        val call = githubApi.getData()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
            }

        })

//        liveData.observe(this, androidx.lifecycle.Observer {
//
//        })
//        myLiveData.value = "11"
//
        val okHttpClient = OkHttpClient.Builder().build()
        val request = Request.Builder().url("wwww.baidu.com").get().build()
        val okhttpCall = okHttpClient.newCall(request)
        okhttpCall.enqueue(object : okhttp3.Callback {


            override fun onFailure(call: okhttp3.Call, e: IOException) {
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
            }


        })
//        myLiveData.observe(this, androidx.lifecycle.Observer {
//
//        })
//        println("-------ok")
//        Flowable.create(FlowableOnSubscribe<String> {
//            it.onNext("1");
//            it.onNext("1");
//            it.onNext("1");
//            it.onNext("1");
//            it.onNext("1");
//            it.onNext("1");
//
//        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : FlowableSubscriber<String> {
//                override fun onComplete() {
//                }
//
//                override fun onSubscribe(s: Subscription) {
//                    s.request(3)
//                }
//
//                override fun onNext(t: String?) {
//                    try {
//                        // 接收事件速度：5s / 个
//                        Thread.sleep(5000);
//                        println("-------next")
//
//                    } catch (e: InterruptedException) {
//                        e.printStackTrace();
//                    }
//                }
//
//                override fun onError(t: Throwable?) {
//                }
//
//            })
//        Observable.create<String> { emitter ->
//            println(Thread.currentThread().name+"------")
//            emitter.onNext("11")
//
//        }.subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : Observer<String?> {
//            override fun onSubscribe(d: Disposable) {
//                println("-------end")
//            }
//
//            override fun onError(e: Throwable) {}
//            override fun onComplete() {
//                println("-------complete")
//            }
//
//            override fun onNext(t: String) {
//                println(Thread.currentThread().name+"------")
//
//            }
//        })

//        val invocationHandler = object : InvocationHandler {
//            override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
//                println("动态代理")
//                return ""
//            }
//
//        }
//
//        val helloApi = Proxy.newProxyInstance(
//            HelloApi::class.java.classLoader,
//            arrayOf(HelloApi::class.java), invocationHandler
//        ) as HelloApi
//        helloApi.say()
    }


}


