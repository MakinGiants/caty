package com.makingiants.caty

import com.makingiants.caty.screens.settings.SettingsActivity
import com.makingiants.caty.screens.welcome.WelcomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
  fun inject(catyApplication: CatyApplication)
  fun inject(welcomeActivity: WelcomeActivity)
  fun inject(settingsActivity: SettingsActivity)
}