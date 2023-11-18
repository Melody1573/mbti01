package com.javakc.mbti.tools;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.javakc.mbti.action.App;
import com.javakc.mbti.service.imp.ServiceImpl;
import com.javakc.mbti.vo.Question;
import com.javakc.mbti.vo.Result;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
        System.out.println("���Ĵ�����" + map);
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

    public static void initResult(String url, String answerEnd) {
        Path path = Paths.get(url);
        String str = null;
        try {
            str = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        List<Result> results = gson.fromJson(str, new TypeToken<List<Result>>() {
        }.getType());
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getNum().equals(answerEnd)) {
                System.out.println(results.get(i).getNum());
                System.out.println(results.get(i).getContent());
                break;
            }
        }
    }

    //��ҳ��
    public static int displayHomePage(Scanner scanner) {
        System.out.println("\n\n\n");
        System.out.println("============================");
        System.out.println("1.�������");
        System.out.println("2.˳�����");
        System.out.println("3.����/�رմ���ʱ��");
        System.err.println("�����������������˳�");
        System.out.println("============================");
        return scanner.nextInt();
    }

    //����
    public static void doWork(List<Question> list, Scanner scanner, List<String> answer, ServiceImpl service) {
        //ʹ�ñ�������¼������ʵ�ִ��3�κ��˳�
        int numError = 0;
        //ѭ�����⹦��
        for (int i = 0; i < list.size(); i++) {
            while (true) {
                //�����Ŀ
                System.out.print(list.get(i).getNum() + ".");
                System.out.println(list.get(i).getTitle());
                //���ѡ��
                System.out.println("A." + list.get(i).getOptions().get(0).getTitle());
                System.out.println("B." + list.get(i).getOptions().get(1).getTitle());
                //�����
                String sc = scanner.next();
                //�Զ�����
                //String sc = (new Random().nextInt(2) == 0? "A" : "B");
                if ("A".equalsIgnoreCase(sc)) {
                    //�б�ķ�ʽ
                    answer.add(list.get(i).getOptions().get(0).getScore());
                    break;
                } else if ("B".equalsIgnoreCase(sc)) {
                    answer.add(list.get(i).getOptions().get(1).getScore());
                    break;
                } else {
                    System.err.println("�������");
                    numError++;
                    if (numError >= 3){
                        i = list.size();
                        System.out.println("���3��,�Զ��ύ");
                        break;
                    }
                }
            }
            //������ɴ𰸺��ж�ʱ���Ƿ����
            if (App.endTime[0]){
                System.err.println("ʱ�䵽��,�Զ��ύ");
                break;
            }
            System.out.println("\n\n\n\n");
        }
        App.endTime[0] = false;

        //�жϽ��
        String answerEnd = service.parseResult(answer);
        tools.initResult("src/Resources/result.txt", answerEnd);
    }
}
