package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
public class SimpleDbController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/simple/db/test")
    public Map<String, Object> simpleDbTest() {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> rows = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, NAME, age FROM user LIMIT 5")) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("id", rs.getLong("id"));
                row.put("name", rs.getString("NAME"));
                row.put("age", rs.getInt("age"));
                rows.add(row);
            }

            result.put("success", true);
            result.put("data", rows);
            result.put("count", rows.size());

        } catch (Exception e) {
            result.put("success", false);
            result.put("error", e.getClass().getName());
            result.put("message", e.getMessage());
            e.printStackTrace();  // 控制台会打印详细错误
        }

        return result;
    }
}