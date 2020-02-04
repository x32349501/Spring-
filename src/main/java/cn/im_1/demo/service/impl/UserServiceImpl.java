package cn.im_1.demo.service.impl;

import cn.im_1.demo.entity.bo.BUser;
import cn.im_1.demo.entity.dto.BaseResponse;
import cn.im_1.demo.entity.dto.LoginResponse;
import cn.im_1.demo.entity.dto.UsersResponse;
import cn.im_1.demo.entity.po.PUser;
import cn.im_1.demo.mapper.UserMapper;
import cn.im_1.demo.service.UserService;
import cn.im_1.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static cn.im_1.demo.entity.dto.BaseResponse.CODE_ERROR;
import static cn.im_1.demo.entity.dto.BaseResponse.CODE_OK;

@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public BaseResponse login(BUser user) {
        PUser pUser = userMapper.login(user);

        LoginResponse response = new LoginResponse();
        if (pUser != null) {
            String token = UUID.randomUUID().toString();
            // Redis失效时间7200秒（2小时）
            RedisUtil.getInstance().set(token, pUser, 7200);
            response.setCode(CODE_OK);
            response.setMsg("Login successful.");
            response.setData(token, pUser);
        } else {
            response.setCode(CODE_ERROR);
            response.setMsg("手机号或密码错误！");
        }
        return response;
    }

    @Override
    public UsersResponse getUserList() {
        List<PUser> list = userMapper.getUserList();
        UsersResponse response = new UsersResponse();
        response.setCode(CODE_OK);
        response.setMsg("Get user list successful.");
        response.setData(list);
        return response;
    }
}
