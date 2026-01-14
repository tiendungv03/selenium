package com.tiendung.framework.pages;

import com.tiendung.framework.base.BasePage;
import com.tiendung.framework.base.FileHelper; // Nhớ import cái này
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class PIMPage extends BasePage {

    // --- LOCATORS ---
    private By menuPIM = By.xpath("//aside[@class='oxd-sidepanel']//li[2]");
    private By buttonAdd = By.xpath("//button[normalize-space()='Add']");

    private By inputFirstName = By.name("firstName");
    private By inputLastName = By.name("lastName");
    // XPath tối ưu hơn dùng contains để tránh lỗi khoảng trắng
    private By inputEmployeeID = By.xpath("//label[contains(text(),'Employee Id')]/parent::div/parent::div//input");
    private By buttonSave = By.xpath("//button[@type='submit']");
    private By successMessage = By.xpath("//div[contains(@class, 'oxd-toast-content--success')]");

    private By menuEmployeeList = By.xpath("//a[normalize-space()='Employee List']");
    private By inputSearchID = By.xpath("//label[contains(text(),'Employee Id')]/parent::div/parent::div//input");
    private By buttonSearch = By.xpath("//button[@type='submit']");

    public PIMPage(WebDriver driver) {
        super(driver);
    }

    // --- ACTIONS ---

    public void navigateToPIM() {
        waitForLoadingGone();
        clickElement(menuPIM);
        FileHelper.writeLog("Đã vào trang PIM.");
    }

    public void addNewEmployee(String firstName, String lastName, String empID) {
        waitForLoadingGone();
        clickElement(buttonAdd);
        waitForLoadingGone(); // Đợi form load xong

        setText(inputFirstName, firstName);
        setText(inputLastName, lastName);

        // Xử lý ID kỹ càng: Xóa cũ nhập mới bằng phím tắt
        WebElement idEle = driver.findElement(inputEmployeeID);
        idEle.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        idEle.sendKeys(Keys.DELETE);
        idEle.sendKeys(empID);

        FileHelper.writeLog("Đang thêm nhân viên: " + firstName + " " + lastName + " - ID: " + empID);

        clickElement(buttonSave);

        // Verify Success Toast
        try {
            waitForElementVisible(successMessage);
            FileHelper.writeLog("PASSED: Thấy thông báo thành công!");
        } catch (Exception e) {
            FileHelper.writeLog("WARNING: Không kịp thấy thông báo thành công (có thể ẩn quá nhanh).");
        }

        // Đợi spinner lặn sau khi save
        waitForLoadingGone();
    }

    public void verifyEmployeeInList(String empID) {
        FileHelper.writeLog("Bắt đầu xác thực nhân viên ID: " + empID);

        // 1. Refresh trang
        driver.navigate().refresh();
        waitForLoadingGone();

        // 2. Vào tab danh sách
        clickElement(menuEmployeeList);
        waitForLoadingGone();

        // 3. Search
        setText(inputSearchID, empID);
        clickElement(buttonSearch);
        waitForLoadingGone();

        // 4. Verify
        try {
            // Tìm cell chứa đúng ID đó
            By cellID = By.xpath("//div[contains(@class, 'oxd-table-cell')]//div[text()='" + empID + "']");
            waitForElementVisible(cellID);

            FileHelper.writeLog("PASSED: Đã tìm thấy nhân viên " + empID + " trong bảng!");
        } catch (Exception e) {
            FileHelper.writeLog("FAILED: Không tìm thấy nhân viên ID " + empID);
            Assert.fail("Không tìm thấy nhân viên trong danh sách!");
        }
    }
}