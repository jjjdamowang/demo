package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest  // 这会加载完整的 Spring Boot 应用上下文
// 或者可以使用以下组合注解
// @SpringBootTest(classes = DemoApplication.class)  // 明确指定启动类
public class DataSourceTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            // 使用 try-with-resources 自动关闭连接
            System.out.println("连接成功！数据库地址: " + connection.getMetaData().getURL());
            System.out.println("数据库用户名: " + connection.getMetaData().getUserName());
            System.out.println("数据库产品名: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("数据库版本: " + connection.getMetaData().getDatabaseProductVersion());
        } catch (SQLException e) {
            System.err.println("连接失败！错误信息: " + e.getMessage());
            e.printStackTrace();
        }
    }
}