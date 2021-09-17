package com.zgs.zgslibrarydemo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 作者　: zgs
 * 时间　: 8/31/21
 * 描述　:
 */
public class MainAc extends AppCompatActivity {
    private static Object lock = new Object();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new ThreadA()).start();

        new Thread(new ThreadB()).start();
    }
    class ThreadA implements Runnable{
        

        @Override
        public void run() {
            synchronized (lock){
                for (int i = 0; i<20;i++){
                    System.out.println("Thread A=="+i);
                }
            }

        }
    }
    class ThreadB implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                for (int i = 0; i<20;i++){
                    System.out.println("Thread A=="+i);
                }
            }
        }
    }
}
