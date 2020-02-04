package cn.im_1.demo.service;

import cn.im_1.demo.entity.bo.BUser;
import cn.im_1.demo.entity.dto.BaseResponse;
import cn.im_1.demo.entity.dto.UsersResponse;

public interface UserService {
    BaseResponse login(BUser user);

    UsersResponse getUserList();

}
