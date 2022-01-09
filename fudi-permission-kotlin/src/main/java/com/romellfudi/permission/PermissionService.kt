package com.romellfudi.permission

import android.annotation.TargetApi
import android.app.Activity
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.preference.PreferenceManager

/**
 * @author Romell Dominguez
 * @version 1.0.a 23/02/2017
 * @since 1.0
 */

object PermissionService : PermissionServiceInterface {

    private const val requestCode = 999

    abstract class Callback : PermissionInterface

    private lateinit var sharedPreferences: SharedPreferences
    var mInterface: PermissionServiceInterface? = null

    override val buildSDK: Int
        get() = Build.VERSION.SDK_INT

    override fun getPermissions(context: Activity): Array<String> = context.packageManager
        .getPackageInfo(context.packageName, PackageManager.GET_PERMISSIONS)
        .requestedPermissions

    init {
        mInterface = this
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun request(context: Activity, callback: Callback) =
        if (mInterface?.buildSDK!! >= Build.VERSION_CODES.M) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val permissions = mInterface?.getPermissions(context)
            setValues(permissions)
            context.requestPermissions(permissions, requestCode)
        } else callback.onResponse(null)

    private fun setValues(permissionsRejected: Array<String>?) = permissionsRejected?.forEach {
        with(sharedPreferences.edit()) {
            putBoolean(it, false)
            apply()
        }
    }

    fun handler(callback: Callback, grantResults: IntArray, permissions: Array<String>) {
        val permissionsRejected = mutableListOf<String>()
        (1..grantResults.size).filter { grantResults[it - 1] != 0 }
            .forEach { permissionsRejected.add(permissions[it - 1]) }
        callback.onResponse(if (permissionsRejected.isEmpty()) null else permissionsRejected)
    }

}
