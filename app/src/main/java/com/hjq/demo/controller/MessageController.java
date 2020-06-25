package com.hjq.demo.controller;

import com.hjq.demo.model.MessageModel;
import com.hjq.demo.model.User;
import com.hjq.demo.view.StudentMessageView;
import com.hjq.demo.view.TeacherMessageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageController {

    private User user;
    private StudentMessageView studentMessageView = null;
    private TeacherMessageView teacherMessageView = null;

    public MessageController(User user, StudentMessageView studentMessageView){
        this.user = user;
        this.studentMessageView = studentMessageView;
    }

    public MessageController(User user, TeacherMessageView teacherMessageView){
        this.user = user;
        this.teacherMessageView = teacherMessageView;
    }

    public ArrayList<Map<String, Object>> getMessageInfoOfStudent() {
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<MessageModel> messages = MessageModel.queryMessageInfoByStudent(user.getUid());

        /*if(messages == null)
            return list;*/
        int size = messages.size();
        int i;
        MessageModel messageModel;

        for(i = 0; i < size; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            messageModel = messages.get(i);
            map.put("message_name", "教师 " + messageModel.getTname() + " " + messageModel.getTstrId());
            map.put("message_detail", "已邀请你加入课程 " + messageModel.getCname() + "    " + messageModel.getCreateTime());
            map.put("read", messageModel.isRead());
            list.add(map);
        }

        return list;
    }

    public ArrayList<Map<String, Object>> getMessageInfoOfTeacher() {
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<MessageModel> messages = MessageModel.queryMessageInfoByTeacher(user.getUid());

        /*if(messages == null)
            return list;*/
        int size = messages.size();
        int i;
        MessageModel messageModel;

        for(i = 0; i < size; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            messageModel = messages.get(i);
            map.put("message_name", "学生 " + messageModel.getSname() + " " + messageModel.getSstrId());
            map.put("message_detail", "申请加入课程 " + messageModel.getCname() + "    " + messageModel.getCreateTime());
            map.put("read", messageModel.isRead());
            list.add(map);
        }

        return list;
    }


    public void changeReadStateOf(int position, int usertype) {
        List<MessageModel> messages;
        if(usertype == User.STUDENT)
            messages = MessageModel.queryMessageInfoByStudent(user.getUid());
        else
            messages = MessageModel.queryMessageInfoByTeacher(user.getUid());
        MessageModel messageModel = messages.get(position);

        messageModel.setRead(true);
        MessageModel.updateMessageInfo(messageModel);
    }

    public void deleteOf(int position, int usertype) {
        List<MessageModel> messages;
        if(usertype == User.STUDENT)
            messages = MessageModel.queryMessageInfoByStudent(user.getUid());
        else
            messages = MessageModel.queryMessageInfoByTeacher(user.getUid());
        MessageModel messageModel = messages.get(position);

        MessageModel.deleteMessageInfo(messageModel);
        if(studentMessageView != null)
            studentMessageView.load();
        else
            teacherMessageView.load();
    }
}
