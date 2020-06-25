package com.hjq.demo.controller;

import android.content.Context;

import com.hjq.base.BaseDialog;
import com.hjq.demo.R;
import com.hjq.demo.common.MyController;
import com.hjq.demo.model.CourseModel;
import com.hjq.demo.model.CourseSelectInfoModel;
import com.hjq.demo.model.MessageModel;
import com.hjq.demo.model.User;
import com.hjq.demo.ui.dialog.InputDialog;
import com.hjq.toast.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InviteStuController implements MyController {

    private User user;
    private CourseModel course;

    public InviteStuController(User user){
        this.user = user;
    }

    @Override
    public void initModel(Object model) {

    }

    @Override
    public void saveModel() {

    }

    public boolean checkCourse(int cid){
        return CourseModel.isExist(cid);
    }

    public boolean checkStudent(String strID) {
        return User.isExist(User.STUDENT, strID);
    }

    public void inviteStuForCourse(int cid, Context context){
        new InputDialog.Builder(context)
                // 标题可以不用填写
                .setTitle("邀请学生")
                // 内容可以不用填写
                .setContent("")
                // 提示可以不用填写
                .setHint("输入想要邀请的学生学号(8位)")
                // 确定按钮文本
                .setConfirm("确认")
                // 设置 null 表示不显示取消按钮
                .setCancel("取消")
                // 设置点击按钮后不关闭对话框
                //.setAutoDismiss(false)
                .setListener(new InputDialog.OnListener() {

                    @Override
                    public void onConfirm(BaseDialog dialog, String content) {
                        if(checkStudent(content)){
                            createCourseSelectInfoModel(cid, content);
                        }
                        else
                            ToastUtils.show("该学号无匹配学生，请确认后重试");
                    }

                    @Override
                    public void onCancel(BaseDialog dialog) { }
                })
                .show();
    }

    public void createCourseSelectInfoModel(int cid, String strID){
        int sid = User.getUidByStrID(strID);
        if(CourseSelectInfoModel.queryCourseSelectInfo(sid, cid) == -1) {
            CourseSelectInfoModel.addCourseSelectInfo(sid, cid);
            ToastUtils.show("邀请成功");
            MessageModel.addMessageInfo(new MessageModel(cid, user.getUid(), sid, MessageModel.TEACHER_TO_STUDENT));
        }
        else
            ToastUtils.show("该学生已加入此课程");
    }

    public ArrayList<Map<String, Object>> getCourseInfoOfUser(){
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<CourseModel> courses = CourseModel.queryCourseInfoByTeacher(user.getUid());
        if(courses == null)
            return list;
        int size = courses.size();
        int i;
        CourseModel course;

        for(i = 0; i < size; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            course = CourseModel.queryCourseInfoById(courses.get(i).getCid());
            map.put("course_id", course.getCid());
            map.put("course_name", "ID" + course.getCid() + ". " + course.getName());
            String s = "开课年级：" + course.getGrade() + "级\n任课教师：" + course.getTname();
            map.put("course_detail", s);
            map.put("already_in", 1);
            list.add(map);
        }

        return list;
    }

    public ArrayList<Map<String, Object>> getCourseInfoById(int cid){
        if(!checkCourse(cid))
            return null;
        ArrayList<Map<String, Object>> list = this.getCourseInfoOfUser();
        if(CourseModel.queryCourseInfoById(cid).getTid() == user.getUid()) {
            for(Map<String, Object> map : list){
                if((int)map.get("course_id") == cid){
                    list.remove(map);
                    break;
                }
            }
        }
        CourseModel course = CourseModel.queryCourseInfoById(cid);
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("course_id", course.getCid());
        map.put("course_name", "ID" + course.getCid() + ". " + course.getName());
        String s = "开课年级：" + course.getGrade() + "级\n任课教师：" + course.getTname();
        map.put("course_detail", s);
        map.put("already_in", CourseModel.queryCourseInfoById(course.getCid()).getTid() == user.getUid() ? 1 : -1);
        list.add(0, map);

        return list;
    }
}
