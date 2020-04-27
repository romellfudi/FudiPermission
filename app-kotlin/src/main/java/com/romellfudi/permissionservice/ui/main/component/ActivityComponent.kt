/*
 * Copyright (c) 2020. BoostTag E.I.R.L. Romell D.Z.
 * All rights reserved
 * porfile.romellfudi.com
 */

package com.romellfudi.permissionservice.ui.main.component

import com.romellfudi.permissionservice.di.ActivityScope
import com.romellfudi.permissionservice.ui.main.ActivityModule
import com.romellfudi.permissionservice.ui.main.view.MainActivity
import dagger.Component

/**
 * @autor Romell Domínguez
 * @date 2020-04-20
 * @version 1.0
 */
@ActivityScope
@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)
}