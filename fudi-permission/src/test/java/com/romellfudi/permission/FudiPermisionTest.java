package com.romellfudi.permission;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doAnswer;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 10/4/18
 */
@RunWith(PowerMockRunner.class)
public class FudiPermisionTest {

    @Mock
    Activity activity;

    @InjectMocks
    PermissionService permissionService = new PermissionService(activity);

    @Mock
    PermissionService.Callback callback;

    @Mock
    SharedPreferences sharedPreferences;

    @Mock
    SharedPreferences.Editor editor;

    @Mock
    PermisionServiceInterface permisionServiceInterface;

    @Captor
    ArgumentCaptor<ArrayList<String>> refusePermissionsCaptor;

    @Test
    public void requestLessM() {
        String[] permissions =
                new String[]{ACCESS_FINE_LOCATION, CAMERA, WRITE_EXTERNAL_STORAGE};
        when(permisionServiceInterface.getBuildSDK()).thenReturn(Build.VERSION_CODES.M - 1);
        permissionService.request(callback);
        verify(callback).onResponse(refusePermissionsCaptor.capture());
    }

    @Test
    public void request3Permissions3Accepted() throws PackageManager.NameNotFoundException {
        final String[] permissions =
                new String[]{ACCESS_FINE_LOCATION, CAMERA, WRITE_EXTERNAL_STORAGE};
        when(permisionServiceInterface.getBuildSDK()).thenReturn(Build.VERSION_CODES.M);
        when(activity.checkSelfPermission(any(String.class))).thenReturn(PackageManager.PERMISSION_DENIED);
        when(sharedPreferences.getBoolean(any(String.class), eq(true))).thenReturn(true);
        when(sharedPreferences.edit()).thenReturn(editor);
        when(permisionServiceInterface.getPermissions()).thenReturn(permissions);

        doAnswer(new Answer<Object>() {
            int i = 0;

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                i++;
                if (i == permissions.length)
                    // 0 accepted, 1 refuse
                    callback.handler(permissions, new int[]{0, 0, 0});
                return null;
            }
        }).when(editor).apply();
        permissionService.request(callback);
        verify(callback).onResponse(refusePermissionsCaptor.capture());
    }

    @Test
    public void request3Permissions2Accepted() {
        final String[] permissions =
                new String[]{ACCESS_FINE_LOCATION, CAMERA, WRITE_EXTERNAL_STORAGE};
        when(permisionServiceInterface.getBuildSDK()).thenReturn(Build.VERSION_CODES.M);
        when(activity.checkSelfPermission(any(String.class))).thenReturn(PackageManager.PERMISSION_DENIED);
        when(sharedPreferences.getBoolean(any(String.class), eq(true))).thenReturn(true);
        when(sharedPreferences.edit()).thenReturn(editor);
        when(permisionServiceInterface.getPermissions()).thenReturn(permissions);
        doAnswer(new Answer<Object>() {
            int i = 0;

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                i++;
                if (i == permissions.length)
                    // 0 accepted, 1 refuse
                    callback.handler(permissions, new int[]{0, 1, 0});
                return null;
            }
        }).when(editor).apply();

        permissionService.request(callback);
        verify(callback).onResponse(refusePermissionsCaptor.capture());

        ArrayList<String> refusePermissions = refusePermissionsCaptor.getValue();
        assertThat(refusePermissions.get(0), is(equalTo(CAMERA)));
    }

    @Test
    public void request3Permissions3Reject() {
        final String[] permissions =
                new String[]{ACCESS_FINE_LOCATION, CAMERA, WRITE_EXTERNAL_STORAGE};
        when(permisionServiceInterface.getBuildSDK()).thenReturn(Build.VERSION_CODES.M);
        when(activity.checkSelfPermission(any(String.class))).thenReturn(PackageManager.PERMISSION_DENIED);
        when(sharedPreferences.getBoolean(any(String.class), eq(true))).thenReturn(true);
        when(sharedPreferences.edit()).thenReturn(editor);
        when(permisionServiceInterface.getPermissions()).thenReturn(permissions);
        doAnswer(new Answer<Object>() {
            int i = 0;

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                i++;
                if (i == permissions.length)
                    // 0 accepted, 1 refuse
                    callback.handler(permissions, new int[]{1, 1, 1});
                return null;
            }
        }).when(editor).apply();

        permissionService.request(callback);
        verify(callback).onResponse(refusePermissionsCaptor.capture());

        ArrayList<String> refusePermissions = refusePermissionsCaptor.getValue();
        assertThat(refusePermissions.get(0), is(equalTo(ACCESS_FINE_LOCATION)));
        assertThat(refusePermissions.get(1), is(equalTo(CAMERA)));
        assertThat(refusePermissions.get(2), is(equalTo(WRITE_EXTERNAL_STORAGE)));
    }
}