package com.example.free.mymvpdemo.ui;

import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.example.free.mymvpdemo.R;
import com.example.free.mymvpdemo.manager.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.MediaType;

import static java.security.AccessController.getContext;

public class TextWatcherActivity extends BaseActivity implements TextWatcher {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_idnubmer)
    EditText etIdnubmer;
    @BindView(R.id.et_bank)
    EditText etBank;
    @BindView(R.id.et_banktype)
    EditText etBanktype;
    @BindView(R.id.et_bankphone)
    EditText etBankphone;
    @BindView(R.id.tv_next)
    TextView tvNext;
    Unbinder unbinder;
    private String name;
    private String idCard;
    private String etBankPhone;
    private String bankCard;
    private String etBankType;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_text_watcher;
    }

    @Override
    protected void initView() {
        tvNext.setBackgroundResource(R.color._D8D8D8);
        tvNext.setEnabled(false);
        etBanktype.addTextChangedListener(this);
        etBank.addTextChangedListener(this);
        etBankphone.addTextChangedListener(this);
        etIdnubmer.addTextChangedListener(this);
        etName.addTextChangedListener(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        LogUtils.i("textWatcher  string:" + s + ", start:"+ start + ", before:" + before + ", count:"+ count);
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(idCard) || TextUtils.isEmpty(etBankPhone)
                || TextUtils.isEmpty(bankCard) || TextUtils.isEmpty(etBankType)) {
            tvNext.setBackgroundResource(R.color._D8D8D8);
            tvNext.setEnabled(false);
        } else {
            tvNext.setBackgroundResource(R.drawable.sha_login_button);
            tvNext.setEnabled(true);
        }
        int length = etBank.getText().toString().trim().length();
        if (length >= 6) {
            String trim = etBank.getText().toString().trim();
            char[] chars = trim.toCharArray();
            String string = "wodelixiang";
            LogUtils.e(string);
            etBanktype.setText(string);


        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
