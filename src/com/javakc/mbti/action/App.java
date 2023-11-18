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
        //初始化资源
        ServiceImpl service = new ServiceImpl();
        List<Question> list = service.readQuestion();
        List<String> answer = new ArrayList();
        Timer timer = null;
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean off = true;
        boolean rand = false;
        boolean time = false;

        //用户名功能
        System.out.print("请输入用户名：");
        String next = scanner.next();
        System.out.println("欢迎你" + next + "\n");

        //显示主页面选择功能
        while (off) {
            //用户来选择
            switch (tools.displayHomePage(scanner)) {
                case 1:
                    rand = true;
                case 2:
                    //是否要开启时间模式
                    if (time) {
                        timer = new Timer();
                        System.out.println("\n\n\n");
                        System.out.println("30分钟开始计时");
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                endTime[0] = true;
                                System.err.println("规定时间已到,完成本次答题后自动提交");
                            }
                        }, 2000);
                    }
                    //是否要随机答题
                    if (rand) {
                        //创建随机题目队列
                        List<Question> listRandom = new ArrayList<>();
                        //创建布尔数组来检查该随机题目是否重复
                        boolean[] booleans = new boolean[list.size()];
                        //将list中随机的一个元素放到listRandom的1-93个位置上
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
                    //启动计时模式
                    //点击功能后可选择开启/关闭记时功能,若开启后下一次答题会出现时间限制
                    time = !time;
                    break;
                default:
                    // 停止定时器
                    if (timer != null) {
                        timer.cancel();
                    }
                    off = false;
                    break;
            }
        }

    }
}
