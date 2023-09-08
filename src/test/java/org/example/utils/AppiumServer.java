package org.example.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;


public class AppiumServer {

    protected static AppiumDriverLocalService appiumService;

    public static void startAppiumServer(String ip, int port) {
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress(ip).usingPort(port)
                .withLogFile(new File(System.getProperty("user.dir") +"/appium_server.log"));
        AppiumDriverLocalService server = AppiumDriverLocalService.buildService(serviceBuilder);
        appiumService = server;
        server.start();
    }

    public static boolean checkIfAppiumServerIsRunnning(int port) throws Exception {
        boolean isAppiumServerRunning = false;
        ServerSocket socket;
        try {
            socket = new ServerSocket(port);
            socket.close();
        } catch (IOException e) {
            System.out.println("1");
            isAppiumServerRunning = true;
        } finally {
            socket = null;
        }
        return isAppiumServerRunning;
    }


    public static void stopAppiumServer() {
        if(appiumService.isRunning()){
            appiumService.stop();
        }
    }
}
