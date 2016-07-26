package com.makingiants.caty

import android.app.Application
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

class CatyApplication : Application() {

  val applicationComponent: ApplicationComponent by lazy {
    DaggerApplicationComponent.builder()
        .applicationModule(ApplicationModule(this))
        .build()
  }

  override fun onCreate() {
    super.onCreate()
    Fabric.with(this, Crashlytics())
  }

}