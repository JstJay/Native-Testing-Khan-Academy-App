package test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class KhanAcademyAppTest {

    private AndroidDriver<MobileElement> driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");  
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator"); 
        caps.setCapability("appPackage", "org.khanacademy.android"); 
        caps.setCapability("appActivity", "org.khanacademy.android.ui.library.MainActivity");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

    
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
    }

    @org.junit.Test
    public void testBookmarkCryptographyTopics() throws InterruptedException {
      
        driver.findElement(By.xpath("//android.widget.TextView[@text='Explore']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Computing']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Computer Science']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Cryptography']")).click();


        bookmarkTopic("Ancient Cryptography");
        bookmarkTopic("Cryptography Challenge 101");
        bookmarkTopic("Modular Arithmetic");

  
        driver.navigate().back();
        driver.navigate().back();

      
        driver.findElement(By.xpath("//android.widget.TextView[@text='Information Theory']")).click();
        bookmarkTopic("Modern Information Theory");

    
        driver.findElement(By.xpath("//android.widget.TextView[@text='Bookmark']")).click();
        Assert.assertTrue(isTopicBookmarked("Ancient Cryptography"));
        Assert.assertTrue(isTopicBookmarked("Cryptography Challenge 101"));
        Assert.assertTrue(isTopicBookmarked("Modular Arithmetic"));
        Assert.assertTrue(isTopicBookmarked("Modern Information Theory"));

       
        clearAllBookmarks();
    }

    @Test
    public void testFinancialLiteracyTopic() {
       
        driver.findElement(By.xpath("//android.widget.TextView[@text='Explore']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Life Skills']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Financial Literacy']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@index='0']")).click(); 

       
        driver.findElement(By.xpath("//android.widget.TextView[@text='Home']")).click();
        String recentLesson = driver.findElement(By.xpath("//android.widget.TextView[@text='Recent Lessons']")).getText();
        junit.framework.Assert.assertTrue(recentLesson.contains("Financial Literacy")); 
    }

    private void bookmarkTopic(String topicName) {
     
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + topicName + "']")).click();
        driver.findElement(By.xpath("//android.widget.Button[@text='Bookmark']")).click();
        driver.navigate().back();
    }

    private boolean isTopicBookmarked(String topicName) {
    
        List<MobileElement> bookmarks = driver.findElements(By.xpath("//android.widget.TextView[@text='" + topicName + "']"));
        return bookmarks.size() > 0;
    }

    private void clearAllBookmarks() {
     
        driver.findElement(By.xpath("//android.widget.Button[@text='Edit']")).click();
        List<MobileElement> deleteButtons = driver.findElements(By.xpath("//android.widget.Button[@text='Delete']"));
        for (MobileElement deleteButton : deleteButtons) {
            deleteButton.click();
        }
        driver.findElement(By.xpath("//android.widget.Button[@text='Done']")).click();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();  
        }
    }
}
