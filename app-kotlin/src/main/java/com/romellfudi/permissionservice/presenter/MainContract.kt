package com.romellfudi.permissionservice.presenter

import android.view.View

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 2020-04-26
 */
interface MainContract {

    interface MainView {
        val view: View
        fun showOK()
        fun showError(error: String)
    }
}