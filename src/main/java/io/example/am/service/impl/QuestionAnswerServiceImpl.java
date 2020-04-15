package io.example.am.service.impl;

import io.example.am.domain.QuestionAnswer;
import io.example.am.service.QuestionAnswerService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

public class QuestionAnswerServiceImpl implements QuestionAnswerService {
    public static final String EMPTY = "";
    public static final String QUESTION_MARK = "\\?";
    public static final String SPACE = "\"\\s+\"";
    public static final String DOUBLE_QUOTATION = "\"";
    public static final String STATIC_ANSWER = "The answer to life, universe and everything is 42";

    Map<String, QuestionAnswer> questionAnswers;
    Validator validator;

    public QuestionAnswerServiceImpl(Map<String, QuestionAnswer> questionAnswers) {
        this.questionAnswers = questionAnswers;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public QuestionAnswer parse(String data) {
        String[] questionAnswerSplit = data.split(QUESTION_MARK);
        String question = questionAnswerSplit[0].trim();
        if (questionAnswerSplit.length > 1) {
            String answer = questionAnswerSplit[1];
            String[] answers = answer.split(SPACE);
            if (answers.length > 1) {
                List<String> answerList = Arrays.stream(answers)
                        .map(s -> s.replaceAll(DOUBLE_QUOTATION, EMPTY).trim())
                        .collect(Collectors.toList());
                return new QuestionAnswer(question, answerList);
            }
        }
        return new QuestionAnswer(question, null);
    }

    private void validate(QuestionAnswer result) {
        Set<ConstraintViolation<QuestionAnswer>> violations = validator.validate(result);
        violations.forEach(v -> {
            throw new RuntimeException(v.getMessage());
        });
    }

    @Override
    public QuestionAnswer processAll(String data) {
        QuestionAnswer questionAnswer = parse(data);
        validate(questionAnswer);
        QuestionAnswer foundedQuestionAnswer = findByQuestion(questionAnswer.getQuestion());
        if (questionAnswer.getAnswers() == null && foundedQuestionAnswer == null) {
            return getDefault(questionAnswer.getQuestion());
        } else {
            saveOrUpdate(questionAnswer, foundedQuestionAnswer);
            return findByQuestion(questionAnswer.getQuestion());
        }
    }

    @Override
    public QuestionAnswer getDefault(String question) {
        return new QuestionAnswer(question, Collections.singletonList(STATIC_ANSWER));
    }

    @Override
    public QuestionAnswer saveOrUpdate(QuestionAnswer newQuestionAnswer, QuestionAnswer lastQuestionAnswer) {
        questionAnswers.put(newQuestionAnswer.getQuestion(),
                newQuestionAnswer.getAnswers() == null ? lastQuestionAnswer : newQuestionAnswer);
        return findByQuestion(newQuestionAnswer.getQuestion());
    }

    @Override
    public QuestionAnswer findByQuestion(String question) {
        return questionAnswers.get(question);
    }
}
