package com.hjq.demo.view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.gyf.immersionbar.ImmersionBar;
import com.hjq.demo.R;
import com.hjq.demo.common.MyApplication;
import com.hjq.demo.common.MyFragment;
import com.hjq.demo.controller.InviteStuController;
import com.hjq.demo.controller.InviteStuController;
import com.hjq.demo.other.NestedListView;
import com.hjq.demo.ui.activity.HomeActivity;
import com.hjq.demo.ui.activity.HomeActivityForTeacher;
import com.hjq.demo.ui.adapter.CourseAdapter;
import com.hjq.demo.ui.adapter.CourseAdapterForTeacher;
import com.hjq.demo.widget.XCollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;

public class InviteStuView extends MyFragment<HomeActivityForTeacher>
        implements XCollapsingToolbarLayout.OnScrimsListener {

    @BindView(R.id.ctl_test_bar)
    XCollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.t_test_title)
    Toolbar mToolbar;

    @BindView(R.id.scroll_view)
    NestedScrollView nestedScrollView;
    @BindView(R.id.tv_test_label)
    TextView mLabelView;
    @BindView(R.id.tv_test_hint)
    TextView mHintView;
    @BindView(R.id.tv_test_search)
    ImageView mSearchView;

    @BindView(R.id.course_list)
    NestedListView mListView;

    @BindView(R.id.linear)
    LinearLayoutCompat linearLayoutCompat;

    @BindView(R.id.iv_add_course)
    ImageView mImageView;

    InviteStuController inviteStuController;

    public static InviteStuView newInstance() {
        return new InviteStuView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_course;
    }

    @Override
    protected void initView() {
        // 给这个 ToolBar 设置顶部内边距，才能和 TitleBar 进行对齐
        ImmersionBar.setTitleBar(getAttachActivity(), mToolbar);
        linearLayoutCompat.getBackground().setAlpha(100);
        //设置渐变监听
        mCollapsingToolbarLayout.setOnScrimsListener(this);
        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((MyApplication)(getActivity().getApplication())).getLoginController() == null){
                    toast("请先登录");
                    return;
                }

                if(mHintView.getText().toString().equals(""))
                    return;
                if(inviteStuController == null)
                    inviteStuController = new InviteStuController(
                            ((MyApplication) (getActivity().getApplication())).getLoginController().getUser());
                int courseId = Integer.parseInt(mHintView.getText().toString());
                ArrayList<Map<String,Object>> list = inviteStuController.getCourseInfoById(courseId);
                if(list == null){
                    toast("课程不存在，请查验后重新输入");
                    return;
                }

                CourseAdapterForTeacher adapter = new CourseAdapterForTeacher(getActivity(), list, inviteStuController);
                mListView.setAdapter(adapter);

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    }
                });

            }
        });

        mImageView.setVisibility(View.VISIBLE);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateCourseView.start(getActivity());
            }
        });

        load();
    }

    private void load(){
        if(((MyApplication)(getActivity().getApplication())).getLoginController() != null) {
            inviteStuController = new InviteStuController(
                    ((MyApplication) (getActivity().getApplication())).getLoginController().getUser());
            ((MyApplication) (getActivity().getApplication())).setInviteStuController(inviteStuController);

            CourseAdapterForTeacher adapter = new CourseAdapterForTeacher(getActivity(), inviteStuController.getCourseInfoOfUser(), inviteStuController);
            mListView.setAdapter(adapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        load();
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

    @Override
    public boolean statusBarDarkFont() {
        return mCollapsingToolbarLayout.isScrimsShown();
    }

    /**
     * CollapsingToolbarLayout 渐变回调
     *
     * {@link XCollapsingToolbarLayout.OnScrimsListener}
     */
    @Override
    public void onScrimsStateChange(XCollapsingToolbarLayout layout, boolean shown) {
        if (shown) {
            mLabelView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.black));
            mHintView.setBackgroundResource(R.drawable.bg_home_search_bar_gray);
            mHintView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.black60));
            mSearchView.setImageResource(R.drawable.ic_search_black);
            getStatusBarConfig().statusBarDarkFont(true).init();
        } else {
            mLabelView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.white));
            mHintView.setBackgroundResource(R.drawable.bg_home_search_bar_transparent);
            mHintView.setTextColor(ContextCompat.getColor(getAttachActivity(), R.color.white60));
            mSearchView.setImageResource(R.drawable.ic_search_white);
            getStatusBarConfig().statusBarDarkFont(false).init();
        }
    }
}
