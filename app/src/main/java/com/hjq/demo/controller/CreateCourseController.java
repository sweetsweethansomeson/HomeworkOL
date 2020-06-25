package com.hjq.demo.controller;

import com.hjq.demo.common.MyController;
import com.hjq.demo.model.CourseModel;
import com.hjq.demo.model.User;
import com.hjq.toast.ToastUtils;

public class CreateCourseController implements MyController {

    private CourseModel courseModel;
    private User teacher;

    public CreateCourseController(User teacher, String name, int grade){
        //test
        /*CourseModel.addCourseInfo(new CourseModel(20, "高等数学", 1, "李艳会"));
        CourseModel.addCourseInfo(new CourseModel(19, "离散数学", 2, "聂卉"));
        CourseModel.addCourseInfo(new CourseModel(19, "Java语言程序设计", 3, "王乐球"));*/

        this.teacher = teacher;
        courseModel = new CourseModel(grade, name, teacher.getUid(), teacher.getUsrname());
        saveModel();
    }

    @Override
    public void initModel(Object model) {

    }

    @Override
    public void saveModel() {
        CourseModel.addCourseInfo(courseModel);
        ToastUtils.show("添加完成");
    }
}
