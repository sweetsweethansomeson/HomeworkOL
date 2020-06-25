package com.hjq.demo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.hjq.base.BaseDialog;
import com.hjq.demo.R;
import com.hjq.demo.controller.MessageController;
import com.hjq.demo.model.MessageModel;
import com.hjq.demo.model.User;
import com.hjq.demo.ui.dialog.MessageDialog;
import com.hjq.toast.ToastUtils;

import java.util.ArrayList;
import java.util.Map;

public class MessageAdapterForTeacher extends BaseAdapter {

    private MessageController messageController;

    private Context context;

    private ArrayList<Map<String, Object>> list;

    public MessageAdapterForTeacher(Context context, ArrayList<Map<String, Object>> data, MessageController messageController) {
        this.context = context;
        this.list = data;
        this.messageController = messageController;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_message,null);
            viewHolder = new ViewHolder();
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.message_name);
            viewHolder.tv_detail = (TextView) convertView.findViewById(R.id.message_detail);
            viewHolder.btn_read = (Button) convertView.findViewById(R.id.btn_message_read);
            viewHolder.btn_delete = (Button) convertView.findViewById(R.id.btn_message_delete);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //设置数据
        viewHolder.tv_name.setText(list.get(position).get("message_name").toString());
        viewHolder.tv_detail.setText(list.get(position).get("message_detail").toString());
        if((boolean)list.get(position).get("read")){
            viewHolder.btn_read.setText("申请已通过");
            viewHolder.btn_read.setEnabled(false);
        }
        else {
            viewHolder.btn_read.setText("通过申请");
            viewHolder.btn_read.setEnabled(true);
        }
        viewHolder.btn_delete.setText("拒绝申请");
        setClick(viewHolder, position);

        return convertView;
    }

    static class ViewHolder{
        TextView tv_name;
        TextView tv_detail;
        Button btn_read;
        Button btn_delete;
    }

    public void setClick(ViewHolder viewHolder, int position){
        viewHolder.btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.btn_read.setText("申请已通过");
                viewHolder.btn_read.setEnabled(false);
                messageController.changeReadStateOf(position, User.TEACHER);
            }
        });

        viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MessageDialog.Builder(context)
                        // 标题可以不用填写
                        .setTitle("提示")
                        // 内容必须要填写
                        .setMessage("要拒绝这条申请吗，确认后将删除该消息")
                        // 确定按钮文本
                        .setConfirm("确认")
                        // 设置 null 表示不显示取消按钮
                        .setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener(new MessageDialog.OnListener() {

                            @Override
                            public void onConfirm(BaseDialog dialog) {
                                messageController.deleteOf(position, User.TEACHER);
                                ToastUtils.show("删除成功");
                            }

                            @Override
                            public void onCancel(BaseDialog dialog) {
                                //
                            }
                        })
                        .show();
            }
        });
    }
}
