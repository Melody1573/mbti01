package com.javakc.mbti.vo;

//ѡ����
public class Option {
    //ѡ������
    private String title;
    //ѡ��÷�J��P
    private String score;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Option{" +
                "title='" + title + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
