package com.web.back.state;


import lombok.Data;

@Data
public class ResposeResult<T>{
    private int code;
    private String message;

    private T response;

    public ResposeResult(int code, String message, T response) {
        this.code = code;
        this.message = message;
        this.response = response;
    }

    public ResposeResult(int code, String message) {
        this.code = code;
        this.message = message;
    }
//
//    /**
//     * Faul ResponseResult
//     * @return
//     */
//    public static ResponseResult fail(Integer code, String message){
//        return new ResponseResult<>(code, message);
//    }
//
//    public static ResponseResult ok(){
//        SystemCode systemCode = SystemCode.OK;
//        return new ResponseResult<>(systemCode.getCode(),systemCode.getMessage());
//    }
//
//    public static <F> ResponseResult<F> ok(F response){
//        SystemCode systemCode = SystemCode.OK;
//        return new ResponseResult<>(systemCode.getCode(),systemCode.getMessage());
//    }
//
//    public int getCode(){
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public T getResponse() {
//        return response;
//    }
//    public void setResponse(T response) {
//        this.response = response;
//    }
}
