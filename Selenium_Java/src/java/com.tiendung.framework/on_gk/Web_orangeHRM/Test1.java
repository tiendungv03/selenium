package com.tiendung.framework.on_gk.Web_orangeHRM;

import java.time.Duration;
import java.time.Instant;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import javax.swing.*;

import static run_Test.com.tiendung.framework.Initialization.Init.*;

public class Test1{
    public static void main(String[] args) throws InterruptedException {
        SetUp("chrome");
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();

        WebElement username = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        username.sendKeys("Admin");

        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        password.sendKeys("admin123");

        WebElement btnLogin = driver.findElement(By.xpath("//button[@type='submit']"));
        btnLogin.submit();

        WebElement my_Info = driver.findElement(By.xpath("//li[6]"));
        my_Info.click();

        Thread.sleep(4000);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        / --- Sửa First Name: Ctrl+A -> Delete -> nhập mới
        WebElement firstName = driver.findElement(By.xpath("//input[@placeholder='First Name']"));
        firstName.click();
        firstName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        firstName.sendKeys(Keys.DELETE);
        firstName.sendKeys("dung123");



        WebElement middleName = driver.findElement(By.xpath("//input[@placeholder='Middle Name']"));
        middleName.click();
        middleName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        middleName.sendKeys(Keys.DELETE);
        middleName.sendKeys("hhh");



        WebElement lastName = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
        lastName.click();
        lastName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        lastName.sendKeys(Keys.DELETE);
        lastName.sendKeys("vo");


        WebElement Employee_Id = driver.findElement(By.xpath("//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-card-container']/div[@class='orangehrm-edit-employee']/div[@class='orangehrm-edit-employee-content']/div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']/form[@class='oxd-form']/div[@class='oxd-form-row']/div[1]/div[1]/div[1]/div[2]/input[1]"));
        Employee_Id.click();
        Employee_Id.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        Employee_Id.sendKeys(Keys.DELETE);
        Employee_Id.sendKeys("jdung12");

        // MỞ dropdown Nationality (oxd-select)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));

        WebElement natDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[normalize-space()='Nationality']/following::div[contains(@class,'oxd-select-text')][1]")));
        natDropdown.click();

// CHỌN 'Vietnamese'
        WebElement natOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='listbox']//span[normalize-space()='Vietnamese']")));
        natOption.click();




        WebElement btnSave = driver.findElement(
                By.xpath("//button[@type='submit'][normalize-space()='Save']"));
        btnSave.submit();

    }
}
