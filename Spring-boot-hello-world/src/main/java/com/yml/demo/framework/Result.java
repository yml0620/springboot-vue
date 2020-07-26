package com.yml.demo.framework;
public class Result {
    // 返回code
    private Integer code;
    // 响应消息
    private String msg;
    // 响应中的数据
    private Object data;
    
    public static Result success(){
        return new Result(200, "成功", null);
    }

    public static Result success(Object data){
        return new Result(200, "成功", data);
    }

    public static Result error(){
        return new Result(500, "失败", null);
    }

    public static Result error(String msg){
        return new Result(500, msg, null);
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
