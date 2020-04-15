package io.example.am.service;

import io.example.am.domain.QuestionAnswer;

public interface QuestionAnswerService {
    QuestionAnswer parse(String data);
    QuestionAnswer processAll(String data);
    QuestionAnswer saveOrUpdate(QuestionAnswer newQuestionAnswer, QuestionAnswer lastQuestionAnswer);
    QuestionAnswer findByQuestion(String question);
    QuestionAnswer getDefault(String question);
}
