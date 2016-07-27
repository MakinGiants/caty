package com.makingiants.caty

import android.content.Context
import com.makingiants.caty.model.DeviceStatusChecker
import com.makingiants.caty.model.TextReader
import com.makingiants.caty.model.cache.Settings
import com.makingiants.caty.model.notifications.NotificationHandler
import com.makingiants.caty.model.notifications.Notifier
import com.makingiants.caty.screens.settings.SettingsPresenter
import com.makingiants.caty.screens.welcome.WelcomePresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val application: CatyApplication) {

  @Provides @Singleton
  fun provideContext(): Context = application

  @Provides
  fun provideWelcomePresenter() = WelcomePresenter(Settings(application))

  @Provides
  fun provideSettingsPresenter() = SettingsPresenter(Settings(application), Notifier(application))

  @Provides
  fun provideNotificationHandler() = NotificationHandler(Settings(application),
      TextReader(application), DeviceStatusChecker(application))

}