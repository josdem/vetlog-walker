Vetlog Walker
----------------------------
[![GitHub](https://github.com/josdem/vetlog-walker/actions/workflows/build.yml/badge.svg)](https://github.com/josdem/vetlog-walker/actions)

This project send pet's geolocation to Vetlog

### Requirements

* [Android Studio](https://developer.android.com/studio)
* Android 6 (Marshmallow) or above

**Note:** It was developed with a Pixel 8 Android 16 

### To run emulator
```bash
cd ${androidSdk}/emulator 
./emulator @deviceName 
```

Where:
- `${androidSdk}` is your Android SDK directory
- `deviceName` is device you created

### To format the code
```bash
./gradlew spotlessApply
```

### To run tests
```bash
./gradlew test
```

### To run instrumented tests
```bash
./gradlew connectedAndroidTest
```

### To build the project
```bash
./gradlew assembleDebug
```

**Notes**
- Make sure the app has location permission in your emulator device
- Make sure you has `TOKEN` system properties with a valid value

```bash
expot TOKEN="userToken"
```

Previous token should be the same as defined at [Vetlog Yaml configuration file](https://github.com/josdem/vetlog-spring-boot/wiki/YAML-File)