package com.javakc.mbti.tools;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.javakc.mbti.action.App;
import com.javakc.mbti.service.imp.ServiceImpl;
import com.javakc.mbti.vo.Question;
import com.javakc.mbti.vo.Result;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

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

    public static void initResult(String url, String answerEnd, Scanner scanner, List<String> answer) {
        Path path = Paths.get(url);
        String str = null;
        int num = 0;
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
                num = i;
                break;
            }
        }
        //此处调用答案持久化
        System.out.println("是否要保存结果?\n是/否");
        String next = scanner.next();
        saveResult((next.equals("是") ? true : false), results, num, answer);
    }

    //答案持久化
    public static void saveResult(Boolean bool, List<Result> results, int num, List<String> answer) {
        if (bool) {
            try {
                //获取当前年月日
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                //使用IO流保存文件
                FileOutputStream fileOutputStream = new FileOutputStream("save\\MBTI职业性格测试" + sdf.format(date.getTime()) + App.name + ".txt");
                FileOutputStream fileOutputStream1 = new FileOutputStream("src/Resources/history.txt", true);
                //OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream1, StandardCharsets.UTF_8);
                //保存答题信息
                fileOutputStream.write(("您的答案是" + answer + "\n").getBytes(StandardCharsets.UTF_8));
                fileOutputStream.write((results.get(num).getNum() + "\n").getBytes(StandardCharsets.UTF_8));
                fileOutputStream.write((results.get(num).getContent() + "\n").getBytes(StandardCharsets.UTF_8));
                //保存答题记录文件
                //BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                fileOutputStream1.write(("MBTI职业性格测试" + sdf.format(date.getTime()) + App.name + ".txt\n").getBytes(StandardCharsets.UTF_8));
                //关闭IO流
                fileOutputStream.close();
                fileOutputStream1.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    //主页面
    public static int displayHomePage(Scanner scanner) {
        System.out.println("\n");
        System.out.println("============================");
        System.out.println("1.随机答题");
        System.out.println("2.顺序答题");
        System.out.println("3.开启/关闭答题时间");
        System.out.println("4.重新输入用户名");
        System.out.println("5.获取历史记录");
        System.err.println("输入任意其它数字退出");
        System.out.println("============================");
        return scanner.nextInt();
    }

    //答题
    public static void doWork(List<Question> list, Scanner scanner, List<String> answer, ServiceImpl service) {
        //使用变量来记录打错次数实现打错3次后退出
        int numError = 0;
        //记录最终i的位置
        int iEnd = 0;
        boolean iStart = false;
        //循环答题功能
        for (int i = 0; i < list.size(); i++) {
            //for (int i = 0; i < 5; i++) {
            if (iStart) {
                i = iEnd;
                iStart = false;
            }
            if (i >= iEnd) {
                iEnd = i;
            } else {
                iStart = true;
            }
            while (true) {
                //输出题目
                System.out.print(list.get(i).getNum() + ".");
                System.out.println(list.get(i).getTitle());
                //输出选项
                System.out.println("A." + list.get(i).getOptions().get(0).getTitle());
                System.out.println("B." + list.get(i).getOptions().get(1).getTitle());
                //输入答案
                //String sc = scanner.next();
                //自动输入
                String sc = (new Random().nextInt(2) == 0 ? "A" : "B");
                if ("A".equalsIgnoreCase(sc)) {
                    //列表的方式
                    answer.add(i, list.get(i).getOptions().get(0).getScore());
                    break;
                } else if ("B".equalsIgnoreCase(sc)) {
                    answer.add(i, list.get(i).getOptions().get(1).getScore());
                    break;
                } else if ("cx".equalsIgnoreCase(sc)) {
                    if (iStart) {
                        i--;
                        System.out.println("请答完该题再进行重答");
                        iStart = false;
                        break;
                    }
                    i -= 2;
                    if (i < -1) {
                        i = -1;
                    } else {
                        answer.remove(i + 1);
                    }
                    break;
                } else if (sc.length() >= 3 && "cx".equalsIgnoreCase(sc.substring(0, 2))) {
                    if (iStart) {
                        i--;
                        System.out.println("请答完该题再进行重答");
                        iStart = false;
                        break;
                    }
                    //获取用户输入的题号
                    StringBuffer num = new StringBuffer();
                    int number = 1;
                    for (int j = 0; j < sc.length() - 2; j++) {
                        num.append(sc.charAt(j + 2));
                    }
                    try {
                        number = Integer.valueOf(num.toString());
                        //对题号进行判断
                        if (number >= i + 1 || i < -1) {
                            continue;
                        }
                        //对答案集合进行修改,只删除这个答案,答完这个题后返回到重答点
                        answer.remove(number - 1);
                        //通过改变i的值让下一题返回到指定点
                        i = number - 2;
                        break;
                    } catch (Exception e) {
                        System.out.println(e);
                        System.out.println("输错了");
                    }
                } else {
                    System.err.println("输入错误");
                    numError++;
                    if (numError >= 3) {
                        i = list.size();
                        System.out.println("答错3次,自动提交");
                        break;
                    }
                }
            }
            //输入完成答案后判断时间是否完毕
            if (App.endTime[0]) {
                System.err.println("时间到了,自动提交");
                break;
            }
            System.out.println("\n\n\n");
            System.out.println(answer);
        }
        App.endTime[0] = false;
        //判断结果
        String answerEnd = service.parseResult(answer);
        tools.initResult("src/Resources/result.txt", answerEnd, scanner, answer);
    }

    //查询历史记录
    public static void disHistory(Scanner scanner) {
        try {
            //获取保存所有历史记录的文件
            Path path = Paths.get("src/Resources/history.txt");
            final String s = Files.readString(path, StandardCharsets.UTF_8);
            //使用字符串分割其内容,获取其每条数据
            String[] strings = s.split("\n");
            //对最后三条数据进行遍历
            for (int i = strings.length - 3,num = 1; i < strings.length; i++,num++) {
                System.out.println(num + "." + strings[i]);
            }
            System.out.print("输入要查询历史数据的序号:");
            int i = scanner.nextInt();
            //获取当前文件的路径
            Path pathHistory = Paths.get("save/" + strings[strings.length - (i == 1 ? 3 : (i == 2 ? 2 : 1))]);
            String s1 = Files.readString(pathHistory);
            System.out.println(s1);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
