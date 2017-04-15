package com.telan.weixincenter.manager;

import com.alibaba.fastjson.JSONObject;
import com.telan.weixincenter.core.util.AesCbcUtil;
import com.telan.weixincenter.core.util.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weiwenliang on 17/3/31.
 * 根据小程序的登录code获取session_key等会话信息，并解密用户敏感数据
 */
public class WxSessionManager {
    private String wxspAppid = "wxca8170585f87089d";
    //小程序的 app secret (在微信小程序管理后台获取)
    private String wxspSecret = "6b7666b260043e49764a8543a15e8d54";

    public String getWxspAppid() {
        return wxspAppid;
    }

    public void setWxspAppid(String wxspAppid) {
        this.wxspAppid = wxspAppid;
    }

    public String getWxspSecret() {
        return wxspSecret;
    }

    public void setWxspSecret(String wxspSecret) {
        this.wxspSecret = wxspSecret;
    }

    public Map getSessionInfo(String encryptedData, String iv, String code) {

        Map map = new HashMap();
        //授权（必填）
        String grant_type = "authorization_code";


        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        //请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String sr = HttpUtils.doGet("https://api.weixin.qq.com/sns/jscode2session", params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr);
        //获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        //用户的唯一标识（openid）
        String openid = (String) json.get("openid");

        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        try {
            String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");

                JSONObject userInfoJSON = JSONObject.parseObject(result);
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                userInfo.put("unionId", userInfoJSON.get("unionId"));
                map.put("userInfo", userInfo);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status", 0);
        map.put("msg", "解密失败");
        return map;
    }
}
