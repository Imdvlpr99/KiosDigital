package id.imdvlpr.kiosdigital.utils.extension

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.scottyab.aescrypt.AESCrypt
import java.security.GeneralSecurityException

fun Context.pref(): SharedPreferences {
    return this.getSharedPreferences(Constants.PREF.NAME, Activity.MODE_PRIVATE)
}

fun Context.saveToPref(strKey: String, value: Any?) {
    /* checking value type */
    val editor = this.pref().edit()
    when (value) {
        is String   -> editor.putString(encryptPref(strKey), encryptPref(value))
        is Boolean  -> editor.putBoolean(encryptPref(strKey), value)
        is Float    -> editor.putFloat(encryptPref(strKey), value)
        is Int      -> editor.putInt(encryptPref(strKey), value)
        is Long     -> editor.putLong(encryptPref(strKey), value)
    }
    /* save pref */
    editor.apply()
}

fun Context.getStringFromPref(strKey: String): String {
    return this.pref().getString(encryptPref(strKey), "") ?: ""
}

fun Context.getBooleanFromPref(strKey: String): Boolean {
    return this.pref().getBoolean(encryptPref(strKey), false)
}

fun Context.getIntFromPref(strKey: String): Int {
    return this.pref().getInt(encryptPref(strKey), 0)
}

fun Context.deleteFromPref(strKey: String) {
    val editor = this.pref().edit()
    editor.remove(encryptPref(strKey))
    /* save pref */
    editor.apply()
}

/**
 * Encrypt Pref
 */
private fun encryptPref(value: String): String {
    val encrypted: String
    try {
        encrypted = AESCrypt.encrypt(Constants.PREF.PASSWORD_PREF, value)
    } catch (e: GeneralSecurityException) {
        //handle error
        return ""
    }
    return encrypted
}

/**
 * Decrypt Pref
 */
private fun decryptPref(value: String): String {
    val decrypted: String
    try {
        decrypted = AESCrypt.decrypt(Constants.PREF.PASSWORD_PREF, value)
    } catch (e: GeneralSecurityException) {
        //handle error
        return ""
    }
    return decrypted
}