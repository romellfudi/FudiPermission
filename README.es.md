# Fudi Android Permissions quickly 

[![Platform](https://img.shields.io/badge/platform-android-brightgreen.svg)](https://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=23)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/romellfudi/FudiPermission/blob/master/LICENSE)
[![Bintray](https://img.shields.io/bintray/v/romllz489/maven/fudi-annotation.svg)](https://bintray.com/romllz489/maven/fudi-permission)
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-Fudi%20Permisions-green.svg?style=flat )]( https://android-arsenal.com/details/1/7105 )
[![Jitpack](https://jitpack.io/v/romellfudi/FudiPermission.svg)](https://jitpack.io/#romellfudi/FudiPermission)
[![CircleCi](https://img.shields.io/circleci/project/github/romellfudi/FudiPermission.svg)](https://circleci.com/gh/romellfudi/FudiPermission/tree/master)
[![](https://img.shields.io/badge/language-EN-blue.svg)](./)

### by Romell Domínguez
[![](snapshot/icono.png)](https://www.romellfudi.com/)

`latestVersion` es 1.0.a

Agregar en tu archivo `build.gradle` del proyecto Android:

```groovy
repositories {
    jcenter()
}
dependencies {
    compile 'com.romellfudi.permission:fudi-permission:${latestVersion}'

}
```

Primero Android a partir de la versión 6 necesita consultarle al usuario el permiso de componentes y servisios , acá veremos cómo construir una libreria Android para permmisos (jar or aar file):

Los permisos necesarios a consultar los listo (*ver needPermissions.txt*):

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

En caso usar componentes aar, indicas en el gradle, mediante las las sentencias:

```gradle
repositories{
    flatDir {
        dirs 'libs'
    }
}
```

Agregamos la dependencia en nuestro módulo aplicativo, usar uno obviamente para no tener conflictos:

```gradle
dependencies {
//    compile project(':fudi-permission')
//    compile(name: 'fudi-permission-1.0.a', ext: 'aar')
//    compile files('libs/fudi-permission-1.0.a.jar')
    implementation 'com.romellfudi.permission:fudi-permission:1.0.a'
}
```


## How use

Agregamos los permisos que se requieran y este en el archivo adjunto de los permisos en nuestro app/manifest.xml:

```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

Importamos las variables de clases respectivamente para cada permisos:

```java
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
```

Construcción del callback de permisos. En caso el usuario rechace una o más, estas retornarán en el método *onRefuse method* , de lo contrario pasará a *onFinally*

```java
private PermissionService.Callback callback = new PermissionService.Callback() {
        @Override
        public void onRefuse(ArrayList<String> refusePermissions) {
            // todo
        }

        @Override
        public void onFinally() {
            // todo
        }
    };
```

Invocamos nuestro servicio, pasando los permisos y el objeto callback:

```java
new PermissionService(this).request(
                new String[]{ACCESS_FINE_LOCATION, CAMERA},
                callback);
```

**Recordar** sobreescribir el método onRequestPermissions:

```java
@Override
public void onRequestPermissionsResult(int requestCode, 
            @NonNull String[] permissions, @NonNull int[] grantResults) {
            callback.handler(permissions, grantResults);
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