package com.hjq.demo.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.hjq.base.BaseDialog;
import com.hjq.demo.R;
import com.hjq.demo.aop.DebugLog;
import com.hjq.demo.aop.SingleClick;
import com.hjq.demo.common.MyActivity;
import com.hjq.demo.common.MyApplication;
import com.hjq.demo.controller.CreateCourseController;
import com.hjq.demo.helper.InputTextHelper;
import com.hjq.demo.model.User;
import com.hjq.demo.other.IntentKey;
import com.hjq.demo.ui.activity.HomeActivity;
import com.hjq.demo.ui.activity.HomeActivityForTeacher;
import com.hjq.demo.ui.dialog.SelectDialog;
import com.hjq.widget.layout.SettingBar;

import java.util.HashMap;

import butterknife.BindView;

public class CreateCourseView extends MyActivity {

    @BindView(R.id.tv_addcourse_title)
    TextView mTitleView;

    @BindView(R.id.et_addcourse_coursename)
    EditText mEditText;

    @BindView(R.id.sb_addcourse_grade)
    SettingBar mSettingBar;

    @BindView(R.id.btn_addcourse_commit)
    Button mButton;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_course;
    }

    @DebugLog
    public static void start(Context context) {
        Intent intent = new Intent(context, CreateCourseView.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        // 给这个 View 设置沉浸式，避免状态栏遮挡
        ImmersionBar.setTitleBar(this, mTitleView);

       /* InputTextHelper.with(this)
                .addView(mEditText)
                .setListener(helper -> mEditText.getText().toString().length() >= 2)
                .build();*/

        setOnClickListener(R.id.btn_addcourse_commit, R.id.sb_addcourse_grade);
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
            case R.id.btn_addcourse_commit:
                User teacher = ((MyApplication)(getActivity().getApplication())).getLoginController().getUser();
                int grade = mSettingBar.getRightText().charAt(0) - '0';
                CreateCourseController createCourseController = new CreateCourseController(
                        teacher, mEditText.getText().toString(), grade);
                ((MyApplication)(getActivity().getApplication())).setCreateCourseController(createCourseController);
                startActivity(HomeActivityForTeacher.class);
                break;

            case R.id.sb_addcourse_grade:
                new SelectDialog.Builder(this)
                        .setTitle("请选择课程面向年级")
                        .setList("17级", "18级", "19级", "20级")
                        // 设置单选模式
                        .setSingleSelect()
                        // 设置默认选中
                        .setSelect(0)
                        .setListener(new SelectDialog.OnListener<String>() {

                            @Override
                            public void onSelected(BaseDialog dialog, HashMap<Integer, String> data) {
                                //toast("确定了" + data.toString());
                                int c = data.toString().charAt(1) - '0' + 17;
                                mSettingBar.setRightText(c + "级");
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) { }
                        })
                        .show();
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
