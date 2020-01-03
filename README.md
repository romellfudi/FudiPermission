# Fudi Android Permissions quickly

[![Platform](https://img.shields.io/badge/platform-android-brightgreen.svg)](https://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=23)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/romellfudi/FudiPermission/blob/master/LICENSE)
[![](https://github.com/romellfudi/FudiPermission/workflows/Android%20CI/badge.svg)](https://github.com/romellfudi/FudiPermission/actions)
[![Bintray](https://img.shields.io/bintray/v/romllz489/maven/fudi-annotation.svg)](https://bintray.com/romllz489/maven/fudi-permission) 
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-Fudi%20Permisions-green.svg?style=flat )]( https://android-arsenal.com/details/1/7105 )
[![Jitpack](https://jitpack.io/v/romellfudi/FudiPermission.svg)](https://jitpack.io/#romellfudi/FudiPermission)
[![CircleCi](https://img.shields.io/circleci/project/github/romellfudi/FudiPermission.svg)](https://circleci.com/gh/romellfudi/FudiPermission/tree/master)
[![](https://img.shields.io/badge/language-ES-blue.svg)](./README.es)

### by Romell Domínguez
[![](snapshot/icono.png)](https://www.romellfudi.com/)

`latestVersion` is 1.0.a

Add the following in your app's `build.gradle` file:

```groovy
repositories {
    jcenter()
}
dependencies {
    compile 'com.romellfudi.permission:fudi-permission:${latestVersion}'
    compile 'com.romellfudi.permission:fudi-permission-kotlin:${latestVersion}'

}
```


First Android need confirmation by user from version 6 , its fot that create a sample how build a Adroid permission library (jar or aar file):

Permissions that you have deploy (needPermissions.txt):

```txt
CALENDAR
    READ_CALENDAR
    WRITE_CALENDAR
CAMERA
    CAMERA
CONTACTS
    READ_CONTACTS
    WRITE_CONTACTS
    GET_ACCOUNTS
LOCATION
    ACCESS_FINE_LOCATION
    ACCESS_COARSE_LOCATION
MICROPHONE
    RECORD_AUDIO
PHONE
    READ_PHONE_STATE
    CALL_PHONE
    READ_CALL_LOG
    WRITE_CALL_LOG
    ADD_VOICEMAIL
    USE_SIP
    PROCESS_OUTGOING_CALLS
SENSORS
    BODY_SENSORS
SMS
    SEND_SMS
    RECEIVE_SMS
    READ_SMS
    RECEIVE_WAP_PUSH
    RECEIVE_MMS
STORAGE
    READ_EXTERNAL_STORAGE
    WRITE_EXTERNAL_STORAGE
```

## Import library:

If you use aar component you need add this lines into gradle:

```gradle
repositories{
    flatDir {
        dirs 'libs'
    }
}
```

add dependency into app project, use only one to reject conflict:

```gradle
dependencies {
//    implementation(name: 'fudi-permission-1.0.a', ext: 'aar')
//    implementation files('libs/fudi-permission-1.0.a.jar')
}
```


## How use

Add normally permissions on your manifest app:
```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

Import class permissions variables, ex:

```java
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
```

Build an callback object to catch the rsponse for user. If have 1 or more refuse permissions, *onRefuse method* activate, otherwise *onFinally*

```java
// java
private PermissionService.Callback callback = new PermissionService.Callback() {
        @Override
        public void onRefuse(ArrayList<String> RefusePermissions) {
            // todo
        }

        @Override
        public void onFinally() {
            // todo
        }
    };
```

```kotlin
// kotlin
private val callback = object : PermisionInterface {
        override fun onRefuse(RefusePermissions: ArrayList<String>) {
            Toast.makeText(baseContext,
                    "Have to allow all permissions",
                    Toast.LENGTH_SHORT).show()

            Handler().postDelayed({ finish() }, 2000)
        }

        override fun onFinally() {
            initController()
        }
    }
```

Invoke service inside at Activity/onCreate method :

```java
// java
new PermissionService(this).request(
                new String[]{ACCESS_FINE_LOCATION, CAMERA},
                callback);
```

```kotlin
// kotlin
PermissionService(this).request(
                arrayOf(ACCESS_FINE_LOCATION, CAMERA), callback)
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
        PermissionService.handler(callback,permissions, grantResults)
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

**2018, July**

<style>
img[src*='#center'] { 
    width:500px;
    display: block;
    margin: auto;
}
</style>