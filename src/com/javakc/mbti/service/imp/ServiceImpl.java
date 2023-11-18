package com.javakc.mbti.service.imp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.javakc.mbti.dao.imp.DaoImpl;
import com.javakc.mbti.service.Service;
import com.javakc.mbti.vo.Question;
import jdk.swing.interop.SwingInterOpUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceImpl implements Service {
    /*
    1.	调用数据层读取文件中的json数据
    2.	将json数据解析成封装Question对象的List对象
    3.	返回List对象
    */
    @Override
    public List<Question> readQuestion() {
        //String strDao = "C:\\Users\\Luo\\Desktop\\question1.txt";
        String strDao = "src/Resources/question.txt";
        DaoImpl dao = new DaoImpl();
        String str = dao.read(strDao);
        Gson gson = new Gson();
        List<Question> qs = gson.fromJson(str, new TypeToken<List<Question>>() {
        }.getType());
        return qs;
    }
    /*
    1.	传入封装用户答案的List对象
    2.	统计List对象中的数据，得到【性格分类】
    3.	调用数据层读取Result信息
    4.	根据【性格分类】在Result信息中找到对象的分析结果，并返回
    */
    @Override
    public String parseResult(List<String> list) {
        int[] answerEnd = new int[8];
        char[] chars = new char[4];
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i)){
                case "E":
                    answerEnd[0]++;
                    break;
                case "I":
                    answerEnd[1]++;
                    break;
                case "S":
                    answerEnd[2]++;
                    break;
                case "N":
                    answerEnd[3]++;
                    break;
                case "T":
                    answerEnd[4]++;
                    break;
                case "F":
                    answerEnd[5]++;
                    break;
                case "J":
                    answerEnd[6]++;
                    break;
                case "P":
                    answerEnd[7]++;
                    break;
            }
        }
        //相同时判断部分
        if (answerEnd[0] == answerEnd[1]){
            answerEnd[1]++;
        }
        if (answerEnd[2] == answerEnd[3]){
            answerEnd[3]++;
        }
        if (answerEnd[4] == answerEnd[5]){
            answerEnd[5]++;
        }
        if (answerEnd[6] == answerEnd[7]){
            answerEnd[7]++;
        }
        //判定最终结果
        if (answerEnd[0] > answerEnd[1]){
            chars[0] = 'E';
        }else{
            chars[0] = 'I';
        }
        if (answerEnd[2] > answerEnd[3]){
            chars[1] = 'S';
        }else{
            chars[1] = 'N';
        }
        if (answerEnd[4] > answerEnd[5]){
            chars[2] = 'T';
        }else{
            chars[2] = 'F';
        }
        if (answerEnd[6] > answerEnd[7]){
            chars[3] = 'J';
        }else{
            chars[3 ] = 'P';
        }
        //System.out.println(Arrays.toString(chars));
        System.out.println(list);
        return ""+chars[0]+chars[1]+chars[2]+chars[3];
    }
}
