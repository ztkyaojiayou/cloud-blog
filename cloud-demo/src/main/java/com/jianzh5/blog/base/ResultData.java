package com.jianzh5.blog.base;

import lombok.Data;

/**
 * 抽取公共返回结果
 * @author JAVA日知录
 * @date 2019/12/4 14:56
 */
@Data
public class ResultData<T> {
    /** 结果状态 ,具体状态码参见ResultData.java*/
    private int status;
    /**响应消息**/
    private String message;
    /**响应数据**/
    private T data;
    /**接口请求时间**/
    private long timestamp ;


    public ResultData (){
        this.timestamp = System.currentTimeMillis();
    }


    public static <T> ResultData<T> success(String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ReturnCode.RC100.getCode());
//        resultData.setSuccess(true);
        resultData.setMessage(message);
        return resultData;
    }

    public static <T> ResultData<T> success() {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ReturnCode.RC100.getCode());
//        resultData.setSuccess(true);
        resultData.setMessage(ReturnCode.RC100.getMessage());
        return resultData;
    }

    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ReturnCode.RC100.getCode());
//        resultData.setSuccess(true);
        resultData.setMessage(ReturnCode.RC100.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> fail(String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ReturnCode.RC999.getCode());
//        resultData.setSuccess(false);
        resultData.setMessage(message);
        return resultData;
    }

    public static <T> ResultData<T> fail(int code, String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(code);
//        resultData.setSuccess(false);
        resultData.setMessage(message);
        return resultData;
    }

    public static <T>ResultData<T> fail() {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ReturnCode.RC999.getCode());
//        resultData.setSuccess(false);
        resultData.setMessage(ReturnCode.RC999.getMessage());
        return resultData;
    }

}
