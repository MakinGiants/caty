package test.notificationreader.model

import android.content.Context
import android.media.AudioManager

open class DeviceStatusChecker(context: Context) {
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    @Suppress("DEPRECATION")
    open val headphonesConnected: Boolean
        get() = audioManager.isWiredHeadsetOn
}
