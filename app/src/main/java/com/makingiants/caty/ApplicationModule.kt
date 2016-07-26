package com.makingiants.caty

import android.content.Context
import com.makingiants.caty.model.cache.Settings
import com.makingiants.caty.screens.welcome.WelcomePresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val application: CatyApplication) {

  @Provides @Singleton
  fun provideContext(): Context = application

  @Provides @Singleton
  fun provideWelcomePresenter(): WelcomePresenter = WelcomePresenter(Settings(application))

}