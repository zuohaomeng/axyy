package com.axyy.util;


import cn.hutool.core.date.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * id编号生成
 * @date 2020/4/15--16:45
 */

public class IDUtil {
    public static String createId(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(" yyyyMMddHHmmss");
        String id = simpleDateFormat.format(date)+(DateUtil.current(true)/100%100);
        return id;
    }

    public static void main(String[] args) {
        System.out.println(IDUtil.createId());
    }
}
