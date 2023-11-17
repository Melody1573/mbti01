package com.javakc.mbti.dao.imp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.javakc.mbti.dao.Dao;
import com.javakc.mbti.vo.Question;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

public class DaoImpl implements Dao {
    /*
    1.	读取文件信息，得到字节数组
    2.	将字节数组转换成字符串，并返回
    * */
    @Override
    @Test
    public String read(String str) {
        String url = str;

        FileReader fileR = null;
        try {
            fileR = new FileReader(url);
            //定义一个数组容器存放每一次的临时值，并定义每次的流量大小
            char[] chars = new char[1024];
            //定义一个长度字符控制每次输出的值与读取的值一致
            int len;
            //判断若通过read得到的值不为-1时继续循环并输出
            while ((len = fileR.read(chars)) != -1) {
                //解码输出，输出长度为读取的长度，这样不会出现空字符
                //System.out.print(str = new String(chars, 0, len));
                str = new String(chars, 0, len);
            }
            fileR.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }
}
