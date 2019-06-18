package com.romellfudi.permission

import android.Manifest.permission.*
import android.app.Activity
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.preference.PreferenceManager
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.powermock.core.classloader.annotations.PrepareForTest
import java.util.*

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 10/4/18
 */
@PrepareForTest(PreferenceManager::class)
class FudiPermisionTest {

    @MockK
    lateinit var activity: Activity

    @MockK
    lateinit var permissionService: PermissionService

    @MockK
    lateinit var callback: PermissionService.Callback

    @MockK
    lateinit var sharedPreferences: SharedPreferences

    @Mock
    internal var editor: SharedPreferences.Editor = mock()

    @MockK
    lateinit var permisionServiceInterface: PermisionServiceInterface

    val refusePemissions = slot<ArrayList<String>>()

    val permissions = arrayOf(ACCESS_FINE_LOCATION, CAMERA, WRITE_EXTERNAL_STORAGE)

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        mockkStatic(PreferenceManager::class)
        every { PreferenceManager.getDefaultSharedPreferences(any()) } returns sharedPreferences
        permissionService = PermissionService(activity)
        permissionService.permisionServiceInterface = permisionServiceInterface
        i = 0
        every { permisionServiceInterface.buildSDK } returns Build.VERSION_CODES.M
        every { activity.checkSelfPermission(any()) } returns PackageManager.PERMISSION_DENIED
        every { sharedPreferences.getBoolean(any(), any()) } returns true
        every { sharedPreferences.edit() } returns editor
    }

    @Test
    fun requestLessM() {
        every { permisionServiceInterface.buildSDK } returns Build.VERSION_CODES.M - 1
        permissionService.request(permissions, callback)
        verify { callback.onFinally() }
    }

    companion object {
        var i = 0
    }

    @Test
    fun request3Permissions3Accepted() {
        whenever(editor.apply()).thenAnswer {
            i++
            if (i == permissions.size)
            // 0 accepted, 1 refuse
                PermissionService.handler(callback, intArrayOf(0, 0, 0), permissions)
        }
        permissionService.request(permissions, callback)

        verify { callback.onFinally() }
    }

    @Test
    fun request3Permissions2Accepted() {
        whenever(editor.apply()).thenAnswer {
            i++
            if (i == permissions.size)
            // 0 accepted, 1 refuse
                PermissionService.handler(callback, intArrayOf(0, 1, 0), permissions)
        }
        permissionService.request(permissions, callback)
        verify { callback.onRefuse(capture(refusePemissions)) }

        val refusePermissions = refusePemissions.captured
        assertThat(refusePermissions[0], `is`(equalTo(CAMERA)))
    }


    @Test
    fun request3Permissions3Reject() {
        whenever(editor.apply()).thenAnswer {
            i++
            if (i == permissions.size)
            // 0 accepted, 1 refuse
                PermissionService.handler(callback, intArrayOf(1, 1, 1), permissions)
        }
        permissionService.request(permissions, callback)
        verify { callback.onRefuse(capture(refusePemissions)) }

        val refusePermissions = refusePemissions.captured
        assertThat(refusePermissions[0], `is`(equalTo(ACCESS_FINE_LOCATION)))
        assertThat(refusePermissions[1], `is`(equalTo(CAMERA)))
        assertThat(refusePermissions[2], `is`(equalTo(WRITE_EXTERNAL_STORAGE)))
    }
}