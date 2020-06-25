package com.hjq.demo.controller;

import com.hjq.demo.common.MyController;
import com.hjq.demo.model.CourseModel;
import com.hjq.demo.model.CourseSelectInfoModel;
import com.hjq.demo.model.MessageModel;
import com.hjq.demo.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoinCourseController implements MyController {

    private User user;
    private CourseModel course;

    public JoinCourseController(User user){
        this.user = user;
    }

    @Override
    public void initModel(Object model) {

    }

    @Override
    public void saveModel() {
        System.out.println(user.toString() + "->" + course.getName() + "   SAVED");
    }

    public boolean parseInputInfo(int targetCid){
        if(checkCourse(targetCid)){
            course = CourseModel.queryCourseInfoById(targetCid);
            /*Course c = new Course();
            c.setTid(course.getTid());
            c.setTname(course.getTname());
            c.setGrade(course.getGrade());
            c.setName(course.getName());
            c.setCid(course.getCid());*/
            //CourseSelectInfoModel.addCourseSelectInfo(user.getUid(), course.getCid());
            MessageModel.addMessageInfo(
                    new MessageModel(course.getCid(), course.getTid(), user.getUid(), MessageModel.STUDENT_TO_TEACHER));
            return true;
        }
        else
            return false;
    }

    public boolean checkCourse(int cid){
        return CourseModel.isExist(cid);
    }

    public ArrayList<Map<String, Object>> getCourseInfoOfUser(){
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Integer> courses = CourseSelectInfoModel.queryCourseSelectInfo(user.getUid());
        int size = courses.size();
        int i;
        CourseModel course;

        for(i = 0; i < size; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            course = CourseModel.queryCourseInfoById(courses.get(i));
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
        if(CourseSelectInfoModel.queryCourseSelectInfo(user.getUid(), cid) == 1) {
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
        map.put("already_in", CourseSelectInfoModel.queryCourseSelectInfo(user.getUid(), course.getCid()));
        list.add(0, map);

        return list;
    }
}
