package com.romellfudi.permissionservice.ui.main.presenter

import android.app.Activity
import android.view.View

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 2020-04-26
 */
interface MainContract {

    interface MainView {
        fun initController()
        fun finish()
        var view: View
        fun showOK()
        fun showError(error: String)
        var activity: Activity
    }
}