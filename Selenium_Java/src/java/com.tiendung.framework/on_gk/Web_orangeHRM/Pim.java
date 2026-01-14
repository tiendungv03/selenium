package com.tiendung.framework.on_gk.Web_orangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static run_Test.com.tiendung.framework.Initialization.Init.SetUp;
import static run_Test.com.tiendung.framework.Initialization.Init.driver;

public class Pim {
    public static void main(String[] args) throws InterruptedException {
        /*
        // login
        Buoc 1: vô trang "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
        Buoc 2: nhập username
        Buoc 3: nhập password
        Buoc 4: bấm nút Login
         */
        SetUp("chrome");
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();

        WebElement username = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        username.sendKeys("Admin");

        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        password.sendKeys("admin123");

        WebElement btnLogin = driver.findElement(By.xpath("//button[@type='submit']"));
        btnLogin.submit();

        WebElement page_pim = driver.findElement(By.xpath("//aside[@class='oxd-sidepanel']//li[2]"));
        page_pim.click();

        Thread.sleep(4000);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        /*
        // login
        Buoc 1: vô trang
        Buoc 2:
        Buoc 3:
        Buoc 4:
         */



//        WebElement btnadd = driver.findElement(By.xpath("//button[normalize-space()='Add']"));
//        btnadd.click();
//
//        WebElement firstName = driver.findElement(By.xpath("//input[@placeholder='First Name']"));
//        firstName.click();
//        firstName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
//        firstName.sendKeys(Keys.DELETE);
//        firstName.sendKeys("dung99");
//
//        WebElement middleName = driver.findElement(By.xpath("//input[@placeholder='Middle Name']"));
//        middleName.click();
//        middleName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
//        middleName.sendKeys(Keys.DELETE);
//        middleName.sendKeys("hhh");
//
//
//
//        WebElement lastName = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
//        lastName.click();
//        lastName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
//        lastName.sendKeys(Keys.DELETE);
//        lastName.sendKeys("vo");
//
//
//        Thread.sleep(2000);
//        WebElement btnSave = driver.findElement(
//                By.xpath("//button[normalize-space()='Save']"));
//        btnSave.submit();

        WebElement search_name = driver.findElement(By.xpath("//div[@class='oxd-grid-4 orangehrm-full-width-grid']//div[1]//div[1]//div[2]//div[1]//div[1]//input[1]"));
        search_name.sendKeys("Alexandra");

        WebElement btnsearch = driver.findElement(By.xpath("//button[normalize-space()='Search']"));
        btnsearch.click();


        // in danh sách
//        WebElement table_nv = driver.findElement(By.xpath("//div[@role='table']"));
//
//        List<WebElement> item = table_nv.findElements(By.xpath("//div[@class='oxd-table-body']"));

        // Đợi bảng hiện
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[role='table']")));

// Lấy tất cả HÀNG trong phần thân bảng
        List<WebElement> rows = driver.findElements(
                By.cssSelector("[role='table'] [role='rowgroup'] [role='row']"));

// In tổng số hàng
        System.out.println("So hang: " + rows.size());

// Duyệt từng hàng -> lấy các Ô và in text
        for (int i = 0; i < rows.size(); i++) {
            WebElement row = rows.get(i);
            List<WebElement> cells = row.findElements(By.cssSelector("[role='cell']"));
            System.out.print("Row " + (i+1) + ": ");
            for (WebElement cell : cells) {
                System.out.print(cell.getText().trim().replace("\n", " ") + " | ");
            }
            System.out.println();
        }



    }
}
