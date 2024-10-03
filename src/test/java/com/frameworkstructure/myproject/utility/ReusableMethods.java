package com.frameworkstructure.myproject.utility;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ReusableMethods {
    private static String homeWindow = null;
    static Logger logger = LogManager.getLogger(ReusableMethods.class);

    public static void click(WebElement element) {
        try {
            waitForElementVisibility(element);
            element.click();
        } catch (Exception e) {
            logger.error("Error clicking element", e);
        }
    }

    public static void accept_Alert() {
        try {
            Alert alert = DriverManager.getDriver().switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            logger.error("Error accepting alert", e);
        }
    }

    public static String screenCapture(String imgLocation) {
        File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(imgLocation));
        } catch (IOException e) {
            logger.error("Error capturing screenshot", e);
        }
        return imgLocation;
    }

    public static void switchToWindow() {
        homeWindow = DriverManager.getDriver().getWindowHandle();
        for (String window : DriverManager.getDriver().getWindowHandles()) {
            DriverManager.getDriver().switchTo().window(window);
        }
    }

    public static void switchToMainWindow() {
        for (String window : DriverManager.getDriver().getWindowHandles()) {
            if (!window.equals(homeWindow)) {
                DriverManager.getDriver().switchTo().window(window);
                DriverManager.getDriver().close();
            }
            DriverManager.getDriver().switchTo().window(homeWindow);
        }
    }

    public static int getWindowCount() {
        return DriverManager.getDriver().getWindowHandles().size();
    }

    public static void frames(WebElement frameElement) {
        try {
            DriverManager.getDriver().switchTo().frame(frameElement);
        } catch (Exception e) {
            logger.error("Error switching to frame", e);
        }
    }

    public static void switchToframe(WebElement frameElement) {
        try {
            DriverManager.getDriver().switchTo().frame(frameElement);
        } catch (Exception e) {
            logger.error("Error switching to frame", e);
        }
    }

    public static void switchToDefaultcontent() {
        try {
            DriverManager.getDriver().switchTo().defaultContent();
        } catch (Exception e) {
            logger.error("Error switching to default content", e);
        }
    }

    public static void navigateToUrl(String url) {
        try {
            DriverManager.getDriver().navigate().to(url);
        } catch (Exception e) {
            logger.error("Error navigating to URL", e);
        }
    }

    public static void closeBrowser() {
        try {
            DriverManager.getDriver().close();
        } catch (Exception e) {
            logger.error("Error closing browser", e);
        }
    }

    public static void setText(WebElement element, String value) {
        try {
            waitForElementVisibility(element);
            element.clear();
            element.sendKeys(value);
        } catch (Exception e) {
            logger.error("Error setting text", e);
        }
    }

    public static void implicitWait(long time) {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public static void implicitWait(Duration duration) {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(duration);
    }


    public static boolean isElementPresent(WebElement element) {
        boolean elementPresent = false;
        try {
            waitForElementVisibility(element);
            if (element.isDisplayed()) {
                elementPresent = true;
            }
        } catch (Exception e) {

        }
        return elementPresent;
    }

    /**
     * @Method Name: clickWebElement
     * @Description: To click on particular webElement
     * @param: WebElement, Duration
     * @return: void
     */
    public static void clickWebElement(WebElement element, Duration duration) {
        try {
            waitForElementVisibility(element, duration);
            element.click();
        } catch (Exception e) {
        }
    }

    /**
     * @Method Name: getText
     * @Description: Method to get text from the WebElement
     * @param: WebElement
     * @return: String
     */
    public static String getText(WebElement element) {
        String text = null;
        try {
            waitForElementVisibility(element);
            if (element.getText() != null) {
                text = element.getText();
            }
        } catch (Exception e) {
        }
        return text;
    }

    /**
     * @Method Name: getValue
     * @Description: Method to get Attribute value from the WebElement
     * @param: WebElement
     * @return: String
     */
    public static String getValue(WebElement element) {
        String value = null;
        try {
            waitForElementVisibility(element);
            if (element.getAttribute("value") != null) {
                value = element.getAttribute("value");
            }
        } catch (NullPointerException e) {
        }
        return value;
    }

    /**
     * @Method Name: selectByValue
     * @Description: To Select particular value from the WebElement
     * @param: WebElement, String
     * @return: void
     */
    public static void selectByValue(WebElement element, String value) {
        try {
            Select obj_select = new Select(element);
            obj_select.selectByValue(value);
        } catch (Exception e) {
        }
    }

    /**
     * @Method Name: selectByText
     * @Description: To Select particular Text from the WebElement
     * @param: WebElement, String
     * @return: void
     */
    public static void selectByText(WebElement element, String text) {
        try {
            Select obj_select = new Select(element);
            obj_select.selectByVisibleText(text);
        } catch (Exception e) {
        }
    }

    /**
     * @Method Name: selectByIndex
     * @Description: To Select particular Index from the WebElement
     * @param: WebElement, int
     * @return: void
     */
    public static void selectByIndex(WebElement element, int index) {
        try {
            Select obj_select = new Select(element);
            obj_select.selectByIndex(index);
        } catch (Exception e) {
        }
    }

    public void jsMouseOver(WebElement element) {
        String code = "var fireOnThis = arguments[0];" + "var evObj = document.createEvent('MouseEvents');"
                + "evObj.initEvent( 'mouseover', true, true );" + "fireOnThis.dispatchEvent(evObj);";
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript(code, element);
    }

    /**
     * @Method Name: jsMouseHover
     * @Description: Method to move the mouse on particular webElement using Javascript
     * @param: WebElement
     * @return: void
     */
    public void jsMouseHover(WebElement element) {
        try {
            String code = "var fireOnThis = arguments[0];" + "var evObj = document.createEvent('MouseEvents');"
                    + "evObj.initEvent( 'mouseover', true, true );" + "fireOnThis.dispatchEvent(evObj);";
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript(code, element);
        } catch (StaleElementReferenceException e) {
            logger.info("Element with " + element + "is not attached to the page document" + e.getStackTrace());
        } catch (NoSuchElementException e) {
            logger.info("Element " + element + " was not found in DOM" + e.getStackTrace());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Error occurred while hovering" + e.getStackTrace());
        }
    }

    /**
     * @param
     * @Method Name: jsWaitForPageLoad
     * @Description: To wait for page be loaded using JavaScript
     * @return: void
     */
    public static void jsWaitForPageLoad() {
        String pageReadyState = (String) ((JavascriptExecutor) DriverManager.getDriver())
                .executeScript("return document.readyState");
        while (!pageReadyState.equals("complete")) {
            pageReadyState = (String) ((JavascriptExecutor) DriverManager.getDriver())
                    .executeScript("return document.readyState");

        }
    }

    /**
     * @Method Name: waitForElementVisibility
     * @Description: To wait till particular Webelement be visible
     * @param: WebElement, Duration
     * @return: void
     */
    public static void waitForElementVisibility(WebElement element, Duration duration) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), duration);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * @Method Name: pause
     * @Description: Method to pause the thread
     * @param: int
     * @return: void
     */
    public static void pause(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException ie) {
            Thread.interrupted();
        }
    }

    /**
     * @Method Name: enterDate
     * @Description: Method to select the particular date from the page
     * @param: String, String
     * @return: void
     */
    public static void enterDate(String startDate, String endDate) {
        try {
            String dateValue = startDate;
            String[] originDate = dateValue.split("/");
            String ODate = originDate[0];
            String Odates = "(//*[@data-date='" + ODate + "'])[1]";
            String dateValue1 = endDate;
            String[] DestDate = dateValue1.split("/");
            String DDate = DestDate[0];
            DriverManager.getDriver().findElement(By.xpath(Odates)).click();
            Thread.sleep(3000);
            if (Integer.parseInt(DDate) < Integer.parseInt(ODate)) {
                String Ddates = "(//*[@data-date='" + DDate + "'])[1]";
                DriverManager.getDriver().findElement(By.xpath(Ddates)).click();
            } else {
                String Ddates = "(//*[@data-date='" + DDate + "'])[2]";
                DriverManager.getDriver().findElement(By.xpath(Ddates)).click();
            }

        } catch (Exception e) {
            e.getLocalizedMessage();
        }

    }

    public static void highLightElement(WebDriver webDriver, WebElement element) throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }

    public static void scrollToWebElement(WebDriver webDriver, WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public static void unHighLightElement(WebDriver webDriver, WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].removeAttribute('style','')", element);
    }

    /**
     * @Method Name: highLightElement
     * @Description: Method to highlight the webelement
     * @param: WebElement
     * @return: void
     */
    public static void highLightElement(WebElement element) throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }

    public static void waitForSpinnerComplete() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
        ExpectedCondition<Boolean> spinner = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return DriverManager.getDriver().findElement(By.xpath("//div[@class='entry-spinner-container']"))
                        .getCssValue("display").equals("none");
            }
        };
        wait.until(spinner);
    }

    public static void scrollToWebElement(WebElement webElement) {

        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    /**
     * @Method Name: unHighLightElement
     * @Description: Method to unhighlight the webelement
     * @param: WebElement
     * @return: void
     */
    public static void unHighLightElement(WebElement webElement) throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].removeAttribute('style','')", webElement);
    }

    /**
     * @Method Name: waitForSpinnerComplete
     * @Description: Wait for the spinner to complete
     * @param: WebElement, Duration
     * @return: void
     */
    public static void waitForSpinnerComplete(WebElement webElement, Duration duration) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), duration);
        ExpectedCondition<Boolean> spinner = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webElement.getCssValue("display").equals("none");
            }
        };
        wait.until(spinner);
    }

    public static void waitForSpinnerComplete(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
        ExpectedCondition<Boolean> spinner = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webElement.getCssValue("display").equals("none");
            }
        };
        wait.until(spinner);
    }

    /**
     * @Method Name: clickOnWebElementJS
     * @Description: Click  on the webElement using Javascript
     * @param: WebElement
     * @return: void
     */
    public static void clickOnWebElementJS(WebElement webElement) {
        try {
            webElement.click();
        } catch (WebDriverException e) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click()", webElement);
        }
    }

    public static void moveToWebElement(WebDriver webDriver, WebElement webElement) {

        if (ConfigReader.getProperty("Test_Platform").equalsIgnoreCase("firefox")) {
            try {
                Actions actions = new Actions(webDriver);
                actions.moveToElement(webElement);
                Thread.sleep(5000);
            } catch (Exception e) {

            }
        } else {
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).build().perform();
        }
    }

    public static void clickOn(WebElement webElmenet, WebDriver driver, int timeout) {
        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(webElmenet)));
        webElmenet.click();
    }


    /**
     * @Method Name: moveToWebElement
     * @Description: Move the mouse to the middle of the element
     * @param: WebElement
     * @return: void
     */
    public static void moveToWebElement(WebElement webElement) {

        if (ConfigReader.getProperty("Test_Platform").equalsIgnoreCase("firefox")) {
            try {
                Actions actions = new Actions(DriverManager.getDriver());
                actions.moveToElement(webElement);

            } catch (Exception e) {

            }
        } else {
            Actions actions = new Actions(DriverManager.getDriver());
            actions.moveToElement(webElement).build().perform();
        }
    }

    /**
     * @param : WebElement, Duration
     * @Method Name: clickOn
     * @Description: Wait till Element be visible and clickable
     * @return: void
     */
    public static void clickOn(WebElement webElement, Duration duration) {
        final WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), duration);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(webElement)));
        webElement.click();
    }

    public static void clickOn(WebElement webElement) {
        final WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(webElement)));
        webElement.click();
    }

    /**
     * @Method Name: windowScrollTo
     * @Description: Scroll the Webpage from X-Coordinate to Y-Coordinate
     * @param: int, int
     * @return: void
     */
    public static void windowScrollTo(int xCoordinate, int yCoordinate) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollTo(" + xCoordinate + "," + yCoordinate + ")");
    }

    /**
     * @Method Name: windowScrollBy
     * @Description: Sroll the Webpage by Y-Coordinate
     * @param: int
     * @return: void
     */
    public static void windowScrollBy(int yCoordinate) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollBy(0," + yCoordinate + ")");
    }

    /**
     * @Method Name: clickActions
     * @Description: Click on webElement using Actions Class
     * @param: WebElement
     * @return: void
     */
    public static void clickActions(WebElement webElement) {

        Actions action = new Actions(DriverManager.getDriver());
        action.moveToElement(webElement).click().perform();

    }

    public static void waitForElementNonVisibility(By elementBy) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(elementBy));

    }

    /**
     * @Method Name: waitForElementNonVisibility
     * @Description: Wait for Element to become Invisible
     * @param: By, Duration
     * @return: void
     */
    public static void waitForElementInvisibility(By elementBy, Duration duration) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), duration);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(elementBy));

    }

    public static void waitForElementInvisibility(By elementBy) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(elementBy));

    }

    /**
     * @Method Name: validateWebElementColorCSSValue
     * @Description: To validate the color of the webElement
     * @param: WebElement, String
     * @return: boolean
     */
    public static boolean validateWebElementColorCSSValue(WebElement webElement, String expectedColor,
                                                          String attribute, Duration duration) {
        ReusableMethods.waitForElementVisibility(webElement, duration);

        String actualColor = webElement.getCssValue(attribute);
        logger.info("actual :" + actualColor);
        return actualColor.contains(expectedColor);
    }

    public static boolean validateWebElementColorCSSValue(WebElement webElement, String expectedColor, String attribute) {
        ReusableMethods.waitForElementVisibility(webElement);

        String actualColor = webElement.getCssValue(attribute);
        logger.info("actual :" + actualColor);
        return actualColor.contains(expectedColor);
    }

    /**
     * @Method Name: waitForElementClickable
     * @Description: Wait for Element to become clickable
     * @param: WebElement , Duration
     * @return: void
     */
    public static void waitForElementClickable(WebElement webElement, Duration duration) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), duration);
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public static void waitForElementClickable(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Method Name: clickJS
     * @Description: Click on the WebElement using JavaScript
     * @param: WebElement
     * @return: void
     */
    public static void clickJS(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) (DriverManager.getDriver());
        executor.executeScript("arguments[0].click();", webElement);
    }

    public static String getAttributeValue(WebElement element, String attribue) {
        String value = null;
        try {
            waitForElementVisibility(element);
            if (element.getAttribute(attribue) != null) {
                value = element.getAttribute(attribue);
            }
        } catch (NullPointerException e) {
        }
        return value;
    }

    /**
     * @param
     * @Method Name: switchToNewTab
     * @Description: Switch to a new Tab
     * @return: void
     */
    public static void switchToNewTab() {
        WebDriver newTab = DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
        DriverManager.getDriver().switchTo().window(newTab.getWindowHandle());
    }


    /**
     * @param
     * @Method Name: switchToNewWindow
     * @Description: Switch to a new window
     * @return: void
     */
    public static void switchToNewWindow() {
        WebDriver newTab = DriverManager.getDriver().switchTo().newWindow(WindowType.WINDOW);
        DriverManager.getDriver().switchTo().window(newTab.getWindowHandle());
    }


    /**
     * @Method Name: switchBackToMainWindow
     * @Description: Switch Back to main window
     * @param: String
     * @return: void
     */
    public static void switchBackToMainWindow(String windowHandle) {
        logger.info("Current Window Handle = " + windowHandle);
        DriverManager.getDriver().switchTo().window(windowHandle);
    }

    /**
     * @param
     * @Method Name: getCurrentWindowHandle
     * @Description: To get the current Window handle
     * @return: String
     */
    public static String getCurrentWindowHandle() {
        Set<String> handles = DriverManager.getDriver().getWindowHandles();
        List<String> handlesList = new ArrayList<String>(handles);
        String parentWindowHandle = handlesList.get(0);
        return parentWindowHandle;
    }

    /**
     * @param
     * @Method Name: getDriver
     * @Description: To get the webdriver instance
     * @return: WebDriver
     */
    public static WebDriver getDriver() {
        return DriverManager.getDriver();
    }


    /**
     * @param
     * @Method Name: getJSExecutor
     * @Description: To get the JavaScript Executor instance
     * @return: JavascriptExecutor
     */
    public static JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) getDriver();
    }


    /**
     * @Method Name: waitForElementInvisibility
     * @Description: Wait for the element to be invisible
     * @param: WebElement, Duration
     * @return: void
     */
    public static void waitForElementInvisibility(WebElement element, Duration duration) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), duration);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitForElementInvisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * @Method Name: isElementEnabled
     * @Description: verify the particular webelement is enabled or not
     * @param: WebElement, Duration
     * @return: boolean
     */
    public static boolean isElementEnabled(WebElement element, Duration duration) {
        boolean elementPresent = false;
        try {
            waitForElementVisibility(element, duration);
            if (element.isEnabled()) {
                elementPresent = true;
            }
        } catch (Exception e) {

        }
        return elementPresent;
    }

    public static boolean isElementEnabled(WebElement element) {
        boolean elementPresent = false;
        try {
            waitForElementVisibility(element);
            if (element.isEnabled()) {
                elementPresent = true;
            }
        } catch (Exception e) {

        }
        return elementPresent;
    }

    /**
     * @Method Name: isElementSelected
     * @Description: verify the particular webelement is selected or not
     * @param: WebElement
     * @return: boolean
     */
    public static boolean isElementSelected(WebElement element) {
        boolean elementPresent = false;
        try {
            if (element.isSelected()) {
                elementPresent = true;
            }
            return elementPresent;
        } catch (Exception e) {
            return elementPresent;
        }
    }


    /**
     * @param
     * @Method Name: getPageTitle
     * @Description: Get title of the web page
     * @return: String
     */
    public static String getPageTitle() {
        String pageTitle = DriverManager.getDriver().getTitle();
        return pageTitle;

    }


    /**
     * @param
     * @Method Name: getCurrentURL
     * @Description: Get current URL of the page
     * @return: void
     */
    public static String getCurrentURL() {
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        return currentUrl;
    }

    /**
     * @param
     * @Method Name: sendEscapeKey
     * @Description: Send escape key on the active element
     * @return: void
     */
    public static void sendEscapeKey() {
        new Actions(DriverManager.getDriver()).sendKeys(Keys.ESCAPE).build().perform();
    }


    /**
     * @param
     * @Method Name: sendTabKey
     * @Description: Send Tab key on the active element
     * @return: void
     */
    public static void sendTabKey() {
        new Actions(DriverManager.getDriver()).sendKeys(Keys.TAB).build().perform();

    }

    /**
     * @param
     * @Method Name: sendEnterKey
     * @Description: Send Enter key on the active element
     * @return: void
     */
    public static void sendEnterKey() {
        new Actions(DriverManager.getDriver()).sendKeys(Keys.ENTER).build().perform();
    }

    /**
     * @param
     * @Method Name: sendDownKey
     * @Description: Send Down key on the active element
     * @return: void
     */
    public static void sendDownKey() {
        new Actions(DriverManager.getDriver()).sendKeys(Keys.ARROW_DOWN).build().perform();
    }


    /**
     * @param
     * @Method Name: zoomInWebElement
     * @Description: To Zoom in the particular webElement
     * @return: void
     */
    public static void zoomInWebElement() throws InterruptedException {
        DriverManager.getDriver().findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
    }

    /**
     * @param
     * @Method Name: moveCursorToRight
     * @Description: Move the cursor to Right side
     * @return: void
     */
    public static void moveCursorToRight() {
        getJSExecutor().executeScript("window.scrollBy(2000,0)");
    }

    /**
     * @param
     * @Method Name: zoomOut
     * @Description: To zoom out the web page
     * @return: void
     */
    public static void zoomOut() {
        getJSExecutor().executeScript("document.body.style.zoom='50%'");
    }

    /**
     * @param
     * @Method Name: zoomIn
     * @Description: To Zoom in the web page
     * @return: void
     */
    public static void zoomIn() throws InterruptedException {
        getJSExecutor().executeScript("document.body.style.zoom='150%'");
    }

    /**
     * @Method Name: waitForInteractElement
     * @Description: Wait for the element to become Interactable
     * @param: WebElement, Duration
     * @return: void
     */
    public static void waitForInteractElement(WebElement element, Duration duration) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), duration);
        wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForInteractElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
        wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForInteractElement(WebElement elements, int time) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(time));
        wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(elements));
        wait.until(ExpectedConditions.elementToBeClickable(elements));
    }
    /**
     * @Method Name: waitForiFrameLoad
     * @Description: Wait for the frame to be available
     * @param: WebElement, Duration
     * @return: void
     */
    public static void waitForiFrameLoad(WebElement element, Duration duration) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), duration);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    public static void waitForiFrameLoad(WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(time));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    public static void waitForFrameLoad(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    /**
     * @Method Name: closeFrame
     * @Description: This method is to close the frame
     * @param: WebElement, WebElement, Duration
     * @return: void
     */
    public static void closeFrame(WebElement frame, WebElement closeButton, Duration duration) {
        if (isElementPresent(frame)) {
            switchToframe(frame);
            clickWebElement(closeButton, duration);
            switchToDefaultcontent();
        }
    }

    /**
     * @Method Name: launchWeb
     * @Description: Method to launch the URL
     * @param: String
     * @return: void
     */
    public static void launchWeb(String url) {
        DriverManager.getDriver().get(url);
    }

    /**
     * @Method Name: getFirstSelectedOption
     * @Description: To get the First selected option
     * @param: WebElement
     * @return: String
     */
    public static String getFirstSelectedOption(WebElement element) {
        String defaultItem = null;
        try {

            Select select = new Select(element);
            WebElement option = select.getFirstSelectedOption();
            defaultItem = option.getText();
        } catch (Exception e) {

        }
        return defaultItem;
    }

    /**
     * @Method Name: waitForTextVisibility
     * @Description: Wait for text to be visible
     * @param: WebElement, String, Duration
     * @return: void
     */
    public static void waitForTextVisibility(WebElement element, String text, Duration duration) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), duration);
            wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    public static void waitForTextVisibility(WebElement element, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
    /**
     * @param
     * @Method Name: isAlertWindowPresent
     * @Description: To verify the Alert window is present or not
     * @return: boolean
     */
    public static boolean isAlertWindowPresent() {
        boolean presentFlag = false;
        try {
            DriverManager.getDriver().switchTo().alert();
            presentFlag = true;
        } catch (NoAlertPresentException ex) {

        }
        return presentFlag;
    }

    /**
     * @Method Name: authenticateAlertHandle
     * @Description: To authenticate the Alert Window
     * @param: String, String
     * @return: boolean
     */
    public static boolean authenticateAlertHandle(String userName, String password) {
        boolean presentFlag = false;
        try {
            // Check the presence of alert
            Alert alert = DriverManager.getDriver().switchTo().alert();
            // Alert present; set the flag
            presentFlag = true;
            // if present consume the alert
            alert.sendKeys(userName + Keys.TAB + password + Keys.TAB);
            alert.accept();

        } catch (NoAlertPresentException ex) {

        }
        return presentFlag;
    }

    /**
     * @Method Name: setTextAndHitEnter
     * @Description: To set text in the Webelement and press enter key
     * @param: WebElement, String
     * @return: void
     */
    public static void setTextAndHitEnter(WebElement element, String value) {
        //User send the keys and hit the enter button
        element.sendKeys(value);
        Actions act = new Actions(DriverManager.getDriver());
        act.sendKeys(element, Keys.ENTER).build().perform();
    }

    /**
     * @Method Name: setTextAndHitTab
     * @Description: To set text in the Webelement and press Tab key
     * @param: WebElement, String
     * @return: void
     */
    public static void setTextAndHitTab(WebElement element, String value) {
        //User send the keys and hit the tab button
        element.clear();
        element.sendKeys(value);
        Actions act = new Actions(DriverManager.getDriver());
        act.sendKeys(element, Keys.TAB).build().perform();
    }

    /**
     * @param
     * @Method Name: clearText
     * @Description: To clear the text from webelement
     * @return: void
     */
    public static void clearText(WebElement element) {
        element.clear();
    }

    /**
     * @Method Name: doubleClickAction
     * @Description: To double click on the webelement
     * @param: WebElement
     * @return: void
     */
    public static void doubleClickAction(WebElement element) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(element).doubleClick().build().perform();

        } catch (Exception e) {

        }
    }

    /**
     * @param
     * @Method Name: waitForElementVisibilityAll
     * @Description: Wait for the visibility of all Webelements
     * @return: void
     */
    public static void waitForElementVisibilityAll(List<WebElement> webElementList) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(45));
        wait.until(ExpectedConditions.visibilityOfAllElements(webElementList));

    }

    /**
     * @param
     * @Method Name: robotClearAll
     * @Description: To clear data using Robot class
     * @return: void
     */
    public static void robotClearAll() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    /**
     * @param
     * @Method Name: robotPageDown
     * @Description: To move down the page using Robot class
     * @return: void
     */
    public static void robotPageDown() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_DOWN);
        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
    }

    /**
     * @param
     * @Method Name: robotZoomOut
     * @Description: To zoom out of the webpage using Robot class
     * @return: void
     */
    public static void robotZoomOut() throws AWTException {
        Robot robot = new Robot();
        for (int zoomOut = 0; zoomOut < 2; zoomOut++) {
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        }
    }
    /**
     * @Method Name: clickAndHoldAction
     * @Description: To click and hold on the webelement
     * @param: WebElement
     * @return: void
     */
    public static void clickAndHoldAction(WebElement element) {
        try {
            waitForElementVisibility(element);
            Actions action = new Actions(DriverManager.getDriver());
            action.clickAndHold(element).build().perform();

        } catch (Exception e) {
            logger.error("Error while clickAndHold on the WebElement"+e.getMessage());
        }
    }


    /**
     * @Method Name: contextClickAction
     * @Description: To Right click on the webelement
     * @param: WebElement
     * @return: void
     */
    public static void contextClickAction(WebElement element) {
        try {
            waitForElementVisibility(element);
            Actions action = new Actions(DriverManager.getDriver());
            action.contextClick(element).build().perform();

        } catch (Exception e) {
            logger.error("Error while contextClick on the WebElement"+e.getMessage());
        }
    }

    /**
     * @Method Name: releaseAction
     * @Description: To release the pressed mouse button
     * @param: WebElement
     * @return: void
     */
    public static void releaseAction(WebElement element) {
        try {
            waitForElementVisibility(element);
            Actions action = new Actions(DriverManager.getDriver());
            action.release(element).build().perform();

        } catch (Exception e) {
            logger.error("Error while releaseAction on the WebElement"+e.getMessage());
        }
    }

}
