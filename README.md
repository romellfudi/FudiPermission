# Fudi Permissions quickly

[![Platform](https://img.shields.io/badge/platform-android-brightgreen.svg)](https://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=23)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/romellfudi/FudiPermission/blob/master/LICENSE)
[![](https://github.com/romellfudi/FudiPermission/workflows/Android%20CI/badge.svg)](https://github.com/romellfudi/FudiPermission/actions)
[![Bintray](https://img.shields.io/bintray/v/romllz489/maven/fudi-permission.svg)](https://bintray.com/romllz489/maven/fudi-permission) 
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-Fudi%20Permisions-green.svg?style=flat )]( https://android-arsenal.com/details/1/7105 )
[![Jitpack](https://jitpack.io/v/romellfudi/FudiPermission.svg)](https://jitpack.io/#romellfudi/FudiPermission)
[![CircleCi](https://img.shields.io/circleci/project/github/romellfudi/FudiPermission.svg)](https://circleci.com/gh/romellfudi/FudiPermission/tree/master)
[![](https://img.shields.io/badge/language-ES-blue.svg)](./README.es)

### by Romell Domínguez
[![](snapshot/icono.png)](https://www.romellfudi.com/)

`latestVersion` is [ ![Download](https://api.bintray.com/packages/romllz489/maven/fudi-permission/images/download.svg) ](https://bintray.com/romllz489/maven/fudi-permission/_latestVersion)

Add the following in your app's `build.gradle` file:

```groovy
repositories {
    jcenter()
}
dependencies {
    implementation 'com.romellfudi.permission:fudi-permission:${latestVersion}'
    implementation 'com.romellfudi.permission:kotlin-fudi-permission:${latestVersion}'

}
```

First Android need confirmation by user from version 6 , its fot that create a sample how build a Adroid permission library (jar or aar file):

Before adding the permissions which you need, [*check needPermissions file*](needPermissions.txt):
 
## How use 

Build an callback object to catch the rsponse for user. If have 1 or more refuse permissions, *onRefuse method* activate, otherwise *onFinally*

```java
// java
private PermissionService.Callback callback = new PermissionService.Callback() {
        @Override
        public void onResponse(ArrayList<String> RefusePermissions) {
            // todo
        } 
    };
```

```kotlin
// kotlin
private val callback = object : PermisionInterface {
        override fun onResponse(refusePermissions: ArrayList<String>) {
            if (refusePermissions!=null) {
                Toast.makeText(baseContext,
                        "Have to allow all permissions",
                        Toast.LENGTH_SHORT).show()
                Handler().postDelayed({ finish() }, 2000)
                }
        } 
    }
```

Invoke service inside at Activity/onCreate method :

```java
// java
new PermissionService(this).request(callback);
```

```kotlin
// kotlin
PermissionService(this).request(callback)
```

Override onRequestPermissions method from Activity class, note if necessary use if-case sentence:

```java
// java
@Override
public void onRequestPermissionsResult(int requestCode, 
            @NonNull String[] permissions, @NonNull int[] grantResults) {
            callback.handler(permissions, grantResults);
    }
```

```kotlin
// kotlin
override fun onRequestPermissionsResult(requestCode: Int,
        permissions: Array<String>, grantResults: IntArray) {
        PermissionService.handler(callback, grantResults, permissions)
    }
```

### License
```
Copyright 2018 Romell D.Z.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
``` 

<style>
img[src*='#center'] { 
    width:500px;
    display: block;
    margin: auto;
}
</style>