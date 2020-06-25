package com.hjq.demo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseSelectInfoModel {

    //test
    private static Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

    static {
        /*List<Integer> list = new ArrayList<>();
        list.add(1);
        map.put(1,list);*/
    }

    /*
     *  向选课表发起查询
     *  输入：用户id(学生id)，课程id  学生选了这门课，返回1，否则-1
     */
    public static int queryCourseSelectInfo(int uid, int cid){
        //test
        if(!map.containsKey(uid))
            return -1;
        if(map.get(uid).contains(cid))
            return 1;
        else
            return -1;
    }

    /*
     *  向选课表添加数据
     *  输入：用户id(学生id)，课程id  无返回值
     */
    public static void addCourseSelectInfo(int uid, int cid){
        //test
        if(!map.containsKey(uid)){
            List<Integer> list = new ArrayList<>();
            list.add(cid);
            map.put(uid, list);
        }
        else {
            map.get(uid).add(cid);
        }
    }

    /*
     *  向选课表发起查询
     *  输入：用户id(学生id)  返回该学生选择的所有课程
     */
    public static List<Integer> queryCourseSelectInfo(int uid){
        //test
        if(!map.containsKey(uid)){
            List<Integer> list = new ArrayList<>();
            return list;
        }
        else {
            return map.get(uid);
        }
    }

}
