package com.telan.weixincenter.domain.menu;

import java.io.Serializable;

public class ConcernDO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	/**
	 * @author :张超
	 * @date :2015年7月16日 上午9:59:03
	 * @Description:关注者
	 * 微信服务器会返回json格式的数据：{
       "subscribe": 1, 
       "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M",     
       "nickname": "Band", 
       "sex": 1, 
       "language": "zh_CN", 
       "city": "广州", 
       "province": "广东", 
       "country": "中国", 
       "headimgurl":    "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0", 
       "subscribe_time": 1382694957
       }
	 */
	private int concernid;
	//subscribe:用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
    private int subscribe;
    //用户的标识，对当前公众号唯一
    private String openid;
    //用户的昵称
    private String nickname;
    //用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
    private String sex;
    
    //用户的生日
    private String birthdays;
    
    //用户的公司名称
    private String companyName;
    
    
    //用户的语言，简体中文为zh_CN
    private String language;
    //
    private String city;
    private String province;
    private String country;
    //用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
    private String headimgurl;
    //用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
    private String subscribe_time;
    private int status;
    private int integral;
    
    private String level;
    private String application_time;
    private String use_time;
    private String phone_num;
    private String vip_num;
    private String vip_name;
    private int userId;
    
    
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userid the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the subscribe
	 */
	public int getSubscribe() {
		return subscribe;
	}
	/**
	 * @param subscribe the subscribe to set
	 */
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}
	/**
	 * @return the openid
	 */
	public String getOpenid() {
		return openid;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @param openid the openid to set
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getBirthdays() {
		return birthdays;
	}
	public void setBirthdays(String birthdays) {
		this.birthdays = birthdays;
	}
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the headimgurl
	 */
	public String getHeadimgurl() {
		return headimgurl;
	}
	/**
	 * @param headimgurl the headimgurl to set
	 */
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	/**
	 * @return the subscribe_time
	 */
	public String getSubscribe_time() {
		return subscribe_time;
	}
	/**
	 * @param subscribe_time the subscribe_time to set
	 */
	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	/**
	 * @return the concernid
	 */
	public int getConcernid() {
		return concernid;
	}
	
	/**
	 * @param concernid the concernid to set
	 */
	public void setConcernid(int concernid) {
		this.concernid = concernid;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the integral
	 */
	public int getIntegral() {
		return integral;
	}
	/**
	 * @param integral the integral to set
	 */
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	/**
	 * @return the application_time
	 */
	public String getApplication_time() {
		return application_time;
	}
	/**
	 * @param application_time the application_time to set
	 */
	public void setApplication_time(String application_time) {
		this.application_time = application_time;
	}
	/**
	 * @return the use_time
	 */
	public String getUse_time() {
		return use_time;
	}
	/**
	 * @param use_time the use_time to set
	 */
	public void setUse_time(String use_time) {
		this.use_time = use_time;
	}
	/**
	 * @return the phone_num
	 */
	public String getPhone_num() {
		return phone_num;
	}
	/**
	 * @param phone_num the phone_num to set
	 */
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	/**
	 * @return the vip_num
	 */
	public String getVip_num() {
		return vip_num;
	}
	/**
	 * @param vip_num the vip_num to set
	 */
	public void setVip_num(String vip_num) {
		this.vip_num = vip_num;
	}
	/**
	 * @return the vip_name
	 */
	public String getVip_name() {
		return vip_name;
	}
	/**
	 * @param vip_name the vip_name to set
	 */
	public void setVip_name(String vip_name) {
		this.vip_name = vip_name;
	}
}
