package com.frameworkstructure.myproject.utility;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


class ScreenshotHook {
    private WebDriver driver;


        @After
        public void tearDown(Scenario scenario) {
            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "screenshot");

                File screenshotFile = new File("screenshot.png");
                try (FileOutputStream fos = new FileOutputStream(screenshotFile)) {
                    fos.write(screenshot);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    XrayIntegration.attachScreenshotToXray("XRAY-TEST-KEY", screenshotFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            driver.quit();
        }




}


