package com.telan.weixincenter.domain.channel;

import java.io.Serializable;
/**
 * @author : zhangchao
 * @date : 2015年12月2日 下午7:38:37
 * @Description: 返回channel_id的list对象集合
 */
public class SubScribeRecordGroupDO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8656202164064364793L;

    private long channelId;


    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }
}