package com.hjq.demo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseSelectInfoModel {

    private static Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

    static {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        map.put(1,list);
    }

    public static int queryCourseSelectInfo(int uid, int cid){
        if(map.get(uid).contains(cid))
            return 1;
        else
            return -1;
    }

    public static void addCourseSelectInfo(int uid, int cid){
        if(!map.containsKey(uid)){
            List<Integer> list = new ArrayList<>();
            list.add(cid);
            map.put(uid, list);
        }
        else {
            map.get(uid).add(cid);
        }
    }

    public static List<Integer> queryCourseSelectInfo(int uid){
        if(!map.containsKey(uid)){
            List<Integer> list = new ArrayList<>();
            return list;
        }
        else {
            return map.get(uid);
        }
    }

}
