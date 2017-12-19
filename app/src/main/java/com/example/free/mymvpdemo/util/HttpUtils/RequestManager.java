package com.example.free.mymvpdemo.util.HttpUtils;


import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理请求参数，避免每个界面的重写
 * Created by qiaoda on 2016/11/29.
 */

public class RequestManager {


    /**
     * 发送验证码接口
     */
    public static void getAuthCodeAndPhoneDouble(Context context, HttpResult result) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("ip", "63.223.108.42");
        HttpUtils.getInstance().postRequest(PathUrl.SINA_URL, params, context, result);
    }

}



