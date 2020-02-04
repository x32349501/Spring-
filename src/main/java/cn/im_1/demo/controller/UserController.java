package cn.im_1.demo.controller;

import cn.im_1.demo.entity.bo.BUser;
import cn.im_1.demo.entity.dto.BaseResponse;
import cn.im_1.demo.entity.dto.UsersResponse;
import cn.im_1.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 登陆验证
     *
     * @return BaseResponse
     */
    @ResponseBody
    @PostMapping("/login")
    public BaseResponse login(@RequestBody BUser user) {
        return userService.login(user);
    }

    /**
     * 用户列表
     *
     * @return UsersResponse
     */
    @ResponseBody
    @GetMapping("/user_list")
    public UsersResponse getUserList() {
        return userService.getUserList();
    }
}
