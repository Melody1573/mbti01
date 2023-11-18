package com.javakc.mbti.action;

import com.javakc.mbti.service.imp.ServiceImpl;
import com.javakc.mbti.tools.tools;
import com.javakc.mbti.vo.Question;
import java.util.*;

public class App {
    public static void main(String[] args) {
        Start();
    }

    public static void Start() {
        ServiceImpl service = new ServiceImpl();
        List<Question> list = service.readQuestion();
        List<String> answer = new ArrayList();
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String next = scanner.next();
        System.out.println("欢迎你" + next + "\n");
        for (int i = 0; i < list.size(); i++) {
            while (true) {
                //输出题目
                System.out.print(list.get(i).getNum() + ".");
                System.out.println(list.get(i).getTitle());
                //输出选项
                System.out.println("A." + list.get(i).getOptions().get(0).getTitle());
                System.out.println("B." + list.get(i).getOptions().get(1).getTitle());
                //输入答案
                String sc = scanner.next();
                if ("A".equals(sc)) {
                    //列表的方式
                    answer.add(list.get(i).getOptions().get(0).getScore());
                    break;
                } else if ("B".equals(sc)) {
                    answer.add(list.get(i).getOptions().get(1).getScore());
                    break;
                } else {
                    System.err.println("输入错误");
                }
            }
            System.out.println("\n\n\n\n");
        }
        //判断结果
        String answerEnd = service.parseResult(answer);
        tools.initResult("src/Resources/result.txt", answerEnd);
    }
}
