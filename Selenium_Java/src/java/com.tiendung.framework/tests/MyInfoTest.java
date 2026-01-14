package com.tiendung.framework.tests;

import com.tiendung.framework.pages.LoginPage;
import com.tiendung.framework.pages.MyInfoPage;
import org.testng.annotations.Test;

public class MyInfoTest extends BaseTest {

    @Test
    public void testUpdateInfo() {
        // 1. Khởi tạo Pages
        LoginPage loginPage = new LoginPage(driver);
        MyInfoPage myInfoPage = new MyInfoPage(driver);

        // 2. Đăng nhập
        loginPage.login("Admin", "admin123");

        // 3. Vào trang My Info
        myInfoPage.goToMyInfo();

        // 4. Cập nhật thông tin (Họ tên, ID)
        myInfoPage.updatePersonalDetails("Dung", "Huy", "Vo", "JD999");

        // 5. Chọn quốc tịch
        myInfoPage.selectNationality("Vietnamese");

        // 6. Lưu lại
        myInfoPage.clickSave();

        // (Có thể thêm Assert verify thông báo "Successfully Saved" nếu muốn)
    }
}