package com.yunhe;

import com.yunhe.util.AiUtil;

public class Test {
    public static void main(String[] args) {
        AiUtil.dbDMLWithSQL("delete from student where id=6");
    }
}
