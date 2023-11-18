package com.javakc.mbti.dao.imp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.javakc.mbti.dao.Dao;
import com.javakc.mbti.vo.Question;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DaoImpl implements Dao {
    /*
    1.	��ȡ�ļ���Ϣ���õ��ֽ�����
    2.	���ֽ�����ת�����ַ�����������
    * */
    @Override
    @Test
    public String read(String str) {
        //String url = str;
        //
        //FileReader fileR = null;
        //try {
        //    fileR = new FileReader(url);
        //    //����һ�������������ÿһ�ε���ʱֵ��������ÿ�ε�������С
        //    char[] chars = new char[1024];
        //    //����һ�������ַ�����ÿ�������ֵ���ȡ��ֵһ��
        //    int len;
        //    //�ж���ͨ��read�õ���ֵ��Ϊ-1ʱ����ѭ�������
        //    while ((len = fileR.read(chars)) != -1) {
        //        //����������������Ϊ��ȡ�ĳ��ȣ�����������ֿ��ַ�
        //        //System.out.print(str = new String(chars, 0, len));
        //        str = new String(chars, 0, len);
        //    }
        //    fileR.close();
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        try {
            Path path = Paths.get(str);
            str = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
