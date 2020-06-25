package com.hjq.demo.view;

import android.view.View;

import com.hjq.demo.R;
import com.hjq.demo.aop.SingleClick;
import com.hjq.demo.common.MyApplication;
import com.hjq.demo.common.MyFragment;
import com.hjq.demo.model.User;
import com.hjq.demo.ui.activity.HomeActivity;

public class MeFragmentView extends MyFragment<HomeActivity> {

    public static MeFragmentView newInstance() {
        return new MeFragmentView();
    }

    @Override
    protected int getLayoutId() { return R.layout.fragment_me; }

    @Override
    protected void initView() {
        setOnClickListener(R.id.btn_test_login, R.id.btn_test_register, R.id.btn_test_personal);
    }

    @Override
    protected void initData() {

    }

    @SingleClick
    @Override
    public void onClick(View v) {
        boolean logged = false;
        if (((MyApplication) getActivity().getApplication()).getLoginController() != null)
            logged = ((MyApplication) getActivity().getApplication()).getLoginController().isLogged();
        switch (v.getId()) {
            case R.id.btn_test_login:
                if(logged)
                    toast("已检测到登录用户");
                else
                    startActivity(LoginView.class);
                break;
            case R.id.btn_test_register:
                if(logged)
                    toast("已检测到登录用户");
                else
                    startActivity(RegisterView.class);
                break;
            case R.id.btn_test_personal:
                if(!logged)
                    toast("未检测到登录用户");
                else
                    startActivity(PersonalDataView.class);
                break;
        }
    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }
}
