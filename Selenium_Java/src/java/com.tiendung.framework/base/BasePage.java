package com.tiendung.framework.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    // Khai báo Logger để ghi log
    protected static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        // Sửa 10 thành 60 (hoặc 40) để Selenium kiên nhẫn hơn
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    // --- CÁC HÀM TIỆN ÍCH (HELPER) ---

    // 1. Hàm đợi cái vòng xoay Loading biến mất (Fix lỗi Click bị chặn)
    public void waitForLoadingGone() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        } catch (Exception e) {
            // Nếu không có loading thì bỏ qua, không sao cả
        }
    }

    // 2. Hàm đợi một phần tử xuất hiện (Hàm bạn đang cần)
    public void waitForElementVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Timeout: Không tìm thấy element " + locator);
            throw e; // Ném lỗi ra để báo Test Failed
        }
    }

    // --- CÁC HÀM TƯƠNG TÁC (ACTIONS) ---

    // Click chuột (có đợi loading và đợi clickable)
    public void clickElement(By locator) {
        waitForLoadingGone();
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        logger.info("Clicked on element: " + locator);
    }

    // Điền chữ (có đợi loading, click, clear rồi mới điền)
    public void setText(By locator, String text) {
        waitForLoadingGone();
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        element.clear();
        element.sendKeys(text);
        logger.info("Set text '" + text + "' to element: " + locator);
    }

    // Lấy chữ (getText)
    public String getText(By locator) {
        waitForLoadingGone();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }
}