package com.nuoman.westernele.common;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nuoman.westernele.api.NuoManAPI;
import com.nuoman.westernele.common.utils.AppConfig;
import com.nuoman.westernele.common.utils.AppTools;
import com.nuoman.westernele.common.utils.Utils;
import com.nuoman.westernelectric.BuildConfig;
import com.squareup.okhttp.OkHttpClient;

import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * AUTHOR: Alex
 * DATE: 21/10/2015 19:09
 */
public abstract class BasePresenter {

    public static final int REQUEST_SUCCESS = 1;//请求成功
    public static final int REQUEST_FAILURE = 0;//请求失败
    public boolean isShowProgressDialog = true;
    public CustomProgressDialog progressDialog;
    private Context context;


    public BasePresenter() {
        if (BaseActivity.activityList.size() > 0) {
            context = BaseActivity.activityList.get(0);
            progressDialog = new CustomProgressDialog(context);
        }
    }


    public static Gson gson = new GsonBuilder().serializeNulls()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    /**
     * String Convert
     */
    public Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(NuoManAPI.URL)
            .client(client())
            .addConverterFactory(new ToStringConverterFactory())
            .build();

    public NuoManAPI service = retrofit.create(NuoManAPI.class);


    public OkHttpClient client() {

        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new LoggingInterceptor());

        return client;
    }


    /**
     * 接口调用
     *
     * @param methodName   方法名
     * @param parameterMap 参数
     * @param typeToken    返回值类型
     */
    public void commonApi(boolean isPost, final String part, final String methodName, final Map<String, String> parameterMap, final TypeToken<?> typeToken) {
        isShowFlag = false;
        if (Utils.checkNetworkConnection()) {
            delayDisplayProgress();
            Call<String> call;
            if (isPost) {
                call = service.serviceAPI(part, methodName, parameterMap);
            } else {
                call = service.serviceGetAPI(part, methodName, parameterMap);
            }
            if (BuildConfig.DEBUG) {
                AppTools.ergodicParameters(parameterMap);
            }
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Response<String> response, Retrofit retrofit) {
                    dialogDismiss();
                    isShowFlag = true;
                    Object object = null;
                    if (ParseResult.instance().requestServerResult(response.body())) {
                        if (typeToken != null) {
                            object = ParseResult.instance().requestServer(methodName, response.body(), typeToken);
                        }

                        BasePresenter.this.onResponse(methodName, object, REQUEST_SUCCESS,parameterMap);
                    } else {
                        BasePresenter.this.onResponse(methodName, object, REQUEST_FAILURE,parameterMap);
                        BasePresenter.this.onFailure(methodName);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    dialogDismiss();
                    BasePresenter.this.onResponse(methodName, null, REQUEST_FAILURE, parameterMap);
                    isShowFlag = true;
                }
            });

        } else {
            Toast.makeText(AppConfig.getContext(), "网络不给力，请稍后重试！", Toast.LENGTH_SHORT).show();
            BasePresenter.this.onFailure(methodName);
        }

    }

    /**
     * 只有公共参数的方法
     *
     * @param methodName
     * @param typeToken
     */
    public void commonApi(boolean isPost, final String part, final String methodName, final TypeToken<?> typeToken) {

        commonApi(isPost, part, methodName, null, typeToken);

    }


    protected void dialogShow() {
        if (isShowProgressDialog && progressDialog != null && !progressDialog.isShowing()) {
            try {
                progressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void dialogDismiss() {
        if (progressDialog != null && progressDialog.isShowing()) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 无返回值类型
     *
     * @param methodName
     * @param parameterMap
     */
    public void commonApi(boolean isPost, final String part, final String methodName, Map<String, String> parameterMap) {

        commonApi(isPost, part, methodName, parameterMap, null);
    }


    /**
     * 接口返回数据
     *
     * @param methodName 方法名
     * @param object     返回数据对象
     * @param status     是否成功标识
     */

    public abstract void onResponse(String methodName, Object object, int status, Map<String, String> parameterMap);

    public void onFailure(String methodName) {
    }

    private Handler handler = new Handler();
    private boolean isShowFlag; //调用接口成功后dismiss:true /


    /**
     * 延迟显示进度条
     */
    private void delayDisplayProgress() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isShowFlag) {
                    dialogShow();
                }
            }
        }, 100);
    }

}



