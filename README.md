
# Mobile App Testing with Appium - Khan Academy App

## Objective:
Automate the testing of the Khan Academy app using Java with Appium.

## Tools Required:
- **Appium**: For automating the mobile app.
- **IDE**: Eclipse or IntelliJ IDEA.
- **Appium Java Client**: To interact with mobile elements.
- **Emulator/Real Device**: For running the app.

## Pre-requisites:
1. Download the Khan Academy app from the [Google Play Store](https://play.google.com/store/apps/details?id=org.khanacademy.android&hl=en_IN).
2. Ensure the Appium server is running.
3. Use the following configurations for Appium setup:
   - **Platform Name**: Android
   - **Platform Version**: 10.0
   - **Device Name**: Android Emulator
   - **App Package**: `org.khanacademy.android`
   - **App Activity**: `org.khanacademy.android.ui.library.MainActivity`
   - **Automation Name**: UiAutomator2

## Test Cases:

### 1. Bookmark Cryptography Topics

- **Steps**:
  1. Tap on **Explore**.
  2. Tap on **Computing** > **Computer Science** > **Cryptography**.
  3. Bookmark the following topics:
     - Ancient Cryptography
     - Cryptography Challenge 101
     - Modular Arithmetic
  4. Navigate back to the Computer Science page.
  5. Tap on **Information Theory** and bookmark **Modern Information Theory**.
  6. Tap on **Bookmark** and verify that all the bookmarks are present.
  7. Clear all bookmarks (edit and delete them).

- **Code Snippet**:
  ```java
  driver.findElement(By.xpath("//android.widget.TextView[@text='Explore']")).click();
  driver.findElement(By.xpath("//android.widget.TextView[@text='Computing']")).click();
  driver.findElement(By.xpath("//android.widget.TextView[@text='Computer Science']")).click();
  driver.findElement(By.xpath("//android.widget.TextView[@text='Cryptography']")).click();
  
  bookmarkTopic("Ancient Cryptography");
  bookmarkTopic("Cryptography Challenge 101");
  bookmarkTopic("Modular Arithmetic");
  
  driver.navigate().back();
  driver.findElement(By.xpath("//android.widget.TextView[@text='Information Theory']")).click();
  bookmarkTopic("Modern Information Theory");
  
  driver.findElement(By.xpath("//android.widget.TextView[@text='Bookmark']")).click();
  Assert.assertTrue(isTopicBookmarked("Ancient Cryptography"));
  Assert.assertTrue(isTopicBookmarked("Cryptography Challenge 101"));
  Assert.assertTrue(isTopicBookmarked("Modular Arithmetic"));
  Assert.assertTrue(isTopicBookmarked("Modern Information Theory"));
  
  clearAllBookmarks();
  ```

### 2. Test Financial Literacy Topic

- **Steps**:
  1. Tap on **Explore**.
  2. Navigate to **Life Skills** > **Financial Literacy**.
  3. Open the first topic under Financial Literacy.
  4. Tap on **Home** and verify that the last seen topic name under **Recent Lessons** contains "Financial Literacy".

- **Code Snippet**:
  ```java
  driver.findElement(By.xpath("//android.widget.TextView[@text='Explore']")).click();
  driver.findElement(By.xpath("//android.widget.TextView[@text='Life Skills']")).click();
  driver.findElement(By.xpath("//android.widget.TextView[@text='Financial Literacy']")).click();
  driver.findElement(By.xpath("//android.widget.TextView[@index='0']")).click(); 
  
  driver.findElement(By.xpath("//android.widget.TextView[@text='Home']")).click();
  String recentLesson = driver.findElement(By.xpath("//android.widget.TextView[@text='Recent Lessons']")).getText();
  Assert.assertTrue(recentLesson.contains("Financial Literacy"));
  ```

## Helper Methods:

### Bookmark a Topic
```java
private void bookmarkTopic(String topicName) {
    driver.findElement(By.xpath("//android.widget.TextView[@text='" + topicName + "']")).click();
    driver.findElement(By.xpath("//android.widget.Button[@text='Bookmark']")).click();
    driver.navigate().back();
}
```

### Check if a Topic is Bookmarked
```java
private boolean isTopicBookmarked(String topicName) {
    List<MobileElement> bookmarks = driver.findElements(By.xpath("//android.widget.TextView[@text='" + topicName + "']"));
    return bookmarks.size() > 0;
}
```

### Clear All Bookmarks
```java
private void clearAllBookmarks() {
    driver.findElement(By.xpath("//android.widget.Button[@text='Edit']")).click();
    List<MobileElement> deleteButtons = driver.findElements(By.xpath("//android.widget.Button[@text='Delete']"));
    for (MobileElement deleteButton : deleteButtons) {
        deleteButton.click();
    }
    driver.findElement(By.xpath("//android.widget.Button[@text='Done']")).click();
}
```

## Test Suite Setup:

### `@BeforeClass`:
Initializes the driver and configures the desired capabilities.

### `@AfterClass`:
Tears down the driver after the test is completed.

```java
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

@AfterClass
public void tearDown() {
    if (driver != null) {
        driver.quit();
    }
}
```

## Conclusion:
This automation script tests the bookmarking functionality and validates topics in the Khan Academy app using Appium and Java.
