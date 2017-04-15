//package com.yimayhd.weixincenter.domain.channel;
//
//import java.util.Date;
//
//public class WxChannelFansDO implements java.io.Serializable{
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	private long fansId;
//
//    private long channelId;
//
//    private Integer subscribe;
//
//    private String openid;
//
//    private String nickname;
//
//    private Integer sex;
//
//    private String language;
//
//    private String city;
//
//    private String province;
//
//    private String country;
//
//    private String headimgurl;
//
//    private String subscribeTime;
//
//    private String remark;
//
//    private Date inserttime;
//
//    private long userId;
//
//    private long groupid;
//
//    private int currentPage;// 当前页  
//    private int pageSize;// 每页显示条数  
//    private int totalPage;// 总页数  
//    private int totalRecord;// 总记录数  
//    private int fromNum;//从第几页开始
//    private int isNext;//判断是否有下一页
//    private String keywords;//搜索内容
//    private int tagId;
//    private int timeFlag;//判断查询时间段的标识   
//    
//    
//    public int getCurrentPage() {
//		return currentPage;
//	}
//
//	public void setCurrentPage(int currentPage) {
//		this.currentPage = currentPage;
//	}
//
//	public int getPageSize() {
//		return pageSize;
//	}
//
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}
//
//	public int getTotalPage() {
//		return totalPage;
//	}
//
//	public void setTotalPage(int totalPage) {
//		this.totalPage = totalPage;
//	}
//
//	public int getTotalRecord() {
//		return totalRecord;
//	}
//
//	public void setTotalRecord(int totalRecord) {
//		this.totalRecord = totalRecord;
//	}
//
//	public int getFromNum() {
//		return fromNum;
//	}
//
//	public void setFromNum(int fromNum) {
//		this.fromNum = fromNum;
//	}
//
//	public int getIsNext() {
//		return isNext;
//	}
//
//	public void setIsNext(int isNext) {
//		this.isNext = isNext;
//	}
//
//	public String getKeywords() {
//		return keywords;
//	}
//
//	public void setKeywords(String keywords) {
//		this.keywords = keywords;
//	}
//
//	public int getTagId() {
//		return tagId;
//	}
//
//	public void setTagId(int tagId) {
//		this.tagId = tagId;
//	}
//
//	public int getTimeFlag() {
//		return timeFlag;
//	}
//
//	public void setTimeFlag(int timeFlag) {
//		this.timeFlag = timeFlag;
//	}
//
//	public long getFansId() {
//        return fansId;
//    }
//
//    public void setFansId(long fansId) {
//        this.fansId = fansId;
//    }
//
//    public long getChannelId() {
//        return channelId;
//    }
//
//    public void setChannelId(long channelId) {
//        this.channelId = channelId;
//    }
//
//    public Integer getSubscribe() {
//        return subscribe;
//    }
//
//    public void setSubscribe(Integer subscribe) {
//        this.subscribe = subscribe;
//    }
//
//    public String getOpenid() {
//        return openid;
//    }
//
//    public void setOpenid(String openid) {
//        this.openid = openid == null ? null : openid.trim();
//    }
//
//    public String getNickname() {
//        return nickname;
//    }
//
//    public void setNickname(String nickname) {
//        this.nickname = nickname == null ? null : nickname.trim();
//    }
//
//    public Integer getSex() {
//        return sex;
//    }
//
//    public void setSex(Integer sex) {
//        this.sex = sex;
//    }
//
//    public String getLanguage() {
//        return language;
//    }
//
//    public void setLanguage(String language) {
//        this.language = language == null ? null : language.trim();
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city == null ? null : city.trim();
//    }
//
//    public String getProvince() {
//        return province;
//    }
//
//    public void setProvince(String province) {
//        this.province = province == null ? null : province.trim();
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country == null ? null : country.trim();
//    }
//
//    public String getHeadimgurl() {
//        return headimgurl;
//    }
//
//    public void setHeadimgurl(String headimgurl) {
//        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
//    }
//
//    public String getSubscribeTime() {
//        return subscribeTime;
//    }
//
//    public void setSubscribeTime(String subscribeTime) {
//        this.subscribeTime = subscribeTime == null ? null : subscribeTime.trim();
//    }
//
//    public String getRemark() {
//        return remark;
//    }
//
//    public void setRemark(String remark) {
//        this.remark = remark == null ? null : remark.trim();
//    }
//
//    public Date getInserttime() {
//        return inserttime;
//    }
//
//    public void setInserttime(Date inserttime) {
//        this.inserttime = inserttime;
//    }
//
//    public long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(long userId) {
//        this.userId = userId;
//    }
//
//    public long getGroupid() {
//        return groupid;
//    }
//
//    public void setGroupid(long groupid) {
//        this.groupid = groupid;
//    }
//}