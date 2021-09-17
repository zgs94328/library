package com.zgs.zgslibrarydemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route

/**
 * 作者　: zgs
 * 时间　: 8/12/21
 * 描述　:
 */
@Route(path = "/zgs/hahaha")
class MeActivity : AppCompatActivity() {
    @Autowired
    lateinit var zgsName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}