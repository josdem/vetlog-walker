Vetlog Walker
----------------------------

This project send pet's geolocation to Vetlog

### Requirements

* [Android Studio](https://developer.android.com/studio)
* Android 6 (Marshmallow) or above

**Note:** It was developed with a Pixel 8 Android 16 

### To run emulator
```bash
cd ${androidSdk}/emulator 
./emulator @${deviceName} 
```

Where:
- `${androidSdk}` is your Android SDK directory
- `${deviceName}` is device you created 

### To install the app
```bash
${androidSdk}/platform-tools/adb -s ${deviceName} install ${projectHome}/app/build/outputs/apk/debug/app-debug.apk
```

Where:
- `${androidSdk}` is your Android SDK directory
- `${projectHome}` is your project directory
