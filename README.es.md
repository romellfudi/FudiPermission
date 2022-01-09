# Fudi Permissions quickly 

[![Platform](https://img.shields.io/badge/platform-android-brightgreen.svg?logo=android)](https://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat&logo=android)](https://android-arsenal.com/api?level=23)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?logo=apache)](https://github.com/romellfudi/FudiPermission/blob/master/LICENSE)
[![](https://github.com/romellfudi/FudiPermission/workflows/Android%20CI/badge.svg)](https://github.com/romellfudi/FudiPermission/actions)
[![Bintray](https://img.shields.io/bintray/v/romllz489/maven/fudi-permission.svg?logo=jfrog-bintray)](https://bintray.com/romllz489/maven/fudi-permission) 
[![Data Studio](https://img.shields.io/badge/Data%20Studio-Downloads-%23000000.svg?&style=flat&logo=data%3Aimage%2Fsvg%2Bxml%3Bbase64%2CPHN2ZyBpZD0iTGF5ZXJfMSIgZGF0YS1uYW1lPSJMYXllciAxIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAxMDcuNDcgMTAwIj48ZGVmcz48c3R5bGU%2BLmNscy0xe2ZpbGw6IzY2OWRmMzt9LmNscy0ye2ZpbGw6IzFhNzNlODt9PC9zdHlsZT48L2RlZnM%2BPHBhdGggY2xhc3M9ImNscy0xIiBkPSJNOTQuMDcsNzIuNzVINTVjLTkuNDYsMC0xNC43Myw2LjU5LTE0LjczLDEzLjYyQzQwLjIyLDkyLjc1LDQ0LjYyLDEwMCw1NSwxMDBoMzguOVY3Mi43NVoiLz48ZWxsaXBzZSBjbGFzcz0iY2xzLTIiIGN4PSI5NC4wNyIgY3k9Ijg2LjE1IiByeD0iMTMuNDEiIHJ5PSIxMy42MyIvPjxwYXRoIGNsYXNzPSJjbHMtMSIgZD0iTTUzLjYzLDM2LjI2SDE0LjczQzUuMjcsMzYuMjYsMCw0Mi44NiwwLDQ5Ljg5YzAsNi4zNyw0LjQsMTMuNjMsMTQuNzMsMTMuNjNoMzguOVoiLz48ZWxsaXBzZSBjbGFzcz0iY2xzLTIiIGN4PSI1My42MyIgY3k9IjQ5Ljg5IiByeD0iMTMuNDEiIHJ5PSIxMy42MyIvPjxwYXRoIGNsYXNzPSJjbHMtMSIgZD0iTTk0LjA3LDBINTVDNDUuNDksMCw0MC4yMiw2LjU5LDQwLjIyLDEzLjYzYzAsNi4zNyw0LjQsMTMuNjIsMTQuNzMsMTMuNjJoMzguOVYwWiIvPjxlbGxpcHNlIGNsYXNzPSJjbHMtMiIgY3g9Ijk0LjA3IiBjeT0iMTMuNjMiIHJ4PSIxMy40MSIgcnk9IjEzLjYzIi8%2BPC9zdmc%2B)](https://datastudio.google.com/reporting/f5c5c53a-a565-4086-8010-36a7dcc05a8f)
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-Fudi%20Permisions-green.svg?style=flat&logo=android-studio)]( https://android-arsenal.com/details/1/7105 )
[![Jitpack](https://jitpack.io/v/romellfudi/FudiPermission.svg)](https://jitpack.io/#romellfudi/FudiPermission)
[![CircleCi](https://img.shields.io/circleci/project/github/romellfudi/FudiPermission.svg)](https://circleci.com/gh/romellfudi/FudiPermission/tree/master)
[![](https://img.shields.io/badge/anguage-EN-blue.svg?logo=librarything)](./README)

### by Romell Domínguez
[![](https://raw.githubusercontent.com/romellfudi/assets/me/icono.png)](https://www.romellfudi.com/)


## Downloads Dashboard
[![Data Studio](https://img.shields.io/badge/DATA%20STUDIO-DOWNLOADS%20REPORT-%23000000.svg?&style=for-the-badge&logo=data%3Aimage%2Fsvg%2Bxml%3Bbase64%2CPHN2ZyBpZD0iTGF5ZXJfMSIgZGF0YS1uYW1lPSJMYXllciAxIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAxMDcuNDcgMTAwIj48ZGVmcz48c3R5bGU%2BLmNscy0xe2ZpbGw6IzY2OWRmMzt9LmNscy0ye2ZpbGw6IzFhNzNlODt9PC9zdHlsZT48L2RlZnM%2BPHBhdGggY2xhc3M9ImNscy0xIiBkPSJNOTQuMDcsNzIuNzVINTVjLTkuNDYsMC0xNC43Myw2LjU5LTE0LjczLDEzLjYyQzQwLjIyLDkyLjc1LDQ0LjYyLDEwMCw1NSwxMDBoMzguOVY3Mi43NVoiLz48ZWxsaXBzZSBjbGFzcz0iY2xzLTIiIGN4PSI5NC4wNyIgY3k9Ijg2LjE1IiByeD0iMTMuNDEiIHJ5PSIxMy42MyIvPjxwYXRoIGNsYXNzPSJjbHMtMSIgZD0iTTUzLjYzLDM2LjI2SDE0LjczQzUuMjcsMzYuMjYsMCw0Mi44NiwwLDQ5Ljg5YzAsNi4zNyw0LjQsMTMuNjMsMTQuNzMsMTMuNjNoMzguOVoiLz48ZWxsaXBzZSBjbGFzcz0iY2xzLTIiIGN4PSI1My42MyIgY3k9IjQ5Ljg5IiByeD0iMTMuNDEiIHJ5PSIxMy42MyIvPjxwYXRoIGNsYXNzPSJjbHMtMSIgZD0iTTk0LjA3LDBINTVDNDUuNDksMCw0MC4yMiw2LjU5LDQwLjIyLDEzLjYzYzAsNi4zNyw0LjQsMTMuNjIsMTQuNzMsMTMuNjJoMzguOVYwWiIvPjxlbGxpcHNlIGNsYXNzPSJjbHMtMiIgY3g9Ijk0LjA3IiBjeT0iMTMuNjMiIHJ4PSIxMy40MSIgcnk9IjEzLjYzIi8%2BPC9zdmc%2B)](https://datastudio.google.com/reporting/f5c5c53a-a565-4086-8010-36a7dcc05a8f)
[![](https://img.shields.io/badge/language-EN-blue.svg)](./)

### by Romell Domínguez
[![](snapshot/icono.png)](https://www.romellfudi.com/)

`latestVersion` es [ ![Download](https://api.bintray.com/packages/romllz489/maven/fudi-permission/images/download.svg) ](https://bintray.com/romllz489/maven/fudi-permission/_latestVersion)

Agregar en tu archivo `build.gradle` del proyecto Android:

| Repository | implementation | Status |
| :------: | ------ | :------: |
| jcenter() | 'com.romellfudi.permission:fudi-permission:2.0.a' | `DEPRECATED` |
| jcenter() | 'com.romellfudi.permission:kotlin-fudi-permission:2.0.a' | `DEPRECATED` |
| maven { url `https://jitpack.io` } | 'com.github.romellfudi.FudiPermission:fudi-permission:2.0.b' | `READY` |
| maven { url `https://jitpack.io` } | 'com.github.romellfudi.FudiPermission:kotlin-fudi-permission:2.0.b' | `READY` |
```

Primero Android a partir de la versión 6 necesita consultarle al usuario el permiso de componentes y servisios , acá veremos cómo construir una libreria Android para permmisos (jar or aar file):

Los permisos necesarios a consultar los listo [*ver archivo needPermissions*](needPermissions.txt):

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