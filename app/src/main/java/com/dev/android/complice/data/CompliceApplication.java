
package com.dev.android.complice.data;

import android.app.Application;
import android.content.Context;

import com.dev.android.complice.data.IO.ApiAdapter;
import com.dev.android.complice.data.IO.ApiServices;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class CompliceApplication extends Application {

  private ApiServices apiServices;
  private Scheduler scheduler;

  private static CompliceApplication get(Context context) {
    return (CompliceApplication) context.getApplicationContext();
  }

  public static CompliceApplication create(Context context) {
    return CompliceApplication.get(context);
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
