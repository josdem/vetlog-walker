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