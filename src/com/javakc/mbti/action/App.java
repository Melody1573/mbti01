package com.javakc.mbti.action;

import com.javakc.mbti.service.imp.ServiceImpl;
import com.javakc.mbti.tools.tools;
import com.javakc.mbti.vo.Question;
import com.sun.source.tree.NewArrayTree;

import java.util.*;

public class App {
    public static boolean[] endTime = {false};

    public static void main(String[] args) {
        Start();
    }

    public static void Start() {
        //��ʼ����Դ
        ServiceImpl service = new ServiceImpl();
        List<Question> list = service.readQuestion();
        List<String> answer = new ArrayList();
        Timer timer = null;
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean off = true;
        boolean rand = false;
        boolean time = false;

        //�û�������
        System.out.print("�������û�����");
        String next = scanner.next();
        System.out.println("��ӭ��" + next + "\n");

        //��ʾ��ҳ��ѡ����
        while (off) {
            //�û���ѡ��
            switch (tools.displayHomePage(scanner)) {
                case 1:
                    rand = true;
                case 2:
                    //�Ƿ�Ҫ����ʱ��ģʽ
                    if (time) {
                        timer = new Timer();
                        System.out.println("\n\n\n");
                        System.out.println("30���ӿ�ʼ��ʱ");
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                endTime[0] = true;
                                System.err.println("�涨ʱ���ѵ�,��ɱ��δ�����Զ��ύ");
                            }
                        }, 2000);
                    }
                    //�Ƿ�Ҫ�������
                    if (rand) {
                        //���������Ŀ����
                        List<Question> listRandom = new ArrayList<>();
                        //�����������������������Ŀ�Ƿ��ظ�
                        boolean[] booleans = new boolean[list.size()];
                        //��list�������һ��Ԫ�طŵ�listRandom��1-93��λ����
                        for (int i = 0; i < list.size(); i++) {
                            while (true) {
                                int num = random.nextInt(93);
                                if (!booleans[num]) {
                                    listRandom.add(i, list.get(num));
                                    booleans[num] = true;
                                    break;
                                }
                            }
                        }
                        tools.doWork(listRandom, scanner, answer, service);
                    } else {
                        tools.doWork(list, scanner, answer, service);
                    }
                    rand = false;
                    break;
                case 3:
                    //������ʱģʽ
                    //������ܺ��ѡ����/�رռ�ʱ����,����������һ�δ�������ʱ������
                    time = !time;
                    break;
                default:
                    // ֹͣ��ʱ��
                    if (timer != null) {
                        timer.cancel();
                    }
                    off = false;
                    break;
            }
        }

    }
}
