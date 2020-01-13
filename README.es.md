# Fudi Android Permissions quickly 

[![Platform](https://img.shields.io/badge/platform-android-brightgreen.svg)](https://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=23)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/romellfudi/FudiPermission/blob/master/LICENSE)
[![](https://github.com/romellfudi/FudiPermission/workflows/Android%20CI/badge.svg)](https://github.com/romellfudi/FudiPermission/actions)
[![Bintray](https://img.shields.io/bintray/v/romllz489/maven/fudi-permission.svg)](https://bintray.com/romllz489/maven/fudi-permission)  
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-Fudi%20Permisions-green.svg?style=flat )]( https://android-arsenal.com/details/1/7105 )
[![Jitpack](https://jitpack.io/v/romellfudi/FudiPermission.svg)](https://jitpack.io/#romellfudi/FudiPermission)
[![CircleCi](https://img.shields.io/circleci/project/github/romellfudi/FudiPermission.svg)](https://circleci.com/gh/romellfudi/FudiPermission/tree/master)
[![](https://img.shields.io/badge/language-EN-blue.svg)](./)

### by Romell Domínguez
[![](snapshot/icono.png)](https://www.romellfudi.com/)

`latestVersion` es [ ![Download](https://api.bintray.com/packages/romllz489/maven/fudi-permission/images/download.svg) ](https://bintray.com/romllz489/maven/fudi-permission/_latestVersion)

Agregar en tu archivo `build.gradle` del proyecto Android:

```groovy
repositories {
    jcenter()
}
dependencies {
    implementation 'com.romellfudi.permission:fudi-permission:${latestVersion}'
    implementation 'com.romellfudi.permission:fudi-permission-kotlin:${latestVersion}'
}
```

Primero Android a partir de la versión 6 necesita consultarle al usuario el permiso de componentes y servisios , acá veremos cómo construir una libreria Android para permmisos (jar or aar file):

Los permisos necesarios a consultar los listo (*ver needPermissions.txt*):

## Import library:
 
Agregamos la dependencia en nuestro módulo aplicativo, usar uno obviamente para no tener conflictos:

```gradle
dependencies {
     implementation 'com.romellfudi.permission:fudi-permission:2.0.a' 
}
```


## How use
 
Construcción del callback de permisos. En caso el usuario rechace una o más, estas retornarán en el método *onRefuse method* , de lo contrario pasará a *onFinally*

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

Invocamos nuestro servicio, pasando los permisos y el objeto callback:

```java
// java
new PermissionService(this).request(callback);
```

```kotlin
// kotlin
PermissionService(this).request(callback)
```

**Recordar** sobreescribir el método onRequestPermissions:

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

<style>
img[src*='#center'] { 
    width:500px;
    display: block;
    margin: auto;
}
</style>