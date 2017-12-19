//package com.example.free.mymvpdemo.util.HttpUtils;
//
///**
// * 请求地址管理
// * Created by qiaoda on 2016/11/29.
// */
//
//public class PathManager {
//
//        public static String BASE_URL = "http://122.49.61.131:8086/";//测试环境
////    public static String BASE_URL = "http://122.49.61.131:8088/";//临时测试
//
//    //    public static String BASE_URL = "https://api.rencaiwa.com/";  //正式服务器
//    public static String BASE_URL_VERSION = "?apiversion=2.0.0";
//    //    public static String BASE_URL_VERSION = "";
//    public static String H5_BASE_URL_VERSION = "apiversion=2.0.0";
//    public static String CHANNEL_TYPE = null;//推广渠道id
//
//    /*---------------------------- 拼接路径 -----------------------------------*/
//    public static String IMAGE_URL = BASE_URL + "Home/ResumeImage/image/path/";//图片服务器地址
//
//    /*------------------------------   推广记录设备信息   -------------------*/
//    public static String ADD_DEVICE_INFO = BASE_URL + "Api/Promote/addDeviceInfo" + BASE_URL_VERSION;//上传设备信息
//
//    /*----------------------------   登陆注册     -----------------------------------*/
//    public static String SEND_AUTO_CODE = BASE_URL + "Api/Sms/sendAuthCode" + BASE_URL_VERSION;  //发送验证码
//    public static String SEND_SHARE_REGISTER_CODE = BASE_URL + "Api/Sms/sendCodeOfChangeMobile" + BASE_URL_VERSION;  //获取 验证码 并且 判断手机号码是不是已经存在
//    public static String AUTH_CODE_LOGIN = BASE_URL + "Api/Login/authCodeLogin" + BASE_URL_VERSION; //验证码登陆
//    public static String LOGIN = BASE_URL + "Api/Login/login" + BASE_URL_VERSION;   //密码登陆
//    public static String GET_LOGIN_DAYS_AND_SUM = BASE_URL + "Api/Login/signInReward" + BASE_URL_VERSION;//签到
//    public static String LOGIN_OUT = BASE_URL + "Api/Login/logout" + BASE_URL_VERSION;  //登出
//    public static String UPDATE_DEVICE_ID = BASE_URL + "Api/Login/updateLoginLogDeviceId" + BASE_URL_VERSION; //更新设备id
//
//
//    /*----------------------------   推荐流程   ------------------------------------*/
//    //职位采集
//    public static String VALIDATE_LOGIN_RESULT = BASE_URL + "Api/BindChannelAccount/validateLoginResult" + BASE_URL_VERSION;//上传登录后的网页，获取登录状态
//    public static String GET_ALL_JOB_LIST_URLS = BASE_URL + "Api/BindChannelAccount/getAllJobListUrls" + BASE_URL_VERSION;//获取职位列表页URL
//    public static String ADD_JOB_LIST_HTML = BASE_URL + "Api/BindChannelAccount/addJobListHtml" + BASE_URL_VERSION; //提交职位列表界面
//    //重新登录完成
//    public static String BIND_COMPLETE = BASE_URL + "Api/BindChannelAccount/complete" + BASE_URL_VERSION;//绑定完成
//    public static String CHECK_COPY_END = BASE_URL + "Api/BindChannelAccount/checkCopyEnd" + BASE_URL_VERSION;//绑定完成
//    //其他
//    public static String JOB_STATISTICAL_NEW = BASE_URL + "Api/Channel/getChannellist" + BASE_URL_VERSION;  //渠道列表
//    public static String CHANNEL_GET_CHANNEL_ACCOUNT_LIST = BASE_URL + "Api/Channel/getChannelAccountList" + BASE_URL_VERSION;  //渠道列表
//    public static String DELETE_CHANNEL = BASE_URL + "Api/Channel/deleteChannelId" + BASE_URL_VERSION;//删除渠道账号
//    public static String RECOMMEND_ONE = BASE_URL + "Api/ChannelJob/setJobInRecommend" + BASE_URL_VERSION;//设置为推荐职位
//    public static String UNINTERESTED_RESUME = BASE_URL + "Api/Resume/uninterestRresume" + BASE_URL_VERSION;//不感兴趣
//
//    public static String JOB_RESUME_LIST = BASE_URL + "Api/Resume/jobResumeLists" + BASE_URL_VERSION;  //推荐->新推荐的简历列表
//    public static String INVITE_BY_JOB_ID_LIST = BASE_URL + "Api/Invite/inviteByJobIdList" + BASE_URL_VERSION;//已邀请列表
//    public static String BEFORE_SEND_INVITE = BASE_URL + "Api/SendInvite/beforeSendInvite" + BASE_URL_VERSION;//发送邀请之前的判断
//    public static String SEND_INVITE = BASE_URL + "Api/SendInvite/sendInvite" + BASE_URL_VERSION;//发送邀请
//    public static String RECOMMEND_RESUME_BEFORE = BASE_URL + "Api/ChannelJob/recommendResume" + BASE_URL_VERSION;  //请求某职位简历列表之前的接口
//
//    /*-----------------------       蛙币   ----------------------------------*/
//    public static String GET_BALANCE_SUM_BY_USER_ID = BASE_URL + "Api/User/getBalanceSumByUserId" + BASE_URL_VERSION;  //获取账号余额(获取蛙币余额)
//    public static String GET_GOOD_LIST = BASE_URL + "Api/Goods/getGoodsList" + BASE_URL_VERSION;  //蛙币商品列表
//    public static String CREATE_ORDER = BASE_URL + "Api/order/createOrder" + BASE_URL_VERSION;   //订单信息
//    public static String ALI_PAY_RESULT = BASE_URL + "Api/order/alipayResult" + BASE_URL_VERSION;   //
//    public static String GET_BALANCE_LOG_BY_USER_ID = BASE_URL + "Api/User/getBalanceLogByUserId" + BASE_URL_VERSION;//获取蛙币支付结果验证详情
//    public static String GET_PRICE_CONFIGS = BASE_URL + "Api/UsersPriceConfig/getPriceConfigs" + BASE_URL_VERSION;// 获取赚取蛙币参数
//
//
//    /*----------------------------  我的   ------------------------------------*/
//    public static String MY_INFO = BASE_URL + "Api/User/myInfo" + BASE_URL_VERSION;  //我的信息
//    public static String CHANGE_INFO = BASE_URL + "Api/User/editUserInfo" + BASE_URL_VERSION;  //获取 修改个人信息
//    public static String SAVE_USER_INFO = BASE_URL + "Api/User/saveUserInfo" + BASE_URL_VERSION;  //提交 修改个人信息
//    public static String GET_PUSH_CONFIG = BASE_URL + "Api/user/getPushConfig" + BASE_URL_VERSION;  //获取 提醒设置
//    public static String GET_USER_SHARE_URL = BASE_URL + "Api/User/getUserShareUrl" + BASE_URL_VERSION; //获取 推荐给好友的信息
//    public static String GET_READ_INFO = BASE_URL + "Api/Invite/getReadInfo" + BASE_URL_VERSION; //获取 红点的状态
//    public static String UPDATE_SHARE_STATUS = BASE_URL + "Api/User/updateShareStatus" + BASE_URL_VERSION;  //上传 分享状态
//    public static String GET_USER_MESSAGES = BASE_URL + "Api/Messages/getUserMessages" + BASE_URL_VERSION;  //上传 联系人
//    public static String ADD_FORWARD_LOG = BASE_URL + "Api/ResumeForwardLog/addForwardLog" + BASE_URL_VERSION;  //转发 简历
//    public static String SHARE_INFO = BASE_URL + "Api/UserShare/shareInfo" + BASE_URL_VERSION;//邀请好友的
//
//    /*--------------------------    设置    --------------------------------------*/
//    public static String SET_PUSH_CONFIG = BASE_URL + "Api/User/setPushConfig" + BASE_URL_VERSION; //提醒设置
//    public static String SETTING_PASSWORD = BASE_URL + "Api/User/setPassword" + BASE_URL_VERSION;   //设置密码
//    public static String CHANGE_MOBILE = BASE_URL + "Api/User/changeMobileNo" + BASE_URL_VERSION;   //设置新手机号
//    public static String ADD_FEED_BACK = BASE_URL + "Api/Feedback/addFeedback" + BASE_URL_VERSION; //意见反馈
//    public static String PASSWORD_EXISTS = BASE_URL + "Api/User/passwordExists" + BASE_URL_VERSION;   //判断是否设置密码
//    public static String GET_VERSION_INFO = BASE_URL + "Api/Version/getLatestVersionInfo" + BASE_URL_VERSION; //版本更新
//    public static String CHECK_IS_TOP10_SEND = BASE_URL + "Api/Invite/checkIsTop10Send" + BASE_URL_VERSION; //简历详情的状态
//
//    /*--------------------------    邀请    --------------------------------------*/
//    public static String GET_INVITE_LIST = BASE_URL + "Api/Invite/getInviteList" + BASE_URL_VERSION;  //获取邀请列表
//    public static String GET_INVITE_DETAIL = BASE_URL + "Api/Invite/getInviteDetail" + BASE_URL_VERSION;  //获取沟通详情
//
//    /*-----------------------      搜索    ------------------------------------*/
//    public static String GET_FULL_TRADE_DICT = BASE_URL + "/Api/SearchResumes/getFullTradeDict" + BASE_URL_VERSION;//获取行业类别
//    public static String GET_FULL_DEGREE_DICT = BASE_URL + "Api/SearchResumes/getFullDegreeDict" + BASE_URL_VERSION;  //学历字典
//
//    public static String SEARCH_SEND_INVITE = BASE_URL + "Api/SendInvite/searchSendInvite" + BASE_URL_VERSION;//搜索发邀请
//
//    /*-------------------------   h5  ------------------------------------------*/
//    public static String WORK_DETAIL = BASE_URL + "Home/Html/workDetail.html";//职位详情界面
//    public static String RESUME_DETAIL = BASE_URL + "Home/Html/resumeDetail.html";//简历详情界面
//    public static String POND_RESUME_DETAIL = BASE_URL + "Home/Html/pondResumeDetail.html";//池塘简历详情界面
//    public static String JOIN_PREVIEW_RESUME = BASE_URL + "Home/Html/joinPreviewResume.html";//邀请预览界面
//    public static String COMPANY_INFO = BASE_URL + "Home/Html/companyInfo.html";//公司信息预览页
//    public static String USER_AGREEMENT = BASE_URL + "/Home/Html/userAgreement.html";//用户协议
//    public static String SHARE_TO_FRIEND_SHOW = BASE_URL + "/Home/Html/recommend.html";
//
//    /*---------------------------- shareSdk-imageUrl的图标地址 ------------------------*/
//    public static String SHARE_ICON_V201_URL = BASE_URL + "Api/images/shareIcon_v2.0.1.png";
//
//    /*------------------------------------ 池 塘 -------------------------------------*/
//    public static String POND_SEARCH = BASE_URL + "Api/Pond/search" + BASE_URL_VERSION;
//    public static String GET_BANNER_LIST = BASE_URL + "Api/IndexBanner/getList" + BASE_URL_VERSION;  //获取bannner图的列表
//
//    /*-------------------------------------   pop邮箱   ---------------------------------------*/
//    public static String GET_USER_EMAIL = BASE_URL + "Api/Email/getUserEmail" + BASE_URL_VERSION;//邮箱列表
//    public static String ADD_ACCOUNT = BASE_URL + "Api/Email/addAccount" + BASE_URL_VERSION;//邮箱列表
//    public static String UPDATE_ACCOUNT = BASE_URL + "Api/Email/updateUserAccount" + BASE_URL_VERSION;//邮箱列表
//    public static String DELETE_EMAIL2 = BASE_URL + "Api/Email/deleteEmail" + BASE_URL_VERSION;//删除绑定邮箱
//    public static String UPLOAD_EMAIL = BASE_URL + "Api/Email/uploadEmail" + BASE_URL_VERSION;//上传解析邮件详情
//    public static String GET_USER_EMAIL_LIST = BASE_URL + "Api/Email/getUserEmailList" + BASE_URL_VERSION;//获取邮箱列表
//    public static String ADD_SHARE_EMAIL = BASE_URL + "Api/Email/addShareEmail" + BASE_URL_VERSION;//分享简历邮件
//    public static String UPDATE_EMAIL_NUM = BASE_URL + "Api/Email/updateResumeEmailNum" + BASE_URL_VERSION;//上传简历数量
//    public static String GET_EMAIL_TYPE_LIST = BASE_URL + "Api/Email/getEmailTypeList" + BASE_URL_VERSION;  //获取邮箱类型列表
//
//
//    /*-------------------------------------   第三方的url   ---------------------------------------*/
//    public static String GET_IP_INFO = "https://ipip.yy.com/get_ip_info.php";  //获取ip的url
//
//    /*-------------------------------------   V2.0.0新增接口   ---------------------------------------*/
//    public static String GET_RECOMMEND_JOB_LIST = BASE_URL + "Api/ChannelJob/getDefaultRecommendJobList" + BASE_URL_VERSION;//推荐中的职位列表
//    public static String GET_CHANNEL_RECOMMEND_LIST = BASE_URL + "Api/ChannelJob/getCannelDefaultRecommendJobs" + BASE_URL_VERSION;//结束推荐的职位列表
//    public static String CHANNEL_RECOMMEND_POSITION = BASE_URL + "Api/ChannelJob/cannelDefaultRecomendJobs" + BASE_URL_VERSION;//结束推荐中职位
//    public static String CHANGE_TOP = BASE_URL + "Api/ChannelJob/changeTop" + BASE_URL_VERSION;//修改置顶状态
//    public static String SEARCH_JOB_LIST = BASE_URL + "Api/ChannelJob/searchJobList" + BASE_URL_VERSION;//搜索推荐中职位
//    public static String GET_JOB_BOX = BASE_URL + "Api/ChannelJob/getJobBox" + BASE_URL_VERSION;//获取职位收件箱的职位详情
//    public static String CHECK_JOB_INFO = BASE_URL + "Api/ChannelJob/checkJobInfo" + BASE_URL_VERSION; //检查职位信息是否完整
//    public static String GET_JOB_PUBLIC = BASE_URL + "Api/ChannelJob/getJob" + BASE_URL_VERSION; //职位详情 - 统一共用接口
//    public static String SAVE_JOB_DETAIL = BASE_URL + "Api/ChannelJob/saveJobDatail" + BASE_URL_VERSION; //保存 职位信息
//    public static String GET_CONTACT_INFORMATION = BASE_URL + "Api/Resume/getContactInformation" + BASE_URL_VERSION; //查看联系方式的判断
//    public static String GET_CONTACT_PHONE_NUM = BASE_URL + "Api/Resume/getContactPhoneNum" + BASE_URL_VERSION; //查看联系方式
//    public static String SEND_CONTACT_SMS = BASE_URL + "Api/SendInvite/sendContactSms" + BASE_URL_VERSION; //显示联系方式发送短信
//    public static String GET_JOB_CONDITION = BASE_URL + "Api/ChannelJob/getJobCondition" + BASE_URL_VERSION; //获取过滤条件信息
//    public static String SET_JOB_CONDITION = BASE_URL + "Api/ChannelJob/setJobCondition" + BASE_URL_VERSION; //修改过滤条件
//    public static String GET_RESUME_BOX = BASE_URL + "Api/ChannelJob/getResumeBox" + BASE_URL_VERSION;//简历夹列表
//    public static String RECOMMEND_RESUME_AFTER_BIND_CHANNEL = BASE_URL + "Api/BindChannelAccount/recommendResumeAfterBindChannel" + BASE_URL_VERSION;  //绑定完成，给前20个职位推荐简历
//    public static String GET_THE_LATEST_RESUME_NUM = BASE_URL + "Api/Resume/getTheLatestResumeNum" + BASE_URL_VERSION; //获取最新分享的简历数
//    public static String POND_JOB_LIST = BASE_URL + "Api/ChannelJob/pondJobList" + BASE_URL_VERSION; //池塘的选择职位列表
//    public static String GET_GET_CONTACT = BASE_URL + "Api/UsersPriceConfig/getGetConiact" + BASE_URL_VERSION;//获取看看联系方式消耗的U币
//
//    /*------------------------------------   v2.0.2渠道职位同步解析   ---------------------------------*/
//    public static String PARSE_JOB_LIST_HTML = BASE_URL + "Api/BindChannelAccount/parseJobListHtml" + BASE_URL_VERSION;//同步解析职位的
//    public static String SET_BIND_CHANNEL_STATE = BASE_URL + "Api/BindChannelAccount/setBindChannelstate" + BASE_URL_VERSION;//职位解析完成，修改职位和渠道状态
//
//    /*====================================== v2.1.0 新增接口   ================================================*/
//    public static String GET_CITY_LIST = BASE_URL + "Api/Dict/getCityLists" + BASE_URL_VERSION;//获取筛选的城市列表
//    public static String NEW_POND_SEARCH = BASE_URL + "Api/SearchResumes/search" + BASE_URL_VERSION;//搜索接口
//    public static String GET_RESUME_SEARCH_LOG = BASE_URL + "Api/SearchResumes/getResumeSearchLog" + BASE_URL_VERSION;//搜索历史的列表
//    public static String DEL_RESUME_SEARCH_LOGS = BASE_URL + "Api/SearchResumes/delResumeSearchLogs" + BASE_URL_VERSION;//删除历史记录
//    public static String GET_JOB_LIST = BASE_URL + "Api/ChannelJob/getJobList" + BASE_URL_VERSION;//获取搜索的职位列表，带预填
//
//}
