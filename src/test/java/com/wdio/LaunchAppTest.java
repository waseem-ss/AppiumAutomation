package com.wdio;


import com.common.util.Util;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class LaunchAppTest {

    //UiAutomator2Options options = new UiAutomator2Options();
    public String appUnderTest = "android.wdio.native.app.v1.0.8.apk";
    public Util util = new Util();

    /*
    public void testSetup()  {
        System.out.println("Test Setup");
        options = new UiAutomator2Options();
        options.setDeviceName("PXL_9Pro");
        System.out.println(System.getProperty("user.dir"));
        options.setApp(System.getProperty("user.dir")+"/Apps/android.wdio.native.app.v1.0.8.apk");
        try{
            URL url = new URL("http://127.0.0.1:4723/");
            System.out.println("Launching the APK");
            util.launchApp();
            AppiumDriver appiumDriver;
            appiumDriver = new AppiumDriver(url,options);
            appiumDriver.manage().wait(2000);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
*/
    @Test
    public void testHomePage() {
        //Click on the Home page and read the text
        System.out.println("Setting up the Mobile Session");
        //testSetup();
        System.out.println("Clicking on the Home Button");
        AppiumDriver appiumDriver = util.launchApp(appUnderTest);
        WebElement login = appiumDriver.findElement(AppiumBy.accessibilityId("Login"));
        WebElement home = appiumDriver.findElement(AppiumBy.accessibilityId("Home"));
        WebElement forms = appiumDriver.findElement(AppiumBy.accessibilityId("Forms"));
        WebElement drag = appiumDriver.findElement(AppiumBy.accessibilityId("Drag"));
        home.click();
        //Click on the Login Button
        System.out.println("Clicking on the Login Button");
        login.click();
        //Click on the Forms
        System.out.println("Clicking on the Forms Button");
        forms.click();
        System.out.println("Clicking on the Drag Button");
        drag.click();
        System.out.println("Clicking on the Home Button");
        home.click();
    }

    @Test
    public void testLoginPage() {
        //Click the Login button on Home Page
        System.out.println("Setting up the Mobile Session");
        //testSetup();

        System.out.println("Clicking on the Login Button");
        AppiumDriver appiumDriver = util.launchApp(appUnderTest);
        appiumDriver.findElement(AppiumBy.accessibilityId("Login")).click();
    }

    @Test
    public void testUsingTap() {
        System.out.println("Setting up the Mobile Session");
        //util.launchApp();
        AppiumDriver appiumDriver = util.launchApp(appUnderTest);
        WebElement login = appiumDriver.findElement(AppiumBy.accessibilityId("Login"));
        WebElement home = appiumDriver.findElement(AppiumBy.accessibilityId("Home"));
        WebElement forms = appiumDriver.findElement(AppiumBy.accessibilityId("Forms"));
        WebElement drag = appiumDriver.findElement(AppiumBy.accessibilityId("Drag"));
        util.tapAction(appiumDriver, login);
        util.tapAction(appiumDriver, home);
        util.tapAction(appiumDriver, drag);
        util.tapAction(appiumDriver, home);
    }

    @Test
    public void testSwitch() {

        AppiumDriver appiumDriver = util.launchApp(appUnderTest);
        WebElement forms = appiumDriver.findElement(AppiumBy.accessibilityId("Forms"));
        util.tapAction(appiumDriver, forms);
        WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofMillis(1000));
        WebElement switchEle = appiumDriver.findElement(AppiumBy.accessibilityId("switch"));
        util.doubleTap(appiumDriver, switchEle);
    }

    @Test
    public void formTest() {
        AppiumDriver appiumDriver = util.launchApp(appUnderTest);

    }

    @Test
    public void testZoomHomePage() {
        AppiumDriver appiumDriver = util.launchApp(appUnderTest);
        WebElement homeScreen = appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.TextView[@text=\"WEBDRIVER\"]"));
        WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofMillis(2000));
        wait.until(ExpectedConditions.visibilityOf(homeScreen));
        System.out.println(homeScreen.getText());
        util.zoomScreen(appiumDriver, homeScreen);
    }

    //Swipe and Scroll
    @Test
    public void testScrollDown1() {
        AppiumDriver driver = util.launchApp(appUnderTest);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        //Assert.assertEquals();
        WebElement swipe = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Swipe\"]"));
        wait.until(ExpectedConditions.visibilityOf(swipe)).click();
        //util.tapAction(driver,swipe);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement swipeText = driver.findElement(AppiumBy.
                xpath("//android.widget.TextView[@text=\"Swipe horizontal\"]"));
        String actualText = wait.until(ExpectedConditions.visibilityOf(swipeText)).getText();
        System.out.println("Swipe: " +actualText);
        /*Assert.assertEquals(actualText, "Swipe horizontal",
                "Loaded the Swipe Screen");*/

        //driver.findElement(AppiumBy.ByAndroidUIAutomator.xpath("//android.widget.TextView[@text=\"You found me!!!\"]"));
        //driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(\"You found me!!\")"));
