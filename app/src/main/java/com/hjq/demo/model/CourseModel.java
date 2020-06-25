package com.hjq.demo.model;

import android.renderscript.ScriptIntrinsic3DLUT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseModel {

    //test
    private static Map<Integer, List<CourseModel>> map1 = new HashMap<Integer, List<CourseModel>>();
    private static Map<Integer, CourseModel> map2 = new HashMap<Integer, CourseModel>();

    static {
        /*addCourseInfo(new CourseModel(20, "高等数学", 1, "李艳会"));
        addCourseInfo(new CourseModel(19, "离散数学", 2, "聂卉"));
        addCourseInfo(new CourseModel(19, "Java语言程序设计", 3, "王乐球"));*/
    }

    /** 课程总数 */
    private static int sum = 0;

    /** 面向年级 */
    private int grade;
    /** 课程名 */
    private String name;
    /** 课程ID */
    private int cid;
    /** 任课教师ID */
    private int tid;
    /** 任课教师姓名 */
    private String tname;

    public CourseModel(int grade, String name, int tid, String tname){
        this.grade = grade;
        this.name = name;
        this.cid = sum;
        this.tid = tid;
        this.tname = tname;
    }

    public int getGrade() {
        return grade;
    }

    public int getCid() {
        return cid;
    }

    public int getTid() {
        return tid;
    }

    public String getName() {
        return name;
    }

    public String getTname() {
        return tname;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    /*
     *  向课程表发起查询
     *  输入：课程id   返回课程实体
     */
    public static CourseModel queryCourseInfoById(int cid){
        //test
        return map2.get(cid);
    }

    /*
     *  向课程表发起查询
     *  输入：教师id   返回该教师任教的所有课程列表
     */
    public static List<CourseModel> queryCourseInfoByTeacher(int tid){
        //test
        if(!map1.containsKey(tid))
            return null;
        else
            return map1.get(tid);
    }

    /*
     *  向课程表发起查询
     *  输入：课程id   课程存在，返回true，否则false
     */
    public static boolean isExist(int cid){
        //test
        if(!map2.containsKey(cid))
            return false;
        else
            return true;
    }

    /*
     *  向课程表添加数据
     *  输入：课程实体   无返回值
     */
    public static void addCourseInfo(CourseModel courseModel){
        //test
        sum++;
        courseModel.cid = sum;
        List<CourseModel> list = new ArrayList<>();
        //list.add(new CourseModel(courseModel.getGrade(), courseModel.getName(), courseModel.getTid(), courseModel.getTname()));
        list.add(courseModel);
        map1.put(courseModel.tid, list);
        //map2.put(courseModel.cid, new CourseModel(courseModel.getGrade(), courseModel.getName(), courseModel.getTid(), courseModel.getTname()));
        map2.put(courseModel.cid, courseModel);
        System.out.println(courseModel.toString());
    }

    public String toString(){
        String s = "ID." + cid + " " + name + "    teacher: (" + tid + ") " + tname;
        s += ("     for grade " + grade);
        return s;
    }
}

