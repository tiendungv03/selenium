package com.tiendung.framework.base;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;

public class FileHelper {

    // 1. Hàm đọc dữ liệu từ file properties (Lấy user/pass)
    public static String getProp(String key) {
        Properties prop = new Properties();
        try {
            // Đường dẫn đến file tạo ở Bước 1
            FileInputStream fis = new FileInputStream("account.properties");
            prop.load(fis);
            return prop.getProperty(key);
        } catch (IOException e) {
            System.out.println("Lỗi đọc file properties: " + e.getMessage());
            return "";
        }
    }

    // 2. Hàm ghi nhật ký (Log) ra file text
    public static void writeLog(String message) {
        try {
            // true nghĩa là ghi nối tiếp (append), không xóa nội dung cũ
            FileWriter writer = new FileWriter("test-log.txt", true);
            String time = LocalDateTime.now().toString();
            writer.write("[" + time + "] " + message + "\n");
            writer.close();
            System.out.println("Log: " + message); // In ra màn hình để dễ xem luôn
        } catch (IOException e) {
            System.out.println("Lỗi ghi log: " + e.getMessage());
        }
    }
}