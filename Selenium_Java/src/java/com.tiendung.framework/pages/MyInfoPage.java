package com.tiendung.framework.pages;

import com.tiendung.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyInfoPage extends BasePage {

    // --- 1. LOCATORS (Vị trí) ---
    private By myInfoMenu = By.xpath("//aside[@class='oxd-sidepanel']//li[6]"); // Menu My Info

    // Các ô input
    private By firstNameInput = By.name("firstName");
    private By middleNameInput = By.name("middleName");
    private By lastNameInput = By.name("lastName");

    // XPath tìm Employee ID dựa vào Label (Xịn hơn cái XPath dài dòng cũ)
    private By employeeIdInput = By.xpath("//label[contains(text(),'Employee Id')]/parent::div/parent::div//input");

    // Dropdown Nationality
    private By nationalityDropdown = By.xpath("//label[contains(text(),'Nationality')]/parent::div/parent::div//div[contains(@class,'oxd-select-text')]");

    // Nút Save (Dùng xpath này để tìm nút Save đầu tiên trong form Personal Details)
    private By saveButton = By.xpath("(//button[@type='submit'][normalize-space()='Save'])[1]");

    private By loadingSpinner = By.cssSelector(".oxd-form-loader");

    // --- 2. CONSTRUCTOR ---
    public MyInfoPage(WebDriver driver) {
        super(driver);
    }



    // --- 3. ACTIONS (Hành động) ---

    public void goToMyInfo() {
        clickElement(myInfoMenu);
        // Đợi xíu cho trang load ảnh đại diện lên là biết đã vào trang
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".orangehrm-edit-employee-image")));
    }

    // Hàm tiện ích để xóa cũ nhập mới (Ctrl A + Delete)
    // Hàm tiện ích để xóa cũ nhập mới (Ctrl A + Delete)
    public void clearAndType(By locator, String text) {
        // 1. Đợi cái xoay xoay biến mất
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));
        } catch (Exception e) {
            // Không có spinner thì bỏ qua
        }

        // 2. Tìm element
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        // 3. FIX LỖI Ở ĐÂY: Scroll element ra GIỮA màn hình (block: 'center')
        // Thay vì scrollIntoView(true) -> bị menu che
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

        // Nghỉ cực ngắn để giao diện ổn định sau khi cuộn
        try { Thread.sleep(500); } catch (InterruptedException e) {}

        // 4. Click và nhập liệu
        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);
        element.sendKeys(text);
    }

    public void updatePersonalDetails(String fName, String mName, String lName, String empID) {
        clearAndType(firstNameInput, fName);
        clearAndType(middleNameInput, mName);
        clearAndType(lastNameInput, lName);
        clearAndType(employeeIdInput, empID);
    }

    public void selectNationality(String nationality) {
        clickElement(nationalityDropdown);
        // Chọn quốc gia trong list xổ ra
        By option = By.xpath("//div[@role='listbox']//span[normalize-space()='" + nationality + "']");
        clickElement(option);
    }

    public void clickSave() {
        clickElement(saveButton);
    }
}