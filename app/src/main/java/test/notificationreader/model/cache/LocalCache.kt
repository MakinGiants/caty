package test.notificationreader.model.cache

import android.content.Context

class LocalCache(context: Context) : Cache {
    var prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    @Suppress("UNCHECKED_CAST")
    override fun <U> get(name: String, default: U): U = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw java.lang.IllegalArgumentException("Type not allowed.")
        }

        res as U
    }

    override fun <U> put(name: String, value: U) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw java.lang.IllegalArgumentException("Type not allowed.")
        }.apply()
    }
}
