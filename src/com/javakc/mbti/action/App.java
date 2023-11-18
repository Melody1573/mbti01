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
        System.out.print("�������û�����");
        String next = scanner.next();
        System.out.println("��ӭ��" + next + "\n");
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
                if ("A".equals(sc)) {
                    //�б�ķ�ʽ
                    answer.add(list.get(i).getOptions().get(0).getScore());
                    break;
                } else if ("B".equals(sc)) {
                    answer.add(list.get(i).getOptions().get(1).getScore());
                    break;
                } else {
                    System.err.println("�������");
                }
            }
            System.out.println("\n\n\n\n");
        }
        //�жϽ��
        String answerEnd = service.parseResult(answer);
        tools.initResult("src/Resources/result.txt", answerEnd);
    }
}
