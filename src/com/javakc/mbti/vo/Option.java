package com.javakc.mbti.vo;

//选项类
public class Option {
    //选项内容
    private String title;
    //选项得分J或P
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
