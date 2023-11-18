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
        try {
            Path path = Paths.get(str);
            str = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
