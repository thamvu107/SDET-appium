- References:
- [real device config](https://appium.github.io/appium-xcuitest-driver/latest/preparation/real-device-config/)

**Way 1:**

- Find webdriver agent:
    - `find . -name "appium-webdriveragent"`
- Open webdriver agent:
    - `open ./node_modules/appium-xcuitest-driver/node_modules/appium-webdriveragent`
- Select webdriver agent and open: `WebDriverAgent.xcproject`

**Way 2:** (XCUITest driver v4.13.0 or newer)

- Run command : `appium driver run xcuitest open-wda`


- appium driver run xcuitest <script-name>
    - <script-name>
      - `open-wda` : Opens the WebDriverAgent project in Xcode
      - `build-wda` : Builds the WebDriverAgent project using the first available iPhone simulator and the latest iOS supported by the current Xcode version

- build:
    - WDWRunner
    - WDWLib
    - InterationApp
- WDA product - Test

- Trust together:
    - Enable Developer Mode on device (
      phone) [Link](https://developer.apple.com/documentation/xcode/enabling-developer-mode-on-a-device)
    - Settings => General => Device Management on the device to trust the developer and allow the WebDriverAgentRunner
      app to be run
    - Finder Mac -> Trust phone 
