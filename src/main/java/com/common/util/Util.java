package com.common.util;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

public class Util {

    private AppiumDriver driver;

    public AppiumDriver launchApp(String appName) {
        System.out.println("Launching: " + appName);
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("PXL_9Pro");
        //options.setDeviceName("Google Pixel 8 API 34");
        options.setApp(System.getProperty("user.dir") + "/Apps/" + appName);
        System.out.println(System.getProperty("user.dir"));
        try {
            URL url = new URL("http://127.0.0.1:4723/");
            System.out.println("Launching the APK");
            driver = new AppiumDriver(url, options);
            new WebDriverWait(driver, Duration.ofMillis(1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
    }

    public void tapAction(AppiumDriver driver, WebElement element) {
        Point loginOrigin = new Point(element.getLocation().x, element.getLocation().y);
        Dimension dim = element.getSize();
        Point centre = new Point(loginOrigin.x + dim.width / 2, loginOrigin.y + dim.height / 2);
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence actions = new Sequence(finger1, 1);
        System.out.println("Using the single Tap on the Login Button");
        actions.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centre))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(300)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singleton(actions));
    }

    public void doubleTap(AppiumDriver driver, WebElement element) {
        System.out.println("Double Tap");
        Point origin = new Point(element.getLocation().x, element.getLocation().y);
        Dimension dimension = element.getSize();
        Point center = new Point(origin.getX() + dimension.width / 2, origin.getY() + dimension.height / 2);
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence actions = new Sequence(finger1, 1);
        actions.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), center))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(100)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(100)))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(100)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singleton(actions));

    }

    public void longPress(AppiumDriver driver, WebElement element) {
        //new Actions(driver,Duration.ofSeconds(2)).clickAndHold(element).perform();
        System.out.println("Long Press");
        Point origin = new Point(element.getLocation().getX(), element.getLocation().getY());
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Dimension dim = element.getSize();
        Point center = new Point(origin.getX() + dim.width / 2, origin.getY() + dim.height / 2);
        Sequence actions = new Sequence(finger1, 1);
        actions.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), center))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofSeconds(2)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        System.out.println("Perform the actions");
        driver.perform(Collections.singleton(actions));
    }

    public void zoomScreen(AppiumDriver driver, WebElement element) {
        //Screen Center
        Dimension dim = element.getSize();
        Point origin = new Point(element.getLocation().getX(), element.getLocation().getY());
        Point center = new Point(element.getLocation().getX() + dim.getWidth() / 2, element.getLocation().getY() + dim.getHeight() / 2);
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
        Sequence seq1 = new Sequence(finger1, 1);
        seq1.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), center))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(200),
                        PointerInput.Origin.viewport(), center.getX() + 1000, center.getY() - 1000))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        Sequence seq2 = new Sequence(finger2, 1);
        seq2.addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), center))
                .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger2.createPointerMove(Duration.ZERO,
                        PointerInput.Origin.viewport(), center.getX() - 1000, center.getY() + 1000))
                .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(seq1, seq2));
    }

    public void swipeLeft(AppiumDriver appiumDriver, WebElement card){
        Dimension dimScreen = appiumDriver.manage().window().getSize();
        Point ref = card.getLocation();
        Dimension dimCard = card.getSize();
        Point pointStart = new Point(ref.x + dimCard.width/2, ref.y+dimCard.height/2);
        Point pointEnd = new Point(ref.x - dimCard.width/3, ref.y+dimCard.height/2);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        Sequence actions = new Sequence(finger,1);
        actions.addAction(finger.createPointerMove(Duration.ofMillis(50), PointerInput.Origin.viewport(),pointStart))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger,Duration.ofMillis(100)))
                .addAction(finger.createPointerMove(Duration.ofMillis(800), PointerInput.Origin.viewport(), pointEnd))
                .addAction(new Pause(finger,Duration.ofMillis(100)))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger,Duration.ofMillis(300)));

        appiumDriver.perform(Collections.singleton(actions));

        WebDriverWait wait = new WebDriverWait(appiumDriver,Duration.ofMillis(5000));
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(card)));
        }catch (TimeoutException e){
            System.out.println("Time out occurred");
            e.printStackTrace();
        }
    }

    public void scrollDown(AppiumDriver appiumDriver) {
        Dimension dim = appiumDriver.manage().window().getSize();
        System.out.println("Screen Dim: " + dim);
        int startX = (int)(dim.width *0.5);
        int startY = (int)(dim.height * 0.5);
        int endY = (int)(dim.height * 0.3);
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence actions = new Sequence(finger1, 1);
        actions.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),startX,startY))//move to center
                .addAction(new Pause(finger1,Duration.ofMillis(500)))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1,Duration.ofMillis(500)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), startX,endY))
                .addAction(new Pause(finger1, Duration.ofMillis(500)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))//lift up
                .addAction(new Pause(finger1,Duration.ofMillis(500)))
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),startX,(int) (startY*(0.8/0.5))))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(500)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), startX,endY))
                .addAction(new Pause(finger1, Duration.ofMillis(500)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))//lift up
                .addAction(new Pause(finger1,Duration.ofMillis(500)))
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),startX,(int) (startY*(0.8/0.5))))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(500)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), startX,endY))
                .addAction(new Pause(finger1, Duration.ofMillis(500)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        appiumDriver.perform(Collections.singleton(actions));

    }

    public void scrollUIAutomatorText(AppiumDriver appiumDriver, String str){
        appiumDriver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\""+str+"\"))"));
    }
    public void scrollUiAutomatortxt(AppiumDriver appiumDriver, String str){
        AppiumBy.ByAndroidUIAutomator byAndroidUIAutomator = new AppiumBy.
                ByAndroidUIAutomator("new UiScrollable(new UiSelector())." +
                "scrollIntoView(new UiSelector().text(\""+str+"\"))");
        appiumDriver.findElement(byAndroidUIAutomator);
    }

    public void scrollUiAutomatorResID(AppiumDriver appiumDriver, String resID){
        AppiumBy.ByAndroidUIAutomator byAndroidUIAutomator = new AppiumBy.
                ByAndroidUIAutomator("new UiScrollable(new UiSelector())." +
                "scrollIntoView(new UiSelector().resourceId(\""+resID+"\"))");
        appiumDriver.findElement(byAndroidUIAutomator);
    }

    public void scrollToEnd(AppiumDriver appiumDriver, int steps){
        AppiumBy.ByAndroidUIAutomator uiAutomator= new AppiumBy.
                ByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollToEnd("+steps+")");
        appiumDriver.findElement(uiAutomator);
    }

    public void swipeUiLocatorHorizontal(AppiumDriver appiumDriver, String str){
        appiumDriver.findElement(AppiumBy.
                androidUIAutomator(
                        "new UiScrollable(new UiSelector).setAsHorizontalList()." +
                                "scrollToView(new UiSelector().text(\""+str+"\"))"));
    }
    public void swipeLeft(AppiumDriver appiumDriver){
        //Get the Screen Dim
        Dimension dim = appiumDriver.manage().window().getSize();

        //
        //Point start = new Point();

    }
}

