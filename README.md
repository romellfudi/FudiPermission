# Fudi Android Permissions quickly [![API](https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=23) [![platform](https://img.shields.io/badge/platform-android-brightgreen.svg)](https://developer.android.com/index.html)  [![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/romellfudi/FudiPermission/blob/master/LICENSE) 

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

## Create library 

### Build library *.jar:
Create 2 task and excute the gradle task

```gradlew
task deleteJar(type: Delete) {
    delete 'libs/fudi-permission-1.0.a.jar'
}

task createJar(type: Copy) {
    from('build/intermediates/bundles/debug/') // release
    into('../app/libs')
    include('classes.jar')
    rename('classes.jar', 'fudi-permission-1.0.a.jar')
}

createJar.dependsOn(deleteJar, build)
```

Run the task via gradle:
<style>
img[src*='#center'] { 
    width:500px;
    display: block;
    margin: auto;
}
</style>

![center](snapshot/a.png#center)

![center](snapshot/c.png#center)

After you run the task, you should have FudiPermission.jar into libs:

![center](snapshot/g.png#center)

### Build library *.aar:
Copy aar from build/outputs directory into libs directory from main app:

![center](snapshot/f.png#center)

In your app module, you should have fudi-permission-1.0.a.aar:

![center](snapshot/d.png#center)


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
//    compile project(':fudi-permission')
//    compile(name: 'fudi-permission-1.0.a', ext: 'aar')
//    compile files('libs/fudi-permission-1.0.a.jar')
    implementation 'com.romellfudi.permission:fudi-permission:1.0.a'
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
Invoke service inside at Activity/onCreate method :

```java
new PermissionService(this).request(
                new String[]{ACCESS_FINE_LOCATION, CAMERA},
                callback);
```

Override onRequestPermissions method from Activity class, note if necessary use if-case sentence:

```java
@Override
public void onRequestPermissionsResult(int requestCode, 
            @NonNull String[] permissions, @NonNull int[] grantResults) {
            callback.handler(permissions, grantResults);
    }
```

**2018, July**
