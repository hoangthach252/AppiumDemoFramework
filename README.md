# Mobile Automation Framework using Appium 2.1 + TestNG + Maven

A sample Mobile Automation framework.

Sample tests against [webdriverio native-demo-app](https://github.com/webdriverio/native-demo-app/releases) native app.
Tests are running on local Android Emulator and IOS Simulator.
The setup is done on Mac.

Reference Links:
* [Appium 2.1 documentation](https://appium.io/docs/en/2.1/intro/ )
* [TestNG documentation](https://testng.org/doc/documentation-main.html)

## Run/Debug tests
run/debug test from testng.xml


* run tests in Parallel: 2 threads running tests on 1 Android and 1 IOS phones. Parallel is in Tests level. 
Check testng.xml for more detail.
  ```shell
  $ mvn clean test
  ```


## Allure reports (to be added)
Allure report will contain framework logs, Appium interaction logs, screenshots and page sources for
failing test cases

* [Allure CLI](https://docs.qameta.io/allure/#_commandline) should be installed
* Allure results stored in `build/allure-results`
* To generate allure report, first navigate to unsplash directory then run command:
  ```shell
  $ allure serve
  ```

## Logback configuration

You can find logback configuration here `src/test/resources/logback-test.xml`

Current configuration contains two appenders:
(default log level: INFO)

* ConsoleAppender will output logs to system out stream
* FileAppender will output logs to `build/logs/log.log`