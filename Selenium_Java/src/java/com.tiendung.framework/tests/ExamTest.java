package com.tiendung.framework.tests;

import com.tiendung.framework.base.FileHelper; // Import FileHelper
import com.tiendung.framework.pages.LoginPage;
import com.tiendung.framework.pages.PIMPage;
import org.testng.annotations.Test;
import java.util.Random;

public class ExamTest extends BaseTest {

    @Test
    public void testAddEmployeeAndVerify() {
        LoginPage loginPage = new LoginPage(driver);
        PIMPage pimPage = new PIMPage(driver);

        // Tạo dữ liệu ngẫu nhiên
        String firstName = "Tien";
        String lastName = "Dung";
        String employeeID = "TD" + new Random().nextInt(9999);

        // --- ĐỌC FILE ACCOUNT Ở ĐÂY ---
        // Gọi hàm getProp từ FileHelper
        String user = FileHelper.getProp("username");
        String pass = FileHelper.getProp("password");

        // 1. Login bằng tài khoản đọc được
        loginPage.login(user, pass);

        // 2. Vào PIM -> Thêm nhân viên
        pimPage.navigateToPIM();
        pimPage.addNewEmployee(firstName, lastName, employeeID);

        // 3. Xác thực lại trong danh sách
        pimPage.verifyEmployeeInList(employeeID);
    }
}