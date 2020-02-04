package cn.im_1.demo.entity.dto;

import cn.im_1.demo.entity.po.PUser;

import java.util.ArrayList;
import java.util.List;

public class UsersResponse extends BaseResponse {
    private List<Data> data = new ArrayList<>();

    public List<Data> getData() {
        return data;
    }

    public void setData(List<PUser> list) {
        for (PUser user : list) {
            Data d = new Data();
            d.id = user.getId();
            d.phone = user.getPhone();
            d.name = user.getName();
            data.add(d);
        }
    }

    public static class Data {
        private int id;
        private String phone;
        private String name;

        public int getId() {
            return id;
        }

        public String getPhone() {
            return phone;
        }

        public String getName() {
            return name;
        }
    }
}
