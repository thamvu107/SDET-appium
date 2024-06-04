# Issue:

- **Issue 1:
  ** `An unknown server-side error occurred while processing the command. Could not proxy command to remote server. Original
  error: Error: socket hang up`

    - **Solution**:
        - Stop the appium server and execute this:
            - `adb uninstall io.appium.uiautomator2.server`
            - `adb uninstall io.appium.uiautomator2.server.test`
            - `adb uninstall io.appium.unlock`
            - `adb uninstall io.appium.settings`
- [Detail problem link](https://discuss.appium.io/t/selenium-webdriverexception-org-openqa-selenium-webdriverexception-an-unknown-server-side-error-occurred-while-processing-the-command-original-error-could-not-proxy-command-to-remote-server-original-error-error-socket-hang-up/29257/7)


- **Issue 2:**  `System UI Not Responding`
    - **Solutions**:
        - Update emulator to increase RAM (3-4GB) + ( 1-2 GB RAM will be unstable and slow)
        - Clear Storages
        - Cold boot

- **Issue 3**: `Compatibility matrix of Appium Java Client and Selenium Client`
    - New versions of Selenium 4 libraries are published they are pulled transitively as Appium Java Client dependencies
      at the first project (re)build automatically.
    - In order to pin Selenium dependencies they should be declared in pom.xml in the following way:
        - ```<dependencies>
            <dependency>
                <groupId>io.appium</groupId>
                <artifactId>java-client</artifactId>
                <version>X.Y.Z</version>
                <exclusions>
                    <exclusion>
                        <groupId>org.seleniumhq.selenium</groupId>
                        <artifactId>selenium-api</artifactId>
                    </exclusion>
                    <exclusion>
                        <groupId>org.seleniumhq.selenium</groupId>
                        <artifactId>selenium-remote-driver</artifactId>
                    </exclusion>
                    <exclusion>
                        <groupId>org.seleniumhq.selenium</groupId>
                        <artifactId>selenium-support</artifactId>
                    </exclusion>
                </exclusions>
            </dependency>
            <dependency>
                <groupId>org.seleniumhq.selenium</groupId>
                <artifactId>selenium-api</artifactId>
                <version>A.B.C</version>
            </dependency>
            <dependency>
                <groupId>org.seleniumhq.selenium</groupId>
                <artifactId>selenium-remote-driver</artifactId>
                <version>A.B.C</version>
            </dependency>
            <dependency>
                <groupId>org.seleniumhq.selenium</groupId>
                <artifactId>selenium-support</artifactId>
                <version>A.B.C</version>
            </dependency>
      </dependencies>```
    - If the updating Selenium version is happened conflict => Then follow steps below to resolve:
        - Remove all packages under .m2>repository
        - `mvn clean package -DskipTests`
        - Run command line: `mvn clean install -U -DskipTests=true`
        - Reload maven project & Resolved Maven packages (Download packages)
        - Rebuild project

- **Issue 4**: `"Original error: Cannot read properties of undefined (reading '0')`"
    - **Solutions**:
        - Check caps are correct
        - Check time to connect app under test -> If time isn't enough connect then need to increase wait time ( app
          launch, server timeout ,...)
        - ...
- **Issue 5:** emulator crash: `emulator -list-advs` => `Storing crashdata in`
    - **Solution**:
        - https://developer.android.com/studio/emulator_archive
        - https://developer.android.com/studio/releases/emulator
- Issue 6: Gson already is a dependency in java-client ( java-client.pom) but it is only runtime scope. that is why the
  code using Gson will not compile
    - Solution: add dependency into myfile.pom with same version ( to void conflict version) and add scope to compile