/**
 * @Title: UUIDGenerator.java
 * @Copyright (C) 2015 北京婕洛芙电子商务有限公司
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年11月5日  吴青岭
 */
 

package com.telan.weixincenter.utils;

import java.util.UUID;

/**
 * @ClassName: UUIDGenerator
 * @Description: Description of this class
 * @author <a href="mailto:wuql@yimayholiday.com">吴青岭</a> 于 2015年11月5日 上午10:04:05
 */

public class UUIDGenerator {
    /**
     * 获得一个UUID
     * @return String UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获得指定数目的UUID
     * @param number 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUIDs(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }
}
