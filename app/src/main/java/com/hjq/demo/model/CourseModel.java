package com.hjq.demo.model;

public class CourseModel {

    /** 面对年级 */
    private int grade;
    /** 课程名 */
    private String name;
    /** 课程ID */
    private int cid;
    /** 任课教师ID */
    private int tid;
    /** 任课教师姓名 */
    private String tname;

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

    public static CourseModel getCourseInfoById(int cid){
        //test
        CourseModel courseModel = new CourseModel();

        if(cid == 1) {
            courseModel.setCid(1);
            courseModel.setGrade(1);
            courseModel.setTid(1);
            courseModel.setTname("李艳会");
            courseModel.setName("高等数学");
        }
        if(cid == 2) {
            courseModel.setCid(2);
            courseModel.setGrade(2);
            courseModel.setTid(2);
            courseModel.setTname("聂卉");
            courseModel.setName("离散数学");
        }
        if(cid == 3) {
            courseModel.setCid(3);
            courseModel.setGrade(2);
            courseModel.setTid(3);
            courseModel.setTname("王乐球");
            courseModel.setName("Java语言程序设计");
        }

        return courseModel;
    }

    public static boolean isExist(int cid){
        //省略
        return true;
    }
}

