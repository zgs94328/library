package com.zgs.zgslibrarydemo

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * 作者　: zgs
 * 时间　: 8/12/21
 * 描述　:
 */
@Route(path = "/main/home")
class HomeActivity : AppCompatActivity() , IProvider{
    var handlerThread = HandlerThread("handlerThread")


    @Autowired
    lateinit var zgsName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handlerThread.start()
        var handler = object : Handler(handlerThread.looper){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                if(msg.what==1){
                    for (i in 0..10){
                        println("------$i")
                    }
                }else{
                    for (i in 0..10){
                        println("------${i+20}")
                    }
                }
            }
        }
        var msg = Message.obtain()
        msg.what=1
        handler.sendMessage(msg)
        var msg2= Message.obtain()
        msg2.what = 2
        handler.sendMessage(msg2)
    }

    override fun init(context: Context?) {

    }
}