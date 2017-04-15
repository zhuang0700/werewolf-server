package com.telan.weixincenter.enums;

/**
 * Created by 海浩 on 2015/3/29.
 *
 */
public enum ErrorCode {
    USER_NOT_FOUND("用户不存在"),
    PARAM_ERROR("参数错误"),
    SYSTEM_ERROR("系统错误"),
    READ_DB_ERROR("读取数据库错误"),
    WRITE_DB_ERROR("写入数据库错误"),
    UPDATE_USER_EXT_DATA("更新用户扩展数据出错");
    private String desc;

    ErrorCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
