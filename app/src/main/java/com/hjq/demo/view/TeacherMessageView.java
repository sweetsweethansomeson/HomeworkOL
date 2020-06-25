package com.hjq.demo.view;

import com.hjq.demo.R;
import com.hjq.demo.common.MyApplication;
import com.hjq.demo.common.MyFragment;
import com.hjq.demo.controller.MessageController;
import com.hjq.demo.other.NestedListView;
import com.hjq.demo.ui.activity.HomeActivityForTeacher;
import com.hjq.demo.ui.adapter.MessageAdapterForTeacher;

import butterknife.BindView;

public class TeacherMessageView extends MyFragment<HomeActivityForTeacher> {

    @BindView(R.id.message_list)
    NestedListView mListView;

    MessageController messageController = null;

    public static TeacherMessageView newInstance() {
        return new TeacherMessageView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView() {

        load();
    }

    public void load(){
        if(((MyApplication)(getActivity().getApplication())).getLoginController() != null) {
            messageController = new MessageController(
                    ((MyApplication) (getActivity().getApplication())).getLoginController().getUser(), this);
            ((MyApplication) (getActivity().getApplication())).setMessageController(messageController);

            MessageAdapterForTeacher adapter = new MessageAdapterForTeacher(getActivity(), messageController.getMessageInfoOfTeacher(), messageController);
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



}
