package com.javakc.mbti.tools;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.javakc.mbti.vo.Question;
import com.javakc.mbti.vo.Result;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class tools {
    public static void mapResult(Map<String, Integer> map) {
        if (map.get("E") == map.get("I")) {
            map.put("I", map.get("I") + 1);
        }
        if (map.get("S") == map.get("N")) {
            map.put("N", map.get("N") + 1);
        }
        if (map.get("T") == map.get("F")) {
            map.put("F", map.get("F") + 1);
        }
        if (map.get("J") == map.get("P")) {
            map.put("P", map.get("P") + 1);
        }
        char[] chars = new char[4];
        chars[0] = (map.get("E") > map.get("I") ? 'E' : 'I');
        chars[1] = (map.get("S") > map.get("N") ? 'S' : 'N');
        chars[2] = (map.get("T") > map.get("F") ? 'T' : 'F');
        chars[3] = (map.get("J") > map.get("P") ? 'J' : 'P');
        System.out.println("您的答题结果" + map);
        System.out.print(chars[0]);
        System.out.print(chars[1]);
        System.out.print(chars[2]);
        System.out.println(chars[3]);
    }

    public static void initMap(Map<String, Integer> map) {
        map.put("J", 0);
        map.put("P", 0);
        map.put("S", 0);
        map.put("N", 0);
        map.put("T", 0);
        map.put("F", 0);
        map.put("E", 0);
        map.put("I", 0);
    }

    public static void initResult(String url,String answerEnd) {
        Path path = Paths.get(url);
        String str = null;
        try {
            str = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取最终结果字符串

        //获取结果文档字符串
        //String str = "";
        //FileReader fileR = null;
        //try {
        //    fileR = new FileReader(url);
        //    //定义一个数组容器存放每一次的临时值，并定义每次的流量大小
        //    char[] chars = new char[1024];
        //    //定义一个长度字符控制每次输出的值与读取的值一致
        //    int len;
        //    //判断若通过read得到的值不为-1时继续循环并输出
        //    while ((len = fileR.read(chars)) != -1) {
        //        //解码输出，输出长度为读取的长度，这样不会出现空字符
        //        //System.out.print(str = new String(chars, 0, len));
        //        str = new String(chars, 0, len);
        //    }
        //    fileR.close();
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        //将结果文档转化为String
        //System.out.println(str);
        //将文档内容转换为Result对象
        Gson gson = new Gson();
        List<Result> results = gson.fromJson(str, new TypeToken<List<Result>>() {
        }.getType());
        //System.out.println(results);
        //将对象的结果编号与得到的字符串做对比
        //System.out.println(results.get(0).getNum());
        //System.out.println(results.get(1).getNum());
        //System.out.println(results.get(2).getNum());
        for (int i = 0; i < results.size(); i++) {
            if(results.get(i).getNum().equals(answerEnd)){
                System.out.println(results.get(i).getNum());
                System.out.println(results.get(i).getContent());
                break;
            }
        }
        //输出/return 所求结果
        //results.add();
        //System.out.println(answerEnd);

    }
}
