package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/test")
    public String test() {
        return "UserController is working";
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        try {
            System.out.println("收到添加请求: " + user);
            boolean save = userService.save(user);
            result.put("success", save);
            result.put("message", save ? "添加成功" : "添加失败");
            result.put("data", user);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "添加失败: " + e.getMessage());
            result.put("error", e.getClass().getName());
            e.printStackTrace();  // 这会在控制台打印完整错误
        }
        return result;
    }

    @GetMapping("/get/{id}")
    public Map<String, Object> getUser(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            System.out.println("查询用户ID: " + id);
            User user = userService.getById(id);
            if (user != null) {
                result.put("success", true);
                result.put("data", user);
            } else {
                result.put("success", false);
                result.put("message", "用户不存在");
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询失败: " + e.getMessage());
            result.put("error", e.getClass().getName());
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/list")
    public Map<String, Object> list() {
        Map<String, Object> result = new HashMap<>();
        try {
            System.out.println("查询所有用户");
            List<User> list = userService.list();
            result.put("success", true);
            result.put("data", list);
            result.put("total", list.size());
            System.out.println("查询到 " + list.size() + " 条记录");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询失败: " + e.getMessage());
            result.put("error", e.getClass().getName());
            e.printStackTrace();
        }
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            System.out.println("删除用户ID: " + id);
            boolean remove = userService.removeById(id);
            result.put("success", remove);
            result.put("message", remove ? "删除成功" : "删除失败");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "删除失败: " + e.getMessage());
            result.put("error", e.getClass().getName());
            e.printStackTrace();
        }
        return result;
    }
}