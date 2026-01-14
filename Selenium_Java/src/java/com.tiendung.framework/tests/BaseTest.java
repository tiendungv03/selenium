package com.tiendung.framework.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    // Khai báo Logger cho BaseTest
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void createDriver() {
        logger.info("-------------- KHỞI TẠO BROWSER --------------");

        // 1. Cấu hình Chrome Options cho ổn định trên Linux
        org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // Fix lỗi kết nối WebSocket
        options.addArguments("--no-sandbox"); // Quan trọng cho Linux
        options.addArguments("--disable-dev-shm-usage"); // Khắc phục lỗi thiếu bộ nhớ share trên Linux
        options.addArguments("--disable-gpu"); // Tắt GPU nếu không cần thiết
        options.addArguments("--window-size=1920,1080"); // Set kích thước chuẩn thay vì maximize

        // 2. Khởi tạo Driver với Options
        driver = new ChromeDriver(options);

        // Không cần maximize nữa vì đã set window-size ở trên (ổn định hơn)
        // driver.manage().window().maximize();

        // 3. Tăng thời gian chờ lên 60 giây (1 phút)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60)); // Tăng lên 60s

        String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        driver.get(url);
        logger.info("Đã mở trang: " + url);
    }

    @AfterMethod
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
            logger.info("Đã đóng trình duyệt.");
        }
        logger.info("-------------- KẾT THÚC TEST CASE --------------");
    }


}