//        driver.findElement(AppiumBy.androidUIAutomator(
//                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(text(\"You found me!!\"))"));

        util.scrollDown(driver);

        /*driver.findElement(AppiumBy.androidUIAutomator(
              "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(3)"));*/

        /*
        try {
            WebElement youFoundMe = driver.findElement(AppiumBy.
                    xpath("//android.widget.TextView[@text=\"You found me!!!\"]"));
            while (youFoundMe == null) {
                util.scrollDown(driver);
                youFoundMe = driver.findElement(AppiumBy.
                        xpath("//android.widget.TextView[@text=\"You found me!!!\"]"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
    @Test
    public void testSwipeBottom() throws InterruptedException {
        AppiumDriver appiumDriver = util.launchApp(appUnderTest);
        //Navigate to swipe
        appiumDriver.findElement(AppiumBy.accessibilityId("Swipe")).click();
        Thread.sleep(1000);
        WebElement text1 = appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.TextView[@text=\"Swipe horizontal\"]"));
        Assert.assertEquals(text1.getText(),"Swipe horizontal","loaded the page");
        String end = "You found me!!!";
        //util.scrollUiAutomatortxt(appiumDriver,end);
        //util.scrollToEnd(appiumDriver,5);
        util.scrollDown(appiumDriver);

        System.out.println(appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.TextView[@text=\"You found me!!!\"]")).getText());
    }
    @Test
    public void testSwipeCards() throws MalformedURLException, InterruptedException {
        //Launch the APK
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("PXL_9Pro");
        options.setApp(System.getProperty("user.dir") + "/Apps/" + appUnderTest);
        URL url = new URL("http://127.0.0.1:4723");
        AppiumDriver appiumDriver = new AppiumDriver(url,options);
        Thread.sleep(1000);
        appiumDriver.findElement(AppiumBy.accessibilityId("Swipe")).click();

        WebElement card1 = appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.TextView[@text=\"FULLY OPEN SOURCE\"]"));
        util.swipeLeft(appiumDriver,card1);
        WebElement card2 = appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.TextView[@text=\"GREAT COMMUNITY\"]"));
        util.swipeLeft(appiumDriver,card2);
        WebElement card3 = appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.TextView[@text=\"JS.FOUNDATION\"]"));
        util.swipeLeft(appiumDriver,card3);
        WebElement card4 = appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.TextView[@text=\"SUPPORT VIDEOS\"]"));
        util.swipeLeft(appiumDriver,card4);
        WebElement card5 = appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.TextView[@text=\"EXTENDABLE\"]"));
        util.swipeLeft(appiumDriver,card5);
        WebElement card6 = appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.TextView[@text=\"COMPATIBLE\"]"));
        System.out.println("Navigated to the last card: "+ card6.getText());
        //util.swipeLeft(appiumDriver,card6);


    }
    @Test
    public void testDropDown() throws InterruptedException {
        AppiumDriver appiumDriver = util.launchApp(appUnderTest);
        //Navigate to Forms
        Thread.sleep(1000);
        appiumDriver.findElement(AppiumBy.accessibilityId("Forms")).click();

        WebElement dropDown = appiumDriver.findElement(AppiumBy.
                xpath("//android.view.ViewGroup[@content-desc=\"Dropdown\"]"));
        dropDown.click();
        Thread.sleep(2000);
        WebElement option1 = appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" " +
                        "and @text=\"webdriver.io is awesome\"]"));
        option1.click();
        Thread.sleep(1000);
        System.out.println(dropDown.getText());

        /*List<WebElement> listView = appiumDriver.findElements(AppiumBy.
                xpath("//android.widget.ListView[@resource-id=\"com.wdiodemoapp:id/select_dialog_listview\"]"));
        List<WebElement> listNames = appiumDriver.findElements(AppiumBy.className("android.widget.ListView"));
        System.out.println("Element: " + listNames.size());
        System.out.println(listNames.get(1).getText());
        listNames.get(1).click();*/



    }

}
