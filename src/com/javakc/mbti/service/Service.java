package com.javakc.mbti.service;

import com.javakc.mbti.vo.Question;

import java.util.List;

public interface Service {
    //读出题目内容
    List<Question> readQuestion();

    //根据用户答案分析得到性格结果
    String parseResult(List<String> list);
}
