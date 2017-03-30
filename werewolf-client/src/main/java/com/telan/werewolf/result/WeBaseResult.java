package com.telan.werewolf.result;

/**
 * Created by 海浩 on 2015/3/29.
 *
 */
public class WeBaseResult<T> extends WeResultSupport {
    private static final long serialVersionUID = 4999091548448313101L;
    protected T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    

    public static <U> WeBaseResult<U> buildFailResult(int errorCode ,String errorMsg, U value) {
    	WeBaseResult<U> baseResult = new WeBaseResult<U>();
        baseResult.setSuccess(false);
        baseResult.setErrorCode(errorCode);
        baseResult.setValue(value);
        baseResult.setResultMsg(errorMsg);

        return baseResult;
    }

    public static <U> WeBaseResult<U> buildSuccessResult(U value) {
    	WeBaseResult<U> baseResult = new WeBaseResult<U>();
        baseResult.setSuccess(true);
        baseResult.setValue(value);
        return baseResult;
    }

}
