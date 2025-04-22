// File: src/edu/udel/cisc675/rex/v2/ExamModel.java
package edu.udel.cisc675.rex.v2; 

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the structure of an exam abstractly.
 * Hides the internal data structures used to store components.
 */
public class ExamModel {

    public static class Answer {
        private String fullAnswerText;

        public Answer(String fullAnswerText) {
            this.fullAnswerText = fullAnswerText;
        }

        public String getFullAnswerText() {
            return fullAnswerText;
        }
    }

    public static class Problem {
        private String fullProblemText; 
        private List<Answer> answers;   

        public Problem(String fullProblemText, List<Answer> answers) {
            this.fullProblemText = fullProblemText;
            this.answers = answers != null ? new ArrayList<>(answers) : new ArrayList<>();
        }

        public String getFullProblemText() {
            return fullProblemText;
        }

        public List<Answer> getAnswers() {
            return answers;
        }

         public void setAnswerOrder(List<Answer> newOrder) {
             if (newOrder != null && newOrder.size() == this.answers.size()) {
                 this.answers = new ArrayList<>(newOrder); // using a copy
             } 
        }
    }

    // --- ExamModel Fields ---

    private String preambleText = ""; 
    private List<Problem> problems;
    private String epilogueText = "";
    // --- ExamModel Methods ---

    public ExamModel() {
        this.problems = new ArrayList<>();
    }

    public void setPreambleText(String preambleText) {
        this.preambleText = preambleText;
    }

    public String getPreambleText() {
        return preambleText;
    }

    public void addProblem(Problem problem) {
        this.problems.add(problem);
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblemOrder(List<Problem> newOrder) {
        if (newOrder != null && newOrder.size() == this.problems.size()) {
            this.problems = new ArrayList<>(newOrder);
        }     }

    public void setEpilogueText(String epilogueText) {
        this.epilogueText = epilogueText;
    }

    public String getEpilogueText() {
        return epilogueText;
    }
}