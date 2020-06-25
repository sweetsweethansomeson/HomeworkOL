package com.hjq.demo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.hjq.demo.R;
import com.hjq.demo.controller.InviteStuController;
import com.hjq.demo.controller.JoinCourseController;
import com.hjq.toast.ToastUtils;

import java.util.ArrayList;
import java.util.Map;

public class CourseAdapterForTeacher extends BaseAdapter {

    private InviteStuController inviteStuController;

    private Context context;

    private ArrayList<Map<String, Object>> list;

    public CourseAdapterForTeacher(Context context, ArrayList<Map<String, Object>> data, InviteStuController inviteStuController) {
        this.context = context;
        this.list = data;
        this.inviteStuController = inviteStuController;
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
        CourseAdapterForTeacher.ViewHolder viewHolder = null;

        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_course,null);
            viewHolder = new CourseAdapterForTeacher.ViewHolder();
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.course_name);
            viewHolder.tv_detail = (TextView) convertView.findViewById(R.id.course_detail);
            viewHolder.btn_join = (Button) convertView.findViewById(R.id.rb_album_check);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (CourseAdapterForTeacher.ViewHolder) convertView.getTag();
        }

        //设置数据
        viewHolder.tv_name.setText(list.get(position).get("course_name").toString());
        viewHolder.tv_detail.setText(list.get(position).get("course_detail").toString());
        if((int)list.get(position).get("already_in") == 1) {
            viewHolder.btn_join.setText("邀请学生");
            setClick(viewHolder, position);
        }
        else{
            viewHolder.btn_join.setVisibility(View.GONE);
        }

        return convertView;
    }

    static class ViewHolder{
        TextView tv_name;
        TextView tv_detail;
        Button btn_join;
    }

    public void setClick(CourseAdapterForTeacher.ViewHolder viewHolder, int position){
        viewHolder.btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.rb_album_check) {
                    Map<String, Object> map = (Map<String, Object>) list.get(position);
                    int courseId = Integer.parseInt(map.get("course_id").toString());
                    //ToastUtils.show(courseId + " 申请成功提交，请等待审核");
                    inviteStuController.inviteStuForCourse(courseId, context);
                }
            }
        });
    }
}
