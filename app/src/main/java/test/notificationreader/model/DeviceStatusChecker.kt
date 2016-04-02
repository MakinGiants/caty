package test.notificationreader.model

import android.content.Context
import android.media.AudioManager

/**
 * Know how to check if the phone have the headphones connected or not.
 */
open class DeviceStatusChecker(context: Context) {
  val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

  @Suppress("DEPRECATION")
  open val headphonesConnected: Boolean
    get() = audioManager.isWiredHeadsetOn
}
