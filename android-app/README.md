Location Updates (Java)
=======================

This code is initially based on the Google's sample code here:
https://github.com/android/location-samples/tree/main/LocationAddress

Introduction
============

The Android framework location APIs provide a
[Geocode API](http://developer.android.com/reference/android/location/Geocoder.html)
which contains a
[getFromLocation()](http://developer.android.com/reference/android/location/Geocoder.html#getFromLocation(double, double, int))
method that returns an estimated street address corresponding to a given
latitude and longitude. This code uses `getFromLocation()` to get a `List<Address>` of addresses, as can be seen here:

https://github.com/sarinderv/CMPE277-Zeus-Realty/blob/master/android-app/app/src/main/java/com/google/android/gms/location/sample/locationaddress/MainActivity.java#L233

The list is shown in a simple `ListView`.

To run this sample, **location must be enabled**.

Prerequisites
--------------

- Android API Level >v9
- Android Build Tools >v21

Getting Started
---------------

This sample uses the Gradle build system. To build this project, use the
"gradlew build" command or use "Import Project" in Android Studio.
