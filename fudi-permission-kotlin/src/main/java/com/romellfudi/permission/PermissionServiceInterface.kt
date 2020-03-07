package com.romellfudi.permission

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 10/4/18
 */

interface PermissionServiceInterface {
    val buildSDK: Int
    fun getPermissions():Array<String>
}
