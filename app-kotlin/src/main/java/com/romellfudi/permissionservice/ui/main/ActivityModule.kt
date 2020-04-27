/*
 * Copyright (c) 2020. BoostTag E.I.R.L. Romell D.Z.
 * All rights reserved
 * porfile.romellfudi.com
 */

package com.romellfudi.permissionservice.ui.main

import android.os.Handler
import com.romellfudi.permission.PermissionService
import com.romellfudi.permissionservice.di.ActivityScope
import com.romellfudi.permissionservice.ui.main.presenter.MainContract
import dagger.Module
import dagger.Provides

/**
 * @autor Romell Domínguez
 * @date 2020-04-21
 * @version 1.0
 */
@Module
class ActivityModule(var mainView: MainContract.MainView) {

    @Provides
    @ActivityScope
    fun providePermissionService(): PermissionService {
        return PermissionService(mainView.activity)
    }

    @Provides
    @ActivityScope
    fun provideCallback(): PermissionService.Callback = object : PermissionService.Callback() {
        override fun onResponse(refusePermissions: List<String>?) {
            if (refusePermissions != null) {
                mainView.showError("Have to allow all permissions")
                Handler().postDelayed({ mainView.finish() }, 1500)
            } else
                mainView.initController()
        }
    }

}