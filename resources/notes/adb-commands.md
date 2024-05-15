Command with device:

- Show devices list:
    - `emulator -list-avds`
- Start a device
    - `emulator @Pixel_5_API_34`
    - `emulator -avd <avd_name>`
- Stop/Kill a avd
    - `adb -e emu kill`

adb commands:

- adb android debug bridge
- List connected devices
    - `adb devices`
- If a device is connected start the adb sever to be able to interact with the device
    - `adb start-server`
    - `adb kill-server`
- Install an app
    - `adb install [apk_path]`
    - `adb -s device-udid install [apk_path]`
- Run app:
    - `adb shell am start -n com.app.name/com.app.name.LaunchActivity`
    - `adb shell am start -n com.wdiodemoapp/com.wdiodemoapp.MainActivity`
- Uninstall an app
    - `adb uninstall [package_name]`

How to get appPackage & appActivity:

- Launch app on the device and bring activity in focus
- Launch terminal then run one of command:
    - `adb shell dumpsys window | grep -E mCurrentFocus``` (For older Android version)
    - ```adb shell "dumpsys activity activities |grep mResumedActivity"` (For >= Android 10 version)
    - `adb shell dumpsys window | find "mCurrentFocus"`
    - `adb shell dumpsys window | grep "mCurrentFocus"`
    - `adb shell dumpsys window | findstr "mCurrentFocus"`

How to Launch Emulator Automatically:

- Capability to use:
    - `adv`
    - `advLaunchTimeout`
