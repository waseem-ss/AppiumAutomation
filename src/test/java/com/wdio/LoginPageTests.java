package com.wdio;

import com.wdio.pageobjects.LoginPageObject;
import com.common.util.Util;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;

public class LoginPageTests extends Util {
    public String appUnderTest = "android.wdio.native.app.v1.0.8.apk";
    @Test
    public void positiveLoginTest(){
        String userName = "test@abc.com";
        String password = "test@123";
        AppiumDriver appiumDriver = launchApp(appUnderTest);
        //Navigating to the Login Page
        LoginPageObject loginPage = new LoginPageObject(appiumDriver);;
        //Enter User Creds
        loginPage.enterUserDetails(appiumDriver,userName,password);
        loginPage.pressLogin(appiumDriver);
        loginPage.closeSuccessMessage(appiumDriver);
    }
}
