package com.apidemos;

import com.common.util.Util;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ApiDemos {
    public String appUnderTest = "ApiDemos-debug.apk";
    public Util util = new Util();

    @Test
    public void testAppLaunch() {
        AppiumDriver driver = util.launchApp(appUnderTest);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000));
        WebElement views = driver.findElement(AppiumBy.accessibilityId("Views"));
        views.click();
        WebElement expList = driver.findElement(AppiumBy.accessibilityId("Expandable Lists"));
        expList.click();
        WebElement cAdp = driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter"));
        cAdp.click();
        WebElement pplNames = driver.findElement(AppiumBy.xpath("//*[@text=\"People Names\"]"));
        util.longPress(driver, pplNames);
    }


    @Test
    public void testScroll() {
        AppiumDriver appiumDriver = util.launchApp(appUnderTest);

        WebElement view = appiumDriver.findElement(AppiumBy.accessibilityId("Views"));
        view.click();
        //util.scrollDown(appiumDriver);
        //util.scrollUIAutomatorText(appiumDriver,"WebView3");
        //util.scrollUiAutomatortxt(appiumDriver,"WebView3");
        //util.scrollUiAutomatorResID(appiumDriver,"android:id/text1");
    }

    @Test
    public void testScrollToEnd() {
        AppiumDriver appiumDriver = util.launchApp(appUnderTest);

        //Navigate to Lists
        WebElement views = appiumDriver.findElement(AppiumBy.accessibilityId("Views"));
        views.click();
        //Scroll to list
        util.scrollUIAutomatorText(appiumDriver, "Lists");
        //Click on List
        WebElement lists = appiumDriver.findElement(AppiumBy.accessibilityId("Lists"));
        lists.click();
        //Click on Array
        WebElement arrays = appiumDriver.findElement(AppiumBy.accessibilityId("01. Array"));
        arrays.click();
        util.scrollToEnd(appiumDriver, 50);
    }

    @Test
    public void testControls() throws MalformedURLException, InterruptedException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("PXL_9Pro");
        options.setApp(System.getProperty("user.dir") + "/Apps/" + appUnderTest);

        URL url = new URL("http://127.0.0.1:4723");
        AppiumDriver appiumDriver = new AppiumDriver(url, options);

        //Navigate to Controls
        WebElement views = appiumDriver.findElement(AppiumBy.accessibilityId("Views"));
        views.click();
        WebElement controls = appiumDriver.findElement(AppiumBy.accessibilityId("Controls"));
        controls.click();
        WebElement customTheme = appiumDriver.findElement(AppiumBy.accessibilityId("5. Custom Theme"));
        customTheme.click();

        //Text Input
        /*WebElement text1 = appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.EditText[@resource-id=\"io.appium.android.apis:id/edit\"]"));
        //Check box
        WebElement checkBox1 = appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.CheckBox[@resource-id=\"io.appium.android.apis:id/check1\"]"));
        WebElement checkBox2 = appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.CheckBox[@resource-id=\"io.appium.android.apis:id/check2\"]"));*/

        //Radio
        WebElement radio1 = appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.RadioButton[@content-desc=\"RadioButton 1\"]"));
        WebElement radio2 = appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.RadioButton[@content-desc=\"RadioButton 2\"]"));

        //Operations on the Controls
        /*text1.sendKeys("This is a test !");
        checkBox1.click();
        checkBox2.click();
        checkBox1.clear();
        checkBox2.clear();
        radio1.click();
        radio2.click();*/
        //Drop Down
        WebElement select = appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.Spinner[@resource-id=\"io.appium.android.apis:id/spinner1\"]"));

        List<WebElement> dropDownList = appiumDriver.
                findElements(AppiumBy.xpath("//android.widget.Spinner[@resource-id=\"io.appium.android.apis:id/spinner1\"]"));
        //select each element in the drop down list
        //Select dd = new Select(dropDownList);
        /*for (WebElement ele:dropDownList){
            ele.click();
        }*/
        System.out.println("dropDownList: "+dropDownList.size());
        System.out.println("First Text: " + dropDownList.get(0).getText());
        dropDownList.get(0).click();
        Thread.sleep(1000);
        WebElement eleSaturn = appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Saturn\"]"));
        eleSaturn.click();
        /*List<WebElement> listView = appiumDriver.findElements(AppiumBy.
                xpath("//android.widget.ListView[@resource-id=\"android:id/select_dialog_listview\"]"));
        //listView.getFirst().click();
        //listView.getLast().click();

        System.out.println("Size: "+listView.size());*/

    }
    @Test
    public void switchTabs () throws MalformedURLException, InterruptedException {
        //Connect to the Appium Server
        UiAutomator2Options options = new UiAutomator2Options();
        options.setApp(System.getProperty("user.dir")+ "/Apps/"+ appUnderTest);
        options.setDeviceName("PXL_9Pro");
        URL url = new URL("http://127.0.0.1:4723");
        AppiumDriver appiumDriver = new AppiumDriver(url,options);
        //Navigate to TABs
        WebElement views = appiumDriver.findElement(AppiumBy.accessibilityId("Views"));
        views.click();
        util.scrollUiAutomatortxt(appiumDriver,"Tabs");
        WebElement tabs = appiumDriver.findElement(AppiumBy.accessibilityId("Tabs"));
        tabs.click();
        WebElement tabsScreen = appiumDriver.findElement(AppiumBy.accessibilityId("1. Content By Id"));
        tabsScreen.click();
        Thread.sleep(1000);
        WebElement tab3 = appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"TAB3\"]"));
        tab3.click();
        WebElement tab2 = appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"TAB2\"]"));
        tab2.click();
        WebElement tab1 = appiumDriver.findElement(AppiumBy.
                xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"TAB1\"]"));
        tab1.click();
        //WebElement tabList = appiumDriver.findElement(AppiumBy.accessibilityId("LIST"));
        //tabList.click();
        //WebElement tabDestroy = appiumDriver.findElement(AppiumBy.accessibilityId("DESTROY"));
        //tabDestroy.click();
        //tabList.click();

        /*Set<String> tabHandles = appiumDriver.getWindowHandles();
        System.out.println("tabHandles: " + tabHandles.size());
        for (String handle: tabHandles){
            appiumDriver.switchTo().window(handle);
        }*/
       // appiumDriver.switchTo().window("PHOTO LIST");


    }

    @Test
    public void dragDrop (){
        AppiumDriver appiumDriver = util.launchApp(appUnderTest);
        WebElement views = appiumDriver.findElement(AppiumBy.accessibilityId("Views"));
        views.click();
        //util.scrollUiAutomatortxt(appiumDriver,"Drag and Drop");
        WebElement dragDrop = appiumDriver.findElement(AppiumBy.accessibilityId("Drag and Drop"));
        dragDrop.click();
        //Drag and Drop Operation
        WebElement dot1 = appiumDriver.findElement(AppiumBy.
                xpath("//android.view.View[@resource-id=\"io.appium.android.apis:id/drag_dot_1\"]"));
        WebElement dot2 = appiumDriver.findElement(AppiumBy.
                xpath("//android.view.View[@resource-id=\"io.appium.android.apis:id/drag_dot_2\"]"));
        //Drag Dot1 to Dot1

        Point centerDot1 = new Point(dot1.getLocation().getX() + dot1.getSize().width/2,
                dot1.getLocation().getY() + dot1.getSize().height/2);
        Point centerDot2 = new Point(dot2.getLocation().getX() + dot2.getSize().width/2,
                dot2.getLocation().getY() + dot2.getSize().height/2);
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH,"finger1");
        Sequence actions = new Sequence(finger1,1);
        actions.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),centerDot1))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1,Duration.ofMillis(2000)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(400), PointerInput.Origin.viewport(),centerDot2))
                .addAction(new Pause(finger1,Duration.ofMillis(400)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        appiumDriver.perform(Collections.singleton(actions));
    }

    @Test//On-Hold
    public void testSpinner(){
        AppiumDriver appiumDriver = util.launchApp(appUnderTest);
        WebElement views = appiumDriver.findElement(AppiumBy.accessibilityId("Views"));
        views.click();
        util.scrollUiAutomatortxt(appiumDriver,"Spinner");
        WebElement spinner = appiumDriver.findElement(AppiumBy.accessibilityId("Spinner"));
        spinner.click();
        //
        WebElement listView = appiumDriver.findElement(AppiumBy.xpath("//android.widget.ListView"));

    }
}
