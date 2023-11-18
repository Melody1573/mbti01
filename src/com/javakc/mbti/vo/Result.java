package com.javakc.mbti.vo;

//结果类
public class Result {
    //结果编号:ISFJ
    private String num;
    //结果内容
    private String content;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Result{" +
                "num='" + num + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
