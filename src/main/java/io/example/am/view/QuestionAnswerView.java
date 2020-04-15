package io.example.am.view;

import io.example.am.domain.QuestionAnswer;
import io.example.am.service.QuestionAnswerService;
import io.example.am.service.impl.QuestionAnswerServiceImpl;

import java.util.HashMap;
import java.util.Scanner;

public class QuestionAnswerView {
    final QuestionAnswerService questionAnswerService;

    public QuestionAnswerView(QuestionAnswerService questionAnswerService) {
        this.questionAnswerService = null == questionAnswerService ?
                new QuestionAnswerServiceImpl(new HashMap<>()):
                questionAnswerService;
    }

    private void printInitMessage() {
        String initialMessage = "Answering machine version 0.0.1 " +
                "\n\n You can ask your question or add some new question and answer like:" +
                "\n What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\" " +
                "\n Let's start!\n";
        System.out.println(initialMessage);
    }

    public void processData() {
        printInitMessage();
        Scanner sn = new Scanner(System.in);
        while (true) {
            try {
                QuestionAnswer result = questionAnswerService.processAll(sn.nextLine());
                System.out.println(result.toString());
            } catch (RuntimeException r){
                System.out.println(r.getMessage());
            }
        }
    }


}
