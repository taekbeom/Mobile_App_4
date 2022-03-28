package ru.mirea.lebedeva.looper;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.util.concurrent.ThreadLocalRandom;

public class MyLooper extends Thread{
    private int number = 0;
    Handler handler;
    int timeSleep = ThreadLocalRandom.current().nextInt(1, 100);;
    @SuppressLint("HandlerLeak")
    @Override
    public void run(){
        Log.d("MyLooper", "run");
        Looper.prepare();
        handler = new Handler(){

            @Override
            public void handleMessage(Message msg){

                timeSleep = ThreadLocalRandom.current().nextInt(1, 100);

                Log.d("MyLooper", number + ":" + msg.getData().getString("KEY"));
                Log.d("MyLooper", "Occupation: " + msg.getData().getString("OCC"));
                Log.d("MyLooper", "Age: " + msg.getData().getInt("AGE"));
                number++;
            }
        };
        Looper.loop();
    }
}
