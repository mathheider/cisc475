// File: src/edu/udel/cisc675/rex/v2/LatexExamParser.java
package edu.udel.cisc675.rex.v2; 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Parses a LaTeX input stream into an ExamModel.
 */
public class LatexExamParser {

    // LaTeX tags
    private static final String BEGIN_PROBLEM = "\\begin{problem}";
    private static final String END_PROBLEM = "\\end{problem}";
    private static final String BEGIN_ENUMERATE = "\\begin{enumerate}";
    private static final String END_ENUMERATE = "\\end{enumerate}";
    private static final String ITEM = "\\item";

    public ExamModel parse(InputStream inputStream) throws IOException {
        ExamModel model = new ExamModel();

        List<String> lines = new BufferedReader(new InputStreamReader(inputStream))
                                 .lines().collect(Collectors.toList());
        String fullContent = String.join("\n", lines); 

        // 2. Find Preamble
        int firstProblemStart = fullContent.indexOf(BEGIN_PROBLEM);
        if (firstProblemStart == -1) {
            model.setPreambleText(fullContent.trim());
            return model;
        }
        model.setPreambleText(fullContent.substring(0, firstProblemStart).trim());

        // 3. Find Problems and Epilogue
        int currentPos = firstProblemStart;
        while (currentPos < fullContent.length() && currentPos != -1) {
            int problemStart = fullContent.indexOf(BEGIN_PROBLEM, currentPos);
            if (problemStart == -1) {
                 model.setEpilogueText(fullContent.substring(currentPos).trim());
                 break;
            }

             int problemEnd = fullContent.indexOf(END_PROBLEM, problemStart);
             if (problemEnd == -1) {
                 System.err.println("Error: Found " + BEGIN_PROBLEM + " without matching " + END_PROBLEM);
                 model.setEpilogueText(fullContent.substring(problemStart).trim());
                 break;
             }
             problemEnd += END_PROBLEM.length();

             String problemText = fullContent.substring(problemStart, problemEnd);
             List<ExamModel.Answer> answers = parseAnswersFromProblemText(problemText);
             model.addProblem(new ExamModel.Problem(problemText, answers));
             currentPos = problemEnd;

              int nextProblemStart = fullContent.indexOf(BEGIN_PROBLEM, currentPos);
              if (nextProblemStart == -1) {
                  model.setEpilogueText(fullContent.substring(currentPos).trim());
                  break;
              }
        }
        return model;
    }

    /** Helper to parse answers  */
    private List<ExamModel.Answer> parseAnswersFromProblemText(String problemText) {
        List<ExamModel.Answer> answers = new ArrayList<>();
        int enumerateStart = problemText.indexOf(BEGIN_ENUMERATE);
        int enumerateEnd = problemText.indexOf(END_ENUMERATE);

        if (enumerateStart == -1 || enumerateEnd == -1 || enumerateEnd < enumerateStart) {
            return answers; 
        }

        String enumerateContent = problemText.substring(enumerateStart + BEGIN_ENUMERATE.length(), enumerateEnd);
        int currentItemPos = 0;
        while (currentItemPos < enumerateContent.length()) {
            int itemStart = enumerateContent.indexOf(ITEM, currentItemPos);
            if (itemStart == -1) break; // No more items

            int nextItemStart = enumerateContent.indexOf(ITEM, itemStart + ITEM.length());
            int answerEnd = (nextItemStart != -1) ? nextItemStart : enumerateContent.length();

            String answerText = enumerateContent.substring(itemStart, answerEnd).trim();
             if (!answerText.isEmpty()) {
                 answers.add(new ExamModel.Answer(answerText));
             }
            currentItemPos = answerEnd;
        }
        return answers;
    }
}