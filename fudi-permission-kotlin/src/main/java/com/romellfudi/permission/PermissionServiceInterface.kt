package com.romellfudi.permission

import android.app.Activity

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 10/4/18
 */

interface PermissionServiceInterface {
    val buildSDK: Int
    fun getPermissions(context: Activity): Array<String>
}
