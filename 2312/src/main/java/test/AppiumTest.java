package test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumTest {

    public static void main(String[] args) {
        // Define the desired capabilities
        DesiredCapabilities dc = new DesiredCapabilities();

        // Set capabilities
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel8pro"); 
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        dc.setCapability("appActivity", "org.khanacademy.android.ui.library.MainActivity"); 

    
        AppiumDriver<MobileElement> driver = null;
        try {
           
            URL appiumServerURL = new URL("http://localhost:4723/wd/hub");
        
            driver = new AppiumDriver<>(appiumServerURL, dc);

            
            String elementId = "a75f1e3b-e2da-47d3-805b-6e0de0ba648e";
            MobileElement element = driver.findElementById(elementId);

       
            if (element.isDisplayed()) {
                element.click();
                System.out.println("Clicked on element with elementId: " + elementId);
            } else {
                System.out.println("Element not found or not displayed");
            }

        } catch (MalformedURLException e) {
            System.out.println("Invalid Appium server URL: " + e.getMessage());
        } finally {
      
            if (driver != null) {
                driver.quit();
                System.out.println("Driver quit successfully");
            }
        }
    }
}