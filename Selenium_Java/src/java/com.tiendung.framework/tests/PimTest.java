//package com.tiendung.framework.tests;
//
//// CHÚ Ý: Phải là import của org.testng
//import org.testng.annotations.Test;
//import com.tiendung.framework.pages.LoginPage;
//import com.tiendung.framework.pages.PIMPage;
//
//// Kế thừa BaseTest để có driver
//public class PimTest extends BaseTest {
//
//    @Test // Đây là TestNG, không phải JUnit
//    public void testSearchEmployee() {
//        // 1. Khởi tạo các trang
//        LoginPage loginPage = new LoginPage(driver);
//        PIMPage pimPage = new PIMPage(driver);
//
//        // 2. Thực hiện Login
//        loginPage.login("Admin", "admin123");
//
//        // 3. Vào PIM và tìm kiếm
//        pimPage.navigateToPIM();
//        pimPage.searchEmployee("Alexandra");
//
//        // 4. In kết quả
//        pimPage.printEmployeeList();
//    }
//}