package com.romellfudi.permission

import java.util.ArrayList

/**
 *
 * @author Romell Dominguez
 * @version 1.0.a 16/07/2018
 * @since 1.0
 */

interface PermisionInterface {
    fun onRefuse(refusePermissions: ArrayList<String>)
    fun onFinally()
}
