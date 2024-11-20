package com.wdio.pageobjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPageObject {

    //All the Page Identifiers
    private final String messageXpath = "//android.widget.TextView[@resource-id=\"android:id/message\"]";
    private final String okBtnXpath = "//android.widget.Button[@text=\"OK\"]";
    private String test = "test";

    public LoginPageObject(AppiumDriver driver)  {
        System.out.println("Navigating to Login Page");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Login")));
        WebElement login = driver.findElement(AppiumBy.accessibilityId("Login"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Login")));
        login.click();
    }

    private WebElement inputEmail(AppiumDriver driver){
        return driver.findElement(AppiumBy.
                xpath("//android.widget.EditText[@content-desc=\"input-email\"]"));
    }

    private WebElement inputPassword(AppiumDriver driver){
        return driver.findElement(AppiumBy.
                xpath("//android.widget.EditText[@content-desc=\"input-password\"]"));
    }

    private WebElement btnLogin(AppiumDriver driver){
        return driver.findElement(AppiumBy.
                xpath("//android.view.ViewGroup[@content-desc=\"button-LOGIN\"]"));
    }

    public void closeSuccessMessage(AppiumDriver driver){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(5000));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement message = driver.findElement(AppiumBy.xpath(messageXpath));
        WebElement okButton = driver.findElement(AppiumBy.xpath(okBtnXpath));
        wait.until(ExpectedConditions.visibilityOf(message));
        System.out.println("Success Message: " + message.getText());
        wait.until(ExpectedConditions.visibilityOf(okButton)).click();
        //okButton.click();
    }

    public void enterUserDetails(AppiumDriver driver, String email,String pass){
        System.out.println("Entering email :" + email);
        System.out.println("Entering pass :" + pass);
        inputEmail(driver).sendKeys(email);
        inputPassword(driver).sendKeys(pass);
    }

    public void pressLogin(AppiumDriver driver){
        System.out.println("Clicking the login button");
        btnLogin(driver).click();

    }
    private WebDriverWait setWait (AppiumDriver driver,Integer timeout){
     return new WebDriverWait(driver,Duration.ofMillis(timeout));
    }
}
