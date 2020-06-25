package com.hjq.demo.view;

import android.view.View;
import android.widget.ImageView;

import com.hjq.demo.R;
import com.hjq.demo.aop.SingleClick;
import com.hjq.demo.common.MyActivity;
import com.hjq.demo.common.MyApplication;
import com.hjq.demo.controller.LoginController;
import com.hjq.demo.http.glide.GlideApp;
import com.hjq.demo.http.model.HttpData;
import com.hjq.demo.http.request.UpdateImageApi;
import com.hjq.demo.model.User;
import com.hjq.demo.ui.activity.HomeActivity;
import com.hjq.demo.ui.activity.ImageActivity;
import com.hjq.demo.ui.activity.PersonalDataActivity;
import com.hjq.demo.ui.activity.PhoneResetActivity;
import com.hjq.demo.ui.activity.PhoneVerifyActivity;
import com.hjq.demo.ui.activity.PhotoActivity;
import com.hjq.demo.ui.dialog.AddressDialog;
import com.hjq.demo.ui.dialog.InputDialog;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.hjq.widget.layout.SettingBar;

import java.io.File;
import java.util.List;

import butterknife.BindView;

public class PersonalDataView extends MyActivity {
    @BindView(R.id.iv_person_data_avatar)
    ImageView mAvatarView;
    @BindView(R.id.sb_person_data_id)
    SettingBar mIDView;
    @BindView(R.id.sb_person_data_name)
    SettingBar mNameView;
    @BindView(R.id.sb_person_data_realname)
    SettingBar mRealNameView;

    /** 省 *//*
    private String mProvince = "广东省";
    *//** 市 *//*
    private String mCity = "广州市";
    *//** 区 *//*
    private String mArea = "天河区";*/
    /** 当前用户 */
    private User user;

    private LoginController loginController;
    /** 头像地址 *//*
    private String mAvatarUrl;*/

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_data;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.sb_person_data_name, R.id.sb_person_data_address, R.id.sb_setting_exit);
    }

    @Override
    protected void initData() {
        /*GlideApp.with(getActivity())
                .load(R.drawable.ic_head_placeholder)
                .placeholder(R.drawable.ic_head_placeholder)
                .error(R.drawable.ic_head_placeholder)
                .circleCrop()
                .into(mAvatarView);*/

        loginController = ((MyApplication)getApplication()).getLoginController();
        user = loginController.getUser();
        /*mProvince = user.getmProvince();
        mCity = user.getmCity();
        mArea = user.getmArea();
        String address = mProvince + mCity + mArea;
        mAddressView.setRightText(address);*/
        mIDView.setRightText(user.getStrID());
        mRealNameView.setRightText(user.getUsrname());
        if(user.getUsertype() == User.STUDENT)
            mIDView.setLeftText("学生学号");
        else
            mIDView.setLeftText("教师工号");
        mNameView.setRightText(user.getAccount());
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sb_person_data_name:
                new InputDialog.Builder(this)
                        .setTitle(getString(R.string.personal_data_name_hint))
                        .setContent(mNameView.getRightText())
                        .setListener((dialog, content) -> {
                            if (!mNameView.getRightText().equals(content)) {
                                mNameView.setRightText(content);
                            }
                        })
                        .show();
                break;
            case R.id.sb_setting_exit:
                loginController.setLogged(false);
                break;
            default:
                break;
        }
        user.setAccount(mNameView.getRightText().toString());

        if(loginController.isLogged() == false) {
            loginController.saveModel();
            user = null;
            loginController = null;
            toast("退出登录");
            startActivity(HomeActivity.class);
            finish();
        }
    }
}
