Desired Capability:

- Keys and values encoded in a JSON object, sent by Appium Client to the server when a new driver session is required
- Types:
    - General Capability ( Command for all drivers)
    - Android only [UiAutomator2](https://github.com/appium/appium-uiautomator2-driver?tab=readme-ov-file#capabilities)
    - iOS only [XCUITest] (https://appium.github.io/appium-xcuitest-driver/4.16/capabilities/)

- Android Desired Capabilities
    - Without APK (Minimum)
        - Using device name
          ```json
              "deviceName": "Nexus_9_API_28"
              "platformName": "Android"
              "appPackage":"my.app.helloworld"
              "appActivity":"my.app.helloworld.common.activity.SplashScreen"
          ```
        - Using adv
          ```json{
            "appium:appPackage": "com.wdiodemoapp",
            "appium:appActivity": "com.wdiodemoapp.MainActivity",
            "appium:automationName": "uiautomator2",
            "platformName": "android",
            "appium:deviceName": "Pixel 5 API 34",
            "avd": "Pixel_5_API_34",
            "appium:platformVersion": "14",
            "appium:setting[ignoreUNimportantViews]": true
            }
          ```

- iOs Desired Capabilities:
    - With app on simulator
        - Using app path:
    ```json
      {
        "platformName": "iOS",
        "appium:options": {
        "automationName": "XCUITest",
        "platformVersion": "17.4",
        "deviceName": "iPhone 15",
        "app": "/Users/tham/Code/appium/src/test/resrources/apps/wdiodemoapp.app",
        "setting[ignoreUNimportantViews]": true
        }
      }
  ```
      - Using udid

    ```json
    {
       "platformName": "iOS",
        "automationName": "XCUITest",
        "udid": "499D9A97-32D0-4993-9994-950EEECB7BAA",
        "bundleId": "org.reactjs.native.example.wdiodemoapp",
       "setting[ignoreUNimportantViews]": true
     }
    ```

    - Without app on real device

      ```json
       "deviceName": "iPhone-8"
          "bundleId": "com.test.myapp"
          "udid": "12314121211110111042111111"
          "platformVersion": "12.1.4"
          "xcodeOrgId": "123415151"
          "xcodeSigningId": "iPhone Developer"
          "platformName": "IOS"
          "automationName": "XCuiTest"`
      ```

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

Why we use Maven Framework

- Dependency management
- Test execution
- Build Lifecycle Management ( validate, compile, package, verify. install, deploy)
- Support CI/CD
- Parallel Execution

  if (devicePlatform.contains("fullReset")) { // uninstall and install client
  System.out.println("  Driver DO FULL-RESET");
  capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
  capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
  } else if (devicePlatform.contains("fastReset")) { // clears cache and settings without reinstall
  System.out.println("  Driver DO FAST-RESET");
  capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
  capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
  } else { // just start client
  System.out.println("  Driver DO NORMAL start");
  capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
  capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
  }