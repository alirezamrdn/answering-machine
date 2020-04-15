package io.example.am.service.impl;

import io.example.am.domain.QuestionAnswer;
import io.example.am.service.QuestionAnswerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class QuestionAnswerServiceTest {

    static QuestionAnswerService questionAnswerService;

    @BeforeAll
    public static void setUp(){
        questionAnswerService= new QuestionAnswerServiceImpl(new HashMap<>());
    }

    private List<QuestionAnswer> getExpectedList(){
        return Arrays.asList(new QuestionAnswer("What is Peters favorite food",
                Arrays.asList("Pizza","Spaghetti","Ice cream")),
                new QuestionAnswer("How old is Peters",
                        Collections.singletonList("The answer to life, universe and everything is 42")),
                new QuestionAnswer("What is Peters favorite food",
                        Arrays.asList("Pizza","Spaghetti","Ice cream")));
    }

    private List<String> getActualList(){
        return Arrays.asList("What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"",
                "How old is Peters?",
                "What is Peters favorite food?",
                "What is Peters favorite foodWhat is Peters favorite foodWhat is Peters " +
                        "favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite " +
                        "foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat " +
                        "is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat " +
                        "is Peters favorite foodWhat is Peters favorite foodWhat is Peters favorite foodWhat is " +
                        "Peters favorite foodWhat is Peters favorite food",
                "What is Alice favorite food? \"PizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizza" +
                        "PizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizza" +
                        "PizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizza" +
                        "PizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizza" +
                        "PizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizza" +
                        "PizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizzaPizza" +
                        "PizzaPizzaPizza\" \"Spaghetti\" \"Ice cream\"",
                "What is Alice favorite food? \"Pizza\" \"Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti " +
                        "Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti " +
                        "Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti" +
                        "Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti " +
                        "Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti " +
                        "Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti " +
                        "Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti " +
                        "Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti Spaghetti \" \"Ice cream\"");
    }

    @Test
    public void testProcessQuestionAnswers() {
        QuestionAnswer actual = questionAnswerService.processAll(getActualList().get(0));
        assertEquals(getExpectedList().get(0),actual);

        actual = questionAnswerService.processAll(getActualList().get(1));
        assertEquals(getExpectedList().get(1),actual);

        actual = questionAnswerService.processAll(getActualList().get(2));
        assertEquals(getExpectedList().get(2),actual);

        assertThrows(RuntimeException.class, () -> {
            questionAnswerService.processAll(getActualList().get(3));
        });

        assertThrows(RuntimeException.class, () -> {
            questionAnswerService.processAll(getActualList().get(4));
        });
        assertThrows(RuntimeException.class, () -> {
            questionAnswerService.processAll(getActualList().get(5));
        });
    }

}
