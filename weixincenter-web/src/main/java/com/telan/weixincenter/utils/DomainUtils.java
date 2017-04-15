package com.telan.weixincenter.utils;

import com.alibaba.dubbo.common.utils.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xinzhanguo on 6/18/16.
 */

public class DomainUtils {

    public static String getTopDomainWithoutSubdomain(String url)throws MalformedURLException {

        String host = new URL(url).getHost().toLowerCase();// 此处获取值转换为小写
        Pattern pattern = Pattern.compile("[^\\.]+(\\.com\\.cn|\\.net\\.cn|\\.org\\.cn|\\.gov\\.cn|\\.com|\\.net|\\.cn|\\.org|\\.cc|\\.me|\\.tel|\\.mobi|\\.asia|\\.biz|\\.info|\\.name|\\.tv|\\.hk|\\.公司|\\.中国|\\.网络)");
        Matcher matcher = pattern.matcher(host);
        while (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
    public static Boolean isEqualTopDomain(String fromUrl, String toUrl) {
        try {
            do {
                if(StringUtils.isBlank(fromUrl)){
                    throw new Exception("fromUrl is blank");
                }

                if(StringUtils.isBlank(toUrl)){
                    throw new Exception("toUrl is blank");
                }
                fromUrl = URLDecoder.decode(fromUrl, "UTF-8");

                String fromDomain = "";
                String toDomain = "";
                try {
                    fromDomain = DomainUtils.getTopDomainWithoutSubdomain(fromUrl);
                    toDomain = DomainUtils.getTopDomainWithoutSubdomain(toUrl);
                    if(fromDomain==toDomain) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception ex) {
                    return false;
                }
            } while (false);
        } catch (Exception e) {
            return false;
        }
    }

}
