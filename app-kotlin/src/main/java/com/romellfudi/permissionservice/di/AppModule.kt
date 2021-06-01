/*
 * Copyright (c) 2021. BoostTag E.I.R.L. Romell D.Z.
 * All rights reserved
 * portfolio.romellfudi.com
 */

package com.romellfudi.permissionservice.di

import com.romellfudi.permission.PermissionService
import com.romellfudi.permissionservice.presenter.MainContract
import org.koin.dsl.module

/**
 * App Koin's Module
 *
 * @version 1.0.a
 * @autor Romell Domínguez (@romellfudi)
 * @date 4/1/21
 */

val appModule = module {

    single { PermissionService }

    single<PermissionService.Callback> { (mainView: MainContract.MainView) ->
        object : PermissionService.Callback() {
            override fun onResponse(refusePermissions: List<String>?) {
                refusePermissions?.apply {
                    if (isNotEmpty()) {
                        mainView.showError("You must allow all ${toString()}, to continue")
                        return@onResponse
                    }
                }
                mainView.showOK()
            }
        }
    }

}
