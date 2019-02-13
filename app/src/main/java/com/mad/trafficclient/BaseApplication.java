package com.mad.trafficclient;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class BaseApplication extends Application {
    private static BaseApplication instance;
    private static RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        queue = Volley.newRequestQueue(this);
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static BaseApplication getInstance() {
        return instance;
    }

    /**
     * Gets queue.
     *
     * @return the queue
     */
    public static RequestQueue getQueue() {
        return queue;
    }
}
