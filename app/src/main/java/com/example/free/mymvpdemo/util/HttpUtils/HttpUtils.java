package com.example.free.mymvpdemo.util.HttpUtils;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.free.mymvpdemo.bean.BaseBean;
import com.example.free.mymvpdemo.manager.CodeApplication;
import com.example.free.mymvpdemo.util.ActivityManager;
import com.example.free.mymvpdemo.util.DeviceInfo;
import com.example.free.mymvpdemo.util.ULog;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class HttpUtils {

    private static HttpUtils mInstance;
    private OkHttpClient okHttpClient;
    private Handler mDelivery;
    private Gson mGson;

    public static final int REQUEST_ERROR = -500;//网络请求失败
    public static final int GSON_ERROR = -501;//gson解析失败
    public static final int IO_ERROR = -502; //io异常
    public static final int NO_NEYWORK = -503;//没有网络

    //上传文件编码格式
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

    private HttpUtils() {
        File sdcache = CodeApplication.applicationContext.getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize))
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(url, cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
//                        List<Cookie> cookies = cookieStore.get(url);
//                        return cookies != null ? cookies : new ArrayList<Cookie>();
                        return new ArrayList<Cookie>();
                    }

                });
        okHttpClient = builder.build();
        mDelivery = new Handler(Looper.getMainLooper());
        mGson = new Gson();
    }


    /**
     * 单例模式获取网络工具类对象，加同步锁保证线程安全
     *
     * @return
     */
    public static HttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (HttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new HttpUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * post请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @param tag    请求tag
     * @param result 请求回调
     */
    public Call postRequest(String url, Map<String, String> params, Object tag, HttpResult result) {
//        setBaseParams(params);
        return postRequestBase(url, params, tag, result);
    }

    /**
     * 返回OkHttpClient对象
     *
     * @return
     */
    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    /**
     * 追加必填参数
     *
     * @param params
     */
    public static void setBaseParams(Map<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        params.put("sys_type", "1");
    }


    /**
     * 基本的get请求封装
     *
     * @param url    请求地址
     * @param params 请求参数，没有参数的时候为空
     * @param tag    请求标签，没有的时候为空
     * @param result 请求回调
     */
    private void getRequestBase(String url, Map<String, String> params, Object tag, HttpResult result) {
        if (params == null) {
            params = new HashMap<>();
        }
        ULog.i("http", "\n url:" + url + "\n params:" + params.toString());
        if (!DeviceInfo.isNetworkAvailable()) {

//            UToast.showToast("请检查您的网络");
            result.onError(NO_NEYWORK, null);
            return;
        }
        //为get请求拼接参数
        if (params != null) {
            Iterator<Map.Entry<String, String>> entries = params.entrySet().iterator();
            StringBuffer stringBuffer = new StringBuffer(url);
            stringBuffer.append("?");
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                stringBuffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            url = stringBuffer.toString();
        }
        Request.Builder builder = new Request.Builder();
        builder.url(url); //设置请求地址
        final Request request = builder.build();
        //发送请求
        toRequest(request, result, tag);
    }

    /**
     * get请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @param tag    请求tag
     * @param result 请求回调
     */
    public void getRequest(String url, Map<String, String> params, Object tag, HttpResult result) {
        setBaseParams(params);
        getRequestBase(url, params, tag, result);
    }

    /**
     * post的请求的封装
     *
     * @param url    请求地址
     * @param params 请求参数
     * @param tag    请求tag
     * @param result 请求回调
     */
    private Call postRequestBase(String url, Map<String, String> params, Object tag, HttpResult result) {
        ULog.i("http", "\n 请求:" +"\n" + url + "\n" + params.toString());
        if (!DeviceInfo.isNetworkAvailable()) {
//            new StatusDialog(SampleApplicationContext.application).showErrorDialog();
            result.onError(NO_NEYWORK, null);
            return null;
        }
        Request.Builder builder = new Request.Builder();
        builder.url(url); //设置请求地址
        //为post请求拼接参数
        if (params != null) {
            Iterator<Map.Entry<String, String>> entries = params.entrySet().iterator();
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                if (!TextUtils.isEmpty(entry.getValue())) {
                    bodyBuilder.add(entry.getKey(), entry.getValue());
                }
            }
            builder.post(bodyBuilder.build());
        }
        final Request request = builder.build();
        //发送请求
        return toRequest(request, result, tag);
    }


    /**
     * 发送请求
     *
     * @param request 请求体
     * @param result  请求回调
     */
    public Call toRequest(final Request request, final HttpResult result, Object tag) {
        Call mCall = okHttpClient.newCall(request);
        mCall.enqueue(new Callback() {
                          @Override
                          public void onFailure(Call call, final IOException e) {
                              ULog.e("http", "\n url:" + request.url().toString() + "; error:" + e.toString());
                              mDelivery.post(new Runnable() {
                                  @Override
                                  public void run() {
                                      result.onError(REQUEST_ERROR, "网络请求失败");
                                  }
                              });
                          }

                          @Override
                          public void onResponse(Call call, Response response) throws IOException {
                              final String string = response.body().string();
                              ULog.i("http", "\n 返回:" +"\n" + request.url().toString() + "\n" + string);
                              mDelivery.post(new Runnable() {
                                                 @Override
                                                 public void run() {
                                                     result.onResponseString(string);
                                                     try {
                                                         BaseBean bean = mGson.fromJson(string, BaseBean.class);
                                                         if (bean != null && bean.getError_code() != null) {
                                                             int error_code = Integer.valueOf(bean.getError_code());
                                                             switch (error_code) {
                                                                 case 0:
                                                                     try {
                                                                         if (result.mType == String.class || result.mType == null) {
                                                                             result.onResponse(string);
                                                                         } else {
                                                                             BaseBean entity = mGson.fromJson(string, result.mType);
                                                                             if (entity != null) {
                                                                                 result.onResponse(entity);
                                                                             } else {
                                                                                 result.onError(GSON_ERROR, "数据格式错误");
                                                                             }
                                                                         }
                                                                     } catch (com.google.gson.JsonParseException e) {
                                                                         ULog.e("json", e.getMessage());
                                                                         ULog.e("json", e.toString());
                                                                         result.onError(GSON_ERROR, "数据解析失败");
                                                                     }
                                                                     break;
                                                                 case 110:
                                                                     ToastUtils.showShort("账号已在其他地方登录");
                                                                     ActivityManager.getInstance().exitActivity();
                                                                     cancelAll();
                                                                     break;
                                                                 case 209:
                                                                     ToastUtils.showShort("账号已在其他地方登录");
                                                                     ActivityManager.getInstance().exitActivity();
                                                                     cancelAll();
                                                                 default:
                                                                     if (!TextUtils.isEmpty(bean.getMsg())) {
                                                                         result.onError(error_code, bean.getMsg());
                                                                     } else {
                                                                         result.onError(error_code, "请求失败");
                                                                     }
                                                                     break;
                                                             }
                                                         } else {
                                                             result.onError(GSON_ERROR, "数据格式错误");
                                                         }
                                                     } catch (com.google.gson.JsonParseException e) {
                                                         if (result.mType == String.class || result.mType == null) {
                                                             result.onResponse(string);
                                                         } else {
                                                             result.onError(GSON_ERROR, "数据格式错误");
                                                         }
                                                     }
                                                 }
                                             }

                              );
                          }
                      }

        );
        //设置标签
        if (tag != null)

        {
            OkHttpCallManager.getInstance().addCall(tag, mCall);
        }
        return mCall;
    }

    /**
     * 发送请求
     *
     * @param request 请求体
     * @param result  请求回调
     */

    public void toRequestWeb(final Request request, final HttpResult<String> result, Object tag) {
        Call mCall = okHttpClient.newCall(request);
        mCall.enqueue(new Callback() {
                          @Override
                          public void onFailure(Call call, final IOException e) {
                              ULog.e("http", "url:" + request.url().toString() + ";error:" + e.toString());
                              mDelivery.post(new Runnable() {
                                  @Override
                                  public void run() {
                                      result.onError(REQUEST_ERROR, "网络请求失败");
                                  }
                              });
                          }

                          @Override
                          public void onResponse(Call call, final Response response) throws IOException {
                              final String string = response.body().string();
                              ULog.i("http", "url:" + request.url().toString() + "  response:\n" + string);
                              mDelivery.post(new Runnable() {
                                                 @Override
                                                 public void run() {
                                                     List<String> list = response.headers("Set-Cookie");
                                                     ULog.i("http", list.toString());
                                                     if (list != null) {
//                                                         CookieSyncManager.createInstance(context);
                                                         CookieManager cookieManager = CookieManager.getInstance();
                                                         for (String s : list) {
                                                             cookieManager.setCookie(request.url().toString(), s);
                                                         }
                                                         CookieSyncManager.getInstance().sync();
                                                     }
//                                                     result.onResponseString(cookie);
                                                     result.onResponse(string);
                                                 }
                                             }
                              );
                          }
                      }

        );
        //设置标签
        if (tag != null) {
            OkHttpCallManager.getInstance().addCall(tag, mCall);
        }

    }

    public void hasHeaderPost(String url, Map<String, String> params, Map<String, String> header, Object tag, HttpResult<String> result) {
        if (params != null) {
            ULog.i("http", "\n url:  " + url + " \n params:  " + params.toString());
        }
        Request.Builder builder = new Request.Builder();
        builder.url(url); //设置请求地址
        //为post请求拼接参数
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        if (params != null) {
            Iterator<Map.Entry<String, String>> entries = params.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                if (!TextUtils.isEmpty(entry.getValue())) {
                    bodyBuilder.add(entry.getKey(), entry.getValue());
                }
            }
        }
        builder.post(bodyBuilder.build());


        if (header != null) {
            Iterator<Map.Entry<String, String>> entries = header.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                if (!TextUtils.isEmpty(entry.getValue())) {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }
            }
        }
        final Request request = builder.build();
        //发送请求
        toRequestWeb(request, result, tag);
    }


    /**
     * 上传文件
     *
     * @param filePath 文件路径
     * @param result   请求回调
     */
    private void postAsynFile(String url, String filePath, HttpResult result) {
        File file = new File(filePath);
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .build();
        toRequest(request, result, null);
    }


    /**
     * 下载文件
     *
     * @param url     请求地址
     * @param outPath 文件存放地址
     * @param name    文件名称
     */
    public void downAsynFile(String url, final String outPath, String name) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) {
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = null;
                try {
                    if (!TextUtils.isEmpty(outPath)) {
                        fileOutputStream = new FileOutputStream(new File(outPath));
                    } else {
                        fileOutputStream = new FileOutputStream(new File("/sdcard/wangshu.jpg"));
                    }
                    byte[] buffer = new byte[2048];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * 下载文件
     *
     * @param url     请求地址
     * @param outPath 文件存放地址
     * @param name    文件名称
     */
    public void downAsynFile(String url, final Map<String, String> header, final String outPath, final String name, final HttpResult result) {
        Request.Builder builder = new Request.Builder();
        builder.url(url); //设置请求地址
        if (header != null) {
            Iterator<Map.Entry<String, String>> entries = header.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        final Request request = builder.build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mDelivery.post(new Runnable() {
                    @Override
                    public void run() {
                        result.onError(REQUEST_ERROR, "网络请求失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) {
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = null;
                try {
                    File path = new File(outPath);
                    if (!path.exists()) {
                        Log.d("TestFile", "Create the path:" + outPath);
                        path.mkdir();
                    }
                    fileOutputStream = new FileOutputStream(new File(outPath + name));
                    byte[] buffer = new byte[2048];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.flush();

                    List<String> list = response.headers("Set-Cookie");
                    if (list != null) {
//                        CookieSyncManager.createInstance(context);
                        CookieManager cookieManager = CookieManager.getInstance();
                        for (String s : list) {
                            cookieManager.setCookie(request.url().toString(), s);
                        }
                        CookieSyncManager.getInstance().sync();
                    }

                    mDelivery.post(new Runnable() {
                        @Override
                        public void run() {
                            if (!TextUtils.isEmpty(response.header("Set-Cookie"))) {
                                result.onResponse(response.header("Set-Cookie"));
                            } else {
                                result.onError(REQUEST_ERROR, "请求失败");
                            }
                        }
                    });

                } catch (IOException e) {
                    mDelivery.post(new Runnable() {
                        @Override
                        public void run() {
                            result.onError(IO_ERROR, "网络请求失败");
                        }
                    });
                    e.printStackTrace();
                }
            }
        });
    }

    public void cancelRequest(Object tag) {
        List<Call> calls = OkHttpCallManager.getInstance().getCall(tag);
        if (calls != null && calls.size() > 0) {
            for (Call call : calls) {
                if (call != null && !call.isCanceled()) {
                    call.cancel();
                }
            }
            OkHttpCallManager.getInstance().removeCall(tag);
        }
    }

    public void cancelAll() {
        okHttpClient.dispatcher().cancelAll();
        OkHttpCallManager.getInstance().removiAll();
    }

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    /**
     * 表单提交参数和文件  需完善
     *
     * @param url
     * @param params
     * @param files
     */
    public void sendMultipart(String url, Map<String, String> params, List<FormImage> files, HttpResult result, Object tag) {
        if (!DeviceInfo.isNetworkAvailable()) {
//            UToast.showToast("请检查您的网络");
            result.onError(NO_NEYWORK, null);
            return;
        }
        okHttpClient = new OkHttpClient();
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        if (params != null && params.size() > 0) {
            Iterator<Map.Entry<String, String>> entries = params.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                if(!TextUtils.isEmpty( entry.getValue())){
                    bodyBuilder.addFormDataPart(entry.getKey(), entry.getValue());
                }
            }
        }
        if (files != null && files.size() > 0) {
            for (FormImage file : files) {
                bodyBuilder.addFormDataPart(file.getName(), file.getFileName(),
                        RequestBody.create(MEDIA_TYPE_PNG, new File(file.getFilepath())));
            }
        }
        MultipartBody requestBody = bodyBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        toRequest(request, result, tag);

    }


}
