- Installing Appium 2.0
    - Install with last version:
        - `npm install -g appium`
    - install appium with specific version
        - `npm install -g appium@2.5.1`
- Check appium version:
    - `appium -v`
- uninstall appium:
    - remove appium
        - `npm uninstall -g appium`
    - remove appium folder
        - `sudo rm -rf /usr/local/lib/node_modules/appium`

- Installing a driver:
    - `appium driver install <driver name>`
    - `appium driver install uiautomator2`

- View driver list:
    - `appium driver list`

- Updating a driver:
    - appium driver update <driver name>
    - appium driver update xcuitest

- Updating driver list:
    - appium driver list --updates

- Uninstall a driver:
    - `appium driver uninstall <driver name>`
    - `appium driver uninstall xcuitest`
    - `npm uninstall appium-uiautomator2-driver.`

- Appium plugins Installation:
    - `appium plugin install <plugin name>`
    - `appium plugin install execute-driver`
    - Appium
      - 

- Listing all the Appium plugins:
    - `appium plugin list`

- Updating an Appium plugin:
    - `appium plugin list --updates`
    - `appium plugin update <plugin name>`

- Uninstalling a plugin:
    - `appium plugin uninstall <plugin name>`

- Starting the Appium Server with specific Appium Driver:
    - `appium server --use-driver=xcuitest`