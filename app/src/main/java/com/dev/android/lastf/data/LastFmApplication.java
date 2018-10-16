
package com.dev.android.lastf.data;

import android.app.Application;
import android.content.Context;

import com.dev.android.lastf.data.IO.ApiAdapter;
import com.dev.android.lastf.data.IO.ApiServices;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class LastFmApplication extends Application {

  private ApiServices apiServices;
  private Scheduler scheduler;

  private static LastFmApplication get(Context context) {
    return (LastFmApplication) context.getApplicationContext();
  }

  public static LastFmApplication create(Context context) {
    return LastFmApplication.get(context);
  }

  public ApiServices getApiServices() {
    if (apiServices == null) {
      apiServices = ApiAdapter.getInstance().create(ApiServices.class);
    }

    return apiServices;
  }

  public Scheduler subscribeScheduler() {
    if (scheduler == null) {
      scheduler = Schedulers.io();
    }
    return scheduler;
  }

  public void setScheduler(Scheduler scheduler) {
    this.scheduler = scheduler;
  }
}
