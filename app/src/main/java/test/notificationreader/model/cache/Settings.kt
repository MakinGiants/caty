package test.notificationreader.model.cache

import android.content.Context

open class Settings(context: Context) {
    val localCache = LocalCache(context)

    open var playJustWithHeadphones: Boolean
        get() = localCache.get("headphones", false)
        set(value) = localCache.put("headphones", value)

    open var permissionGranted: Boolean
        get() = localCache.get("permissions", false)
        set(value) = localCache.put("permissions", value)

    open var readNotificationEnabled: Boolean
        get() = localCache.get("read_notification", true)
        set(value) = localCache.put("read_notification", value)
}