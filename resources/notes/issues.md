# Issue:

- **Issue 1:
  ** `An unknown server-side error occurred while processing the command. Could not proxy command to remote server. Original
  error: Error: socket hang up`

- **Solution**:
    - Stop the appium server and execute this:
        - `adb uninstall io.appium.uiautomator2.server`
        - `adb uninstall io.appium.uiautomator2.server.test`
- [Detail problem link](https://discuss.appium.io/t/selenium-webdriverexception-org-openqa-selenium-webdriverexception-an-unknown-server-side-error-occurred-while-processing-the-command-original-error-could-not-proxy-command-to-remote-server-original-error-error-socket-hang-up/29257/7)


- **Issue 2:**  `System UI Not Responding`
- **Solutions**:
    - Clear Cache on Your Android Device:
        - Go to Settings > Storage&USB > Choose Cached Data – select it and a pop-up will appear, confirming that you
          want to clear the cache.
