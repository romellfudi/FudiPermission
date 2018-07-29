# Android Permissions Fudi quickly

### by Romell Domínguez
[![](snapshot/icono.png)](https://www.romellfudi.com/)

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
    delete 'libs/FudiPermission.jar'
}

task createJar(type: Copy) {
    from('build/intermediates/bundles/debug/') // release
    into('../app/libs')
    include('classes.jar')
    rename('classes.jar', 'FudiPermission.jar')
}

createJar.dependsOn(deleteJar, build)
```
Run the task via gradle:

![center](snapshot/a.png)

![center](snapshot/c.png)

After you run the task, you should have FudiPermission.jar into libs:

![center](snapshot/g.png)

### Build library *.aar:
Copy aar from build/outputs directory into libs directory from main app:

![center](snapshot/f.png)

In your app module, you should have FudiPermission.aar:

![center](snapshot/d.png)


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
    compile 'com.android.support:appcompat-v7:24.2.1'
    //compile project(':permission')
    //compile(name: 'FudiPermission', ext: 'aar') // jar
   compile files('libs/FudiPermission.jar') // aar
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