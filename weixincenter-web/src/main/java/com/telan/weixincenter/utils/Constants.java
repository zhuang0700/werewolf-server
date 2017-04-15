package com.telan.weixincenter.utils;

/**.
 * 
 * @author xusq
 */
public  class Constants {

	
	/**
	 *.
	 */
	private Constants(){
		
	}
	
    /**
     * 购物车redisKey
     */
    
	public static String REDIS_KEY = "order.cart";
	
	/**
	 * 操作失败
	 */
	public static final int OPERATION_FAILED = 0;
	
	/**
	 * 操作成功
	 */
	public static final int OPERATION_SUCCESSFUL = 1;

	/**
	 * 待审核
	 */
	public static final int TO_BE_APPROVED = 0;
	
	/**
	 * 审核通过
	 */
	public static final int APPROVED  = 1;
	
	/**
	 * 审核未通过
	 */
	public static final int UNAPPROVED = 2;

	
	/**
	 * 一页显示的条数.
	 */
	public static final int PAGE_NUM_TEN = 10;
	
	/*一页显示15条*/
	
	public static final int PAGE_NUM_FIFTY = 15;
	/*一页显示20条*/
	
	public static final int PAGE_NUM_TWENTY = 20;
	/**
	 * 失效时间30*60.
	 */
	public static final int OUT_TIME_1800 = 1800;
	/**
	 * 失效时间60*60.
	 */
	public static final int OUT_TIME_3600 = 3600;
	/**
	 * 永不过期.
	 */
	public static final int OUT_TIME_FOREVER = -1;
	
	/**
	 * 公用常量 数字0.
	 */
	public static final int PUBLIC_STATIC_NUM_0= 0;
	/**
	 * 公用常量 数字1.
	 */
	public static final int PUBLIC_STATIC_NUM_1= 1;
	/**
	 * 公用常量 数字10.
	 */
	public static final int PUBLIC_STATIC_NUM_10= 10;
	/**
	 * 公用常量 数字100.
	 */
	public static final int PUBLIC_STATIC_NUM_100= 100;
	/**
	 * 公用常量 数字100.
	 */
	public static final int PUBLIC_STATIC_NUM_1000= 1000;
	/**
	 * 公用常量 数字1024.
	 */
	public static final int PUBLIC_STATIC_NUM_1024= 1024;
	
	/**
	 * 公用常量 数字Long1.
	 */
	public static final Long PUBLIC_STATIC_LONG_1= 1l;
	
	/**
	 * 状态正常1
	 */
	public static final String SUCCESS = "1";
	/**
	 * 状态错误0
	 */
	public static final String ERROR = "0";

	/**
	 * 删除数据  修改status 为-1
	 */
	public static final Integer STATUS_DEL = -1;
	/**
	 * 正常数据  修改status 为1
	 */
	public static final Integer STATUS_SUCCESS = 1;
	
	
	/**
	 * 标识PC端
	 */
	public static final Integer SITE_PC = 2;
	
	/**
	 * 标识微信端
	 */
	public static final Integer SITE_WX = 3;
	
//    /**
//     * 图片服务器地址（开发）
//     */
//	public static String IMAGES_VIEW2 = SysServiceSingleton.getImagePath();
//	/**
//     * 图片服务器地址（测试）
//     */
//	public static String IMAGES_VIEW2 = "http://123.56.125.109/";
	/**
	 * 优惠卷页面显示条数.
	 */
	public static final Integer COUPON_PAGE_SIZE=10;
	/**
	 * 商品页面显示条数.
	 */
	public static final Integer ITEM_PAGE_SIZE=10;
	/**
	 * 商品收藏页面显示条数.
	 */
	public static final Integer COLLECT_PAGE_SIZE=10;
	/**
	 * 我的评价页面显示条数.
	 */
	public static final Integer REMARK_PAGE_SIZE=10;
	/**
	 * 我的订单页面显示条数.
	 */
	public static final Integer ORDER_PAGE_SIZE=10;
	/**
	 * 我的金币页面显示条数.
	 */
	public static final Integer GOLD_PAGE_SIZE=10;
	/**
	 * 我的积分页面显示条数.
	 */
	public static final Integer INTEGRATION_PAGE_SIZE=10;
	
	/**
	 * @author : zhangchao
	 * @date : 2015年8月17日 下午5:22:52
	 * @Description: 域名回调域名地址
	 */
//	//public static String OAUTH_VISIT = "http://www.jielunxiao.com/yihg-gf-weixin";
//	public static String OAUTH_VISIT = SysServiceSingleton.getBasePathUrl();//"http://www.jielunxiao.com/yihg-gf-weixin";
	
	/**
	 * @author : zhangchao
	 * @date : 2015年9月22日 下午4:36:12
	 * @Description: 域名授权获取用户openid    jsapi_ticket    jssdk相关参数  所需要公众号的管理员ID
	 */
//	//public static Integer OAUTH_VISIT_USERID = 25;
//	public static String OAUTH_VISIT_USERID = SysServiceSingleton.getOAuthVisitUserId();
	/**
	 * 博盛支付成功处理接口
	 */
	public static String BOSHENG_PAY_FACE="http://coin.mythicflora.com/portal/api/game_order/wx_paid";
	/**
     * 二维码rediskey前缀.
     */
    public static String QRCODE_REDIS_KEY="qrcode.";
    /**
     * 二维码rediskey前缀.
     */
    public static Integer QRCODE_OUT_TIME=518400;
    /**
     * 分享商品url.
     */
//    public static String ITEM_SHARE_URL=SysServiceSingleton.getBasePathUrl()+"/microshop/item/sharedetail?itemId=";
    /**
     * 分享二维码url.
     */
//    public static String QECODE_SHARE_URL=SysServiceSingleton.getBasePathUrl()+"/microshop/dimension/qrcode?";
}
