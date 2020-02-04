package cn.im_1.demo.mapper;

import cn.im_1.demo.entity.bo.BUser;
import cn.im_1.demo.entity.po.PUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    PUser login(@Param("u") BUser user);

    List<PUser> getUserList();
}
