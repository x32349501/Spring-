package cn.im_1.demo.entity.dto;

import cn.im_1.demo.entity.po.PUser;

public class LoginResponse extends BaseResponse {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(String token, PUser pUser) {
        data = new Data();
        data.token = token;
        data.name = pUser.getName();
    }

    public static class Data {
        private String token;
        private String name;

        public String getToken() {
            return token;
        }

        public String getName() {
            return name;
        }
    }
}
