package io.example.am.domain;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;


public class QuestionAnswer {

    @Size(max = 255, message = "Question max length must be 255 chars")
    private String question;
    private List<@Size(max = 255, message = "Every answer max length must be 255 chars") String> answers;

    public QuestionAnswer(String question, List<String> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(question + "?" + "\nAnswer will be \n");
        answers.forEach(s -> result.append("\t\t" + s + "\n"));
        return result.toString();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionAnswer that = (QuestionAnswer) o;
        return Objects.equals(question, that.question) &&
                Objects.equals(answers, that.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answers);
    }
}
