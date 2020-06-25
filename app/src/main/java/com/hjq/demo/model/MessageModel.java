package com.hjq.demo.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageModel {

    public static final int STUDENT_TO_TEACHER = 1;
    public static final int TEACHER_TO_STUDENT = 0;

    /** 课程ID */
    private int cid;
    /** 教师ID */
    private int tid;
    /** 学生ID */
    private int sid;
    /** 创建时间 */
    private String createTime;
    /** 已读状态 */
    private boolean read;
    /** 消息传达方向 */
    private int direction;
    /** 教师姓名 */
    private String tname;
    /** 课程名称 */
    private String cname;
    /** 学生姓名 */
    private String sname;
    /** 教师工号 */
    private String tstrId;
    /** 学生学号 */
    private String sstrId;

    public MessageModel(int cid, int tid, int sid, int direction){
        this.cid = cid;
        this.tid = tid;
        this.sid = sid;
        this.direction = direction;
        this.read = false;

        User teacher = User.queryUserInfoById(tid);
        this.tname = teacher.getUsrname();
        this.tstrId = teacher.getStrID();

        User student = User.queryUserInfoById(sid);
        this.sname = student.getUsrname();
        this.sstrId = student.getStrID();

        this.cname = CourseModel.queryCourseInfoById(cid).getName();
        Date now = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        this.createTime = ft.format(now);
    }

    public int getCid() {
        return cid;
    }

    public int getSid() {
        return sid;
    }

    public int getTid() {
        return tid;
    }

    public boolean isRead() {
        return read;
    }

    public int getDirection() {
        return direction;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getTname() {
        return tname;
    }

    public String getCname() {
        return cname;
    }

    public String getSname() {
        return sname;
    }

    public String getSstrId() {
        return sstrId;
    }

    public String getTstrId() {
        return tstrId;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    /*
     *  向消息表发起查询
     *  输入：要查询的学生id   返回所有sid == 该学生id 且 direction == TEACHER_TO_STUDENT 的记录 在list中按创建时间降序排列
     */
    public static List<MessageModel> queryMessageInfoByStudent(int sid) {
        //test
        List<MessageModel> list = new ArrayList<MessageModel>();

        list.add(new MessageModel(1, 1, 1, TEACHER_TO_STUDENT));

        return list;
    }


    /*
     *  向消息表发起查询
     *  输入：要查询的教师id   返回所有tid == 该教师id 且 direction == STUDENT_TO_TEACHER 的记录 在list中按创建时间降序排列
     */
    public static List<MessageModel> queryMessageInfoByTeacher(int tid) {
        //test
        List<MessageModel> list = new ArrayList<MessageModel>();

        list.add(new MessageModel(1, 1, 1, STUDENT_TO_TEACHER));

        return list;
    }

    /*
     *  向消息表发起更新，将该消息的已读状态置为真
     *  输入：要更新的message
     */
    public static void updateMessageInfo(MessageModel messageModel) {
        //test

    }

    /*
     *  向消息表发起删除
     *  输入：要删除的message
     */
    public static void deleteMessageInfo(MessageModel messageModel) {
        //test

    }

    /*
     *  向消息表插入数据
     *  输入：要插入的message
     */
    public static void addMessageInfo(MessageModel messageModel) {
        //test

    }
}
