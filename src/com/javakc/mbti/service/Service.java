package com.javakc.mbti.service;

import com.javakc.mbti.vo.Question;

import java.util.List;

public interface Service {
    //������Ŀ����
    List<Question> readQuestion();

    //�����û��𰸷����õ��Ը���
    String parseResult(List<String> list);
}
