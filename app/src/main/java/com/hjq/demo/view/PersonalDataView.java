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
    @BindView(R.id.sb_person_data_address)
    SettingBar mAddressView;

    /** 省 */
    private String mProvince = "广东省";
    /** 市 */
    private String mCity = "广州市";
    /** 区 */
    private String mArea = "天河区";
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
        mProvince = user.getmProvince();
        mCity = user.getmCity();
        mArea = user.getmArea();
        String address = mProvince + mCity + mArea;
        mAddressView.setRightText(address);
        mIDView.setRightText(user.getId()+"");
        mNameView.setRightText(user.getUsrname());
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           /* case R.id.iv_person_data_avatar:
                if (mAvatarUrl != null && !"".equals(mAvatarUrl)) {
                    // 查看头像
                    ImageActivity.start(getActivity(), mAvatarUrl);
                } else {
                    // 选择头像
                    onClick(findViewById(R.id.fl_person_data_head));
                }
                break;
            case R.id.fl_person_data_head:
                PhotoActivity.start(getActivity(), new PhotoActivity.OnPhotoSelectListener() {

                    @Override
                    public void onSelected(List<String> data) {

                        if (true) {
                            mAvatarUrl = data.get(0);
                            GlideApp.with(getActivity())
                                    .load(mAvatarUrl)
                                    .into(mAvatarView);
                            return;
                        }

                        // 上传头像
                        EasyHttp.post(getActivity())
                                .api(new UpdateImageApi()
                                        .setImage(new File(data.get(0))))
                                .request(new HttpCallback<HttpData<String>>(PersonalDataView.this) {

                                    @Override
                                    public void onSucceed(HttpData<String> data) {
                                        mAvatarUrl = data.getData();
                                        GlideApp.with(getActivity())
                                                .load(mAvatarUrl)
                                                .into(mAvatarView);
                                    }
                                });
                    }

                    @Override
                    public void onCancel() {}
                });
                break;*/
            case R.id.sb_person_data_name:
                new InputDialog.Builder(this)
                        // 标题可以不用填写
                        .setTitle(getString(R.string.personal_data_name_hint))
                        .setContent(mNameView.getRightText())
                        //.setHint(getString(R.string.personal_data_name_hint))
                        //.setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        //.setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener((dialog, content) -> {
                            if (!mNameView.getRightText().equals(content)) {
                                mNameView.setRightText(content);
                            }
                        })
                        .show();
                break;
            case R.id.sb_person_data_address:
                new AddressDialog.Builder(this)
                        //.setTitle("选择地区")
                        // 设置默认省份
                        .setProvince(mProvince)
                        // 设置默认城市（必须要先设置默认省份）
                        .setCity(mCity)
                        // 不选择县级区域
                        //.setIgnoreArea()
                        .setListener((dialog, province, city, area) -> {
                            String address = province + city + area;
                            if (!mAddressView.getRightText().equals(address)) {
                                mProvince = province;
                                mCity = city;
                                mArea = area;
                                mAddressView.setRightText(address);
                            }
                        })
                        .show();
                break;
            /*case R.id.sb_person_data_phone:
                // 先判断有没有设置过手机号
                if (true) {
                    startActivity(PhoneVerifyActivity.class);
                } else {
                    startActivity(PhoneResetActivity.class);
                }
                break;*/
            case R.id.sb_setting_exit:
                loginController.setLogged(false);
                break;
            default:
                break;
        }
        user.setUsrname(mNameView.getRightText().toString());
        user.setmProvince(mProvince);
        user.setmCity(mCity);
        user.setmArea(mArea);

        if(loginController.isLogged() == false) {
            loginController.saveModel();
            user = null;
            toast("退出登录");
            startActivity(HomeActivity.class);
            finish();
        }
    }
}
