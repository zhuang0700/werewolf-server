package com.telan.weixincenter.result;

/**
 * Created by 海浩 on 2015/3/29.
 *
 */
public class WXBaseResult<T> extends WXResultSupport {
    private static final long serialVersionUID = 4999091548448313101L;
    protected T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    

    public static <U> WXBaseResult<U> buildFailResult(int errorCode ,String errorMsg, U value) {
    	WXBaseResult<U> baseResult = new WXBaseResult<U>();
        baseResult.setSuccess(false);
        baseResult.setErrorCode(errorCode);
        baseResult.setValue(value);
        baseResult.setResultMsg(errorMsg);

        return baseResult;
    }

    public static <U> WXBaseResult<U> buildSuccessResult(U value) {
    	WXBaseResult<U> baseResult = new WXBaseResult<U>();
        baseResult.setSuccess(true);
        baseResult.setValue(value);
        return baseResult;
    }

}
