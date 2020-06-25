package com.hjq.demo.view;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.hjq.demo.R;
import com.hjq.demo.aop.SingleClick;
import com.hjq.demo.common.MyActivity;
import com.hjq.demo.common.MyApplication;
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

    @BindView(R.id.et_register_realname)
    EditText mRealnameView;

    @BindView(R.id.et_register_password1)
    EditText mPasswordView1;
    @BindView(R.id.et_register_password2)
    EditText mPasswordView2;

    @BindView(R.id.btn_register_commit)
    Button mCommitView;

    @BindView(R.id.cb_user_type)
    CheckBox mCheckBox;

    @BindView(R.id.et_register_id)
    EditText mUsrStringID;

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
                .addView(mRealnameView)
                .addView(mPasswordView1)
                .addView(mPasswordView2)
                .addView(mUsrStringID)
                .setMain(mCommitView)
                .setListener(helper -> mUsrnameView.getText().toString().length() >= 2 &&
                        mRealnameView.getText().toString().length() >= 2 &&
                        mPasswordView1.getText().toString().length() >= 6 &&
                        mPasswordView1.getText().toString().equals(mPasswordView2.getText().toString()) &&
                        mUsrStringID.getText().toString().length() == 8)
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
                String usrname = mUsrnameView.getText().toString();
                String realName = mRealnameView.getText().toString();
                String password = mPasswordView1.getText().toString();
                int usrtype = mCheckBox.isChecked() ? 1 : 0;
                String usrStrId = mUsrStringID.getText().toString();

                //获取loginController，如果不存在，创建
                LoginController loginController = ((MyApplication)getApplication()).getLoginController();
                if(loginController == null) {
                    loginController = new LoginController();
                    ((MyApplication) getApplication()).setLoginController(loginController);
                }

                // 提交注册，如果成功，跳转到登录界面
                int res = loginController.createUser(usrname, realName, password, usrtype, usrStrId);
                if(res == LoginController.REGISTER_ERROR_ACCOUNT_ALREADY_EXISTS){
                    toast("用户名已存在，请更换");
                    return;
                }
                if(res == LoginController.REGISTER_SUCCEED)
                    toast("注册成功");

                LoginView.start(getActivity(), usrname, password, usrtype);
                setResult(RESULT_OK);

                finish();
                return;
            default:
                break;
        }
    }

    @Override
    public boolean isSwipeEnable() {
        return false;
    }

}
