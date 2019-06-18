package com.romellfudi.permission

import android.annotation.TargetApi
import android.app.Activity
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.preference.PreferenceManager
import java.util.*

/**
 * @author Romell Dominguez
 * @version 1.0.a 23/02/2017
 * @since 1.0
 */

class PermissionService(var context: Activity) : PermisionServiceInterface {

    private val sharedPreferences: SharedPreferences
    private var permissionsToRequest: ArrayList<String>? = null
    private var permissionsRejected: ArrayList<String>? = null
    var permisionServiceInterface: PermisionServiceInterface? = null

    override val buildSDK: Int
        get() = Build.VERSION.SDK_INT

    init {
        permisionServiceInterface = this
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context)
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun request(permissionsArray: Array<String>, callback: Callback) {
        if (permisionServiceInterface?.buildSDK!! >= Build.VERSION_CODES.M) {
            val permissions = ArrayList<String>()
            for (perm in permissionsArray)
                permissions.add(perm)
            permissionsToRequest = missAllowPermissions(permissions)
            permissionsRejected = blockPermissions(permissions)
            if (permissionsToRequest!!.size > 0) {
                context.requestPermissions(permissionsToRequest!!.toTypedArray(), requestCode)
                for (perm in permissions) {
                    sharedPreferences.edit().putBoolean(perm, false)
                    sharedPreferences.edit().apply()
                }
            } else if (permissionsRejected!!.size > 0) {
                for (perm in permissionsRejected!!) {
                    sharedPreferences.edit().putBoolean(perm, false)
                    sharedPreferences.edit().apply()
                }
                callback.onRefuse(permissionsRejected!!)
            } else
                callback.onFinally()
        } else
            callback.onFinally()

    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun hasPermission(permission: String): Boolean {
        return if (permisionServiceInterface?.buildSDK!! >= Build.VERSION_CODES.M)
            context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
        else true
    }

    private fun missAllowPermissions(permissions: ArrayList<String>): ArrayList<String> {
        val result = ArrayList<String>()
        for (perm in permissions) {
            if (!hasPermission(perm) && sharedPreferences.getBoolean(perm, true))
                result.add(perm)
        }
        return result
    }

    private fun blockPermissions(permissions: ArrayList<String>): ArrayList<String> {
        val result = ArrayList<String>()
        for (perm in permissions)
            if (!hasPermission(perm) && !sharedPreferences.getBoolean(perm, true))
                result.add(perm)
        return result
    }


    abstract class Callback : PermisionInterface

    companion object {
        var requestCode = 999

        fun handler(callback: Callback, grantResults: IntArray, permissions: Array<String>) {
            val permissionsRejected = ArrayList<String>()
            (1..grantResults.size).forEach {
                if (grantResults[it-1] != 0)
                    permissionsRejected.add(permissions[it-1])
            }
            if (permissionsRejected.size > 0)
                callback.onRefuse(permissionsRejected)
            else
                callback.onFinally()
        }
    }

}
