package test.notificationreader.model.cache

import android.content.Context
import android.provider.Settings
import java.lang.ref.WeakReference

open class Settings(context: Context) {
    /**
     * Also to be found in {@link android.provider.Settings.Secure}
     * but is hidden https://code.google.com/p/android/issues/detail?id=58030
     */
    private val ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";

    val weakContextReference = WeakReference(context)
    val localCache = LocalCache(context)

    open var playJustWithHeadphones: Boolean
        get() = localCache.get("headphones", false)
        set(value) = localCache.put("headphones", value)

    open var readNotificationEnabled: Boolean
        get() = localCache.get("read_notification", true)
        set(value) = localCache.put("read_notification", value)

    open val notificationPermissionGranted: Boolean
        get() = settingIsOn(Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES) ||
                settingIsOn(ENABLED_NOTIFICATION_LISTENERS)

    /**
     * Check if a setting is inside the enabled ones
     */
    private fun settingIsOn(settingString: String?): Boolean {
        val context = weakContextReference.get() ?: return false
        val accessibilityFound: Boolean

        try {
            val contentResolver = context.applicationContext.contentResolver
            val secureString = Settings.Secure.getString(contentResolver, settingString)

            accessibilityFound = secureString?.contains(context.packageName) ?: false
        } catch (e: Settings.SettingNotFoundException) {
            accessibilityFound = false
        }

        return accessibilityFound
    }
}