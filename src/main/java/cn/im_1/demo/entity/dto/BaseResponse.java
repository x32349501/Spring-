package cn.im_1.demo.entity.dto;

public class BaseResponse {
    public static final int CODE_OK = 0;
    public static final int CODE_ERROR = -1;
    public static final int CODE_EXPIRE = -2;
    public static final int CODE_INVALID = -3;

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
