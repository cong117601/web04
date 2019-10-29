package com.yunhe.dao;

import com.yunhe.javabean.Student;

import java.util.ArrayList;

public interface StudentDao {
    //根据学生id 和学生密码 查找学生姓名
    public ArrayList<Student> selectWhereIdAndWord(String sid, String sword);
    //查询学生表所有
    public ArrayList<Student> selectAll();

}
