package com.tiendung.framework.pages;

import com.tiendung.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // 1. Locators (Khai báo vị trí)
    private By usernameInput = By.name("username"); // Tối ưu hơn xpath placeholder
    private By passwordInput = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // 2. Actions (Hành động)
    public void login(String username, String password) {
        setText(usernameInput, username);
        setText(passwordInput, password);
        clickElement(loginButton);
    }
}