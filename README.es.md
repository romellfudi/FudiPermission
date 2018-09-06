# Fudi Android Permissions quickly 

 [![platform](https://img.shields.io/badge/platform-android-brightgreen.svg)](https://developer.android.com/index.html)
 [![API](https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=23)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/romellfudi/FudiPermission/blob/master/LICENSE)
[![Bintray](https://img.shields.io/bintray/v/yshrsmz/maven/simplepreferences.svg)](https://bintray.com/romllz489/maven/fudi-permission) 

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

## Create library 

### Build library *.jar:
Creamos dos tareas para la construcción y exportación dentro del gradle del proyecto

```gradlew
task deleteJar(type: Delete) {
    delete 'libs/fudi-permission:1.0.a.jar'
}

task createJar(type: Copy) {
    from('build/intermediates/bundles/debug/') // release
    into('../app/libs')
    include('classes.jar')
    rename('classes.jar', 'fudi-permission:1.0.a.jar')
}

createJar.dependsOn(deleteJar, build)
```

Corremos la tarea vía gradle:

![center](snapshot/a.png#center)

![center](snapshot/c.png#center)

Después de correr las tareas, deberiamos tener **'FudiPermission.jar'** en la carpeta libs:

![center](snapshot/g.png#center)

### Build library *.aar:
Las librerias aar permiten extraer archivos xml internos de la libraría sin ninguna restrinción.
Copiar el archivo aar desde la carpeta build/outputs a la carpeta libs dentro de app/main:

![center](snapshot/f.png#center)

En tu módulo se debe tener el siguiente esquema:

![center](snapshot/d.png#center)


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

**2018, July**

<style>
img[src*='#center'] { 
    width:500px;
    display: block;
    margin: auto;
}
</style>