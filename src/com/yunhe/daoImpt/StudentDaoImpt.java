package com.yunhe.daoImpt;

import com.yunhe.util.AiUtil;
import com.yunhe.dao.StudentDao;
import com.yunhe.javabean.Student;

import java.util.ArrayList;


public class StudentDaoImpt implements StudentDao {
    //查询语句  查找sname
    @Override
    public ArrayList<Student> selectWhereIdAndWord(String sid, String sword) {
        String sql = "select sname from student where sid=? and sword=?";
        return AiUtil.dbDQLWithSQL(sql, Student.class, sid, sword);

    }

    //查询student 表
    @Override
    public ArrayList<Student> selectAll() {
        String sql = "select * from student";
        ArrayList<Student> students = AiUtil.dbDQLWithSQL(sql, Student.class);
        return students;

    }
}
