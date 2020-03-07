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

class PermissionService(var context: Activity) : PermissionServiceInterface {

    private val sharedPreferences: SharedPreferences
    var mInterface: PermissionServiceInterface? = null

    override val buildSDK: Int
        get() = Build.VERSION.SDK_INT

    override fun getPermissions(): Array<String> = context.packageManager
            .getPackageInfo(context.packageName, PackageManager.GET_PERMISSIONS)
            .requestedPermissions

    init {
        mInterface = this
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context)
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun request(callback: Callback) =
            if (mInterface?.buildSDK!! >= Build.VERSION_CODES.M) {
                val permissions = mInterface!!.getPermissions()
                val (permissionsToRequest, permissionsRejected) =
                        permissions.filter { !hasPermission(it) }
                                .partition { sharedPreferences.getBoolean(it, true) }
                when {
                    permissionsToRequest.isNotEmpty() -> {
                        context.requestPermissions(permissionsToRequest.toTypedArray(), requestCode)
                        permissions.forEach {
                            with(sharedPreferences.edit()) {
                                putBoolean(it, false)
                                apply()
                            }
                        }
                    }
                    permissionsRejected.isNotEmpty() -> {
                        permissionsRejected.forEach {
                            with(sharedPreferences.edit()) {
                                putBoolean(it, false)
                                apply()
                            }
                        }
                        callback.onResponse(permissionsRejected)
                    }
                    else -> callback.onResponse(null)
                }
            } else
                callback.onResponse(null)

    @TargetApi(Build.VERSION_CODES.M)
    private fun hasPermission(permission: String): Boolean =
            if (mInterface?.buildSDK!! >= Build.VERSION_CODES.M)
                context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
            else true

    abstract class Callback : PermissionInterface

    companion object {
        var requestCode = 999

        fun handler(grantResults: IntArray, permissions: Array<String>, callback: Callback) {
            val permissionsRejected = ArrayList<String>()
            (1..grantResults.size).filter { (grantResults[it - 1] != 0) }.forEach {
                permissionsRejected.add(permissions[it - 1])
            }
            when {
                permissionsRejected.size > 0 -> callback.onResponse(permissionsRejected)
                else -> callback.onResponse(null)
            }
        }
    }

}
