package com.hjq.demo.controller;

import com.hjq.demo.common.MyController;
import com.hjq.demo.model.CourseModel;
import com.hjq.demo.model.CourseSelectInfoModel;
import com.hjq.demo.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoinCourseController implements MyController {

    private User user;
    private CourseModel courseModel;

    public JoinCourseController(User user){
        this.user = user;
    }

    @Override
    public void initModel(Object model) {

    }

    @Override
    public void saveModel() {
        System.out.println(user.toString() + "->" + courseModel.getName() + "   SAVED");
    }

    public boolean parseInputInfo(int targetCid){
        if(checkCourse(targetCid)){
            courseModel = CourseModel.getCourseInfoById(targetCid);
            CourseModel c = new CourseModel();
            c.setTid(courseModel.getTid());
            c.setTname(courseModel.getTname());
            c.setGrade(courseModel.getGrade());
            c.setName(courseModel.getName());
            c.setCid(courseModel.getCid());
            CourseSelectInfoModel.addCourseSelectInfo(user.getId(), courseModel.getCid());
            createMessage(courseModel);
            return true;
        }
        else
            return false;
    }

    public boolean checkCourse(int cid){
        return CourseModel.isExist(cid);
    }

    public void createMessage(CourseModel courseModel){
        System.out.println("MESSAGE CALLED");
    }

    public ArrayList<Map<String, Object>> getCourseInfoOfUser(){
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Integer> courseModels = CourseSelectInfoModel.queryCourseSelectInfo(user.getId());
        int size = CourseSelectInfoModel.queryCourseSelectInfo(user.getId()).size();
        int i;
        CourseModel courseModel;

        for(i = 0; i < size; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            courseModel = CourseModel.getCourseInfoById(courseModels.get(i));
            map.put("course_id", courseModel.getCid());
            map.put("course_name", courseModel.getName());
            String s = "开课年级：" + courseModel.getGrade() + "年级\n任课教师：" + courseModel.getTname();
            map.put("course_detail", s);
            map.put("already_in", 1);
            list.add(map);
        }

        return list;
    }

    public ArrayList<Map<String, Object>> getCourseInfoById(int cid){
        ArrayList<Map<String, Object>> list = this.getCourseInfoOfUser();
        if(CourseSelectInfoModel.queryCourseSelectInfo(user.getId(), cid) == 1) {
            for(Map<String, Object> map : list){
                if((int)map.get("course_id") == cid){
                    list.remove(map);
                    break;
                }
            }
        }
        CourseModel courseModel = CourseModel.getCourseInfoById(cid);
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("course_id", courseModel.getCid());
        map.put("course_name", courseModel.getName());
        String s = "开课年级：" + courseModel.getGrade() + "年级\n任课教师：" + courseModel.getTname();
        map.put("course_detail", s);
        map.put("already_in", CourseSelectInfoModel.queryCourseSelectInfo(user.getId(), courseModel.getCid()));
        list.add(0, map);

        return list;
    }
}
