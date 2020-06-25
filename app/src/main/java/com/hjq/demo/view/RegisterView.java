package com.hjq.demo.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.hjq.demo.R;
import com.hjq.demo.aop.SingleClick;
import com.hjq.demo.common.MyActivity;
import com.hjq.demo.controller.LoginController;
import com.hjq.demo.helper.InputTextHelper;
import com.hjq.demo.http.model.HttpData;
import com.hjq.demo.http.request.GetCodeApi;
import com.hjq.demo.http.request.RegisterApi;
import com.hjq.demo.http.response.RegisterBean;
import com.hjq.demo.other.IntentKey;
import com.hjq.demo.ui.activity.LoginActivity;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.hjq.widget.view.CountdownView;

import butterknife.BindView;

public class RegisterView extends MyActivity {
    @BindView(R.id.tv_register_title)
    TextView mTitleView;

    @BindView(R.id.et_register_usrname)
    EditText mUsrnameView;

    @BindView(R.id.et_register_password1)
    EditText mPasswordView1;
    @BindView(R.id.et_register_password2)
    EditText mPasswordView2;

    @BindView(R.id.btn_register_commit)
    Button mCommitView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        // 给这个 View 设置沉浸式，避免状态栏遮挡
        ImmersionBar.setTitleBar(this, mTitleView);

        InputTextHelper.with(this)
                .addView(mUsrnameView)
                .addView(mPasswordView1)
                .addView(mPasswordView2)
                .setMain(mCommitView)
                .setListener(helper -> mUsrnameView.getText().toString().length() >= 2 &&
                        mPasswordView1.getText().toString().length() >= 6 &&
                        mPasswordView1.getText().toString().equals(mPasswordView2.getText().toString()))
                .build();

        setOnClickListener(R.id.cv_register_countdown, R.id.btn_register_commit);
    }

    @Override
    protected void initData() {

    }

    @Override
    public ImmersionBar createStatusBarConfig() {
        // 不要把整个布局顶上去
        return super.createStatusBarConfig().keyboardEnable(true);
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register_commit:
                if (true) {
                    LoginView.start(getActivity(), mUsrnameView.getText().toString(), mPasswordView1.getText().toString());
                    setResult(RESULT_OK);
                    finish();
                    return;
                }
                /*// 提交注册
                EasyHttp.post(this)
                        .api(new RegisterApi()
                                .setPhone(mUsrnameView.getText().toString())
                                .setPassword(mPasswordView1.getText().toString()))
                        .request(new HttpCallback<HttpData<RegisterBean>>(this) {

                            @Override
                            public void onSucceed(HttpData<RegisterBean> data) {
                                LoginActivity.start(getActivity(), mPhoneView.getText().toString(), mPasswordView1.getText().toString());
                                setResult(RESULT_OK);
                                finish();
                            }
                        });*/
                break;
            default:
                break;
        }
    }

    @Override
    public boolean isSwipeEnable() {
        return false;
    }

}
