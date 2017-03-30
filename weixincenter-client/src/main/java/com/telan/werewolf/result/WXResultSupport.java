package com.telan.werewolf.result;



import java.io.Serializable;

/**
 * Created by 海浩 on 2015/3/29.
 *
 */
public class WXResultSupport implements Serializable {
    private static final long serialVersionUID = -2235152751651905167L;
    private boolean success = true;
    private String resultMsg;
    private int errorCode;

    public WXResultSupport() {

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public void setErrorCode(WXReturnCode wxReturnCode) {
        this.success = false;
        this.errorCode = wxReturnCode.getCode();
        this.resultMsg = wxReturnCode.getDesc();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
