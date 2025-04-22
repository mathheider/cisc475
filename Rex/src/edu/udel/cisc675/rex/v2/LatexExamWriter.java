// File: src/edu/udel/cisc675/rex/v2/LatexExamWriter.java
package edu.udel.cisc675.rex.v2; 

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Writes an ExamModel to an output stream in LaTeX format.
 * Reconstructs problem text based on randomized answers.
 */
public class LatexExamWriter {

    private static final String BEGIN_ENUMERATE = "\\begin{enumerate}";
    private static final String END_ENUMERATE = "\\end{enumerate}";
    private static final Pattern ENUMERATE_PATTERN = Pattern.compile(
        Pattern.quote(BEGIN_ENUMERATE) + "(.*?)" + Pattern.quote(END_ENUMERATE),
        Pattern.DOTALL);

    public void write(ExamModel model, OutputStream outputStream) throws IOException {
        try (PrintWriter writer = new PrintWriter(outputStream)) {

            // Preamble
            if (model.getPreambleText() != null && !model.getPreambleText().isEmpty()) {
                writer.println(model.getPreambleText());
                writer.println();
            }

            // Problems
            boolean firstProblem = true;
            for (ExamModel.Problem problem : model.getProblems()) {
                 if (!firstProblem) writer.println();
                 firstProblem = false;

                 String originalProblemText = problem.getFullProblemText();
                 List<ExamModel.Answer> randomizedAnswers = problem.getAnswers();

                 Matcher matcher = ENUMERATE_PATTERN.matcher(originalProblemText);
                 if (matcher.find()) {
                     String beforeEnumerate = originalProblemText.substring(0, matcher.start());
                     String afterEnumerate = originalProblemText.substring(matcher.end());

                     writer.print(beforeEnumerate);
                     writer.print(BEGIN_ENUMERATE);
                     if (!beforeEnumerate.endsWith("\n") && !BEGIN_ENUMERATE.startsWith("\n")) {
                         writer.println();
                     }

                     for (ExamModel.Answer answer : randomizedAnswers) {
                         String answerText = answer.getFullAnswerText().trim();
                         if (!answerText.startsWith("\\item")) {
                             writer.print("\\item ");
                         }
                         writer.println(answerText);
                     }

                     if (!randomizedAnswers.isEmpty() && !randomizedAnswers.get(randomizedAnswers.size()-1).getFullAnswerText().endsWith("\n")) {
                          writer.println();
                     }
                     writer.print(END_ENUMERATE);
                     writer.print(afterEnumerate);
                 } else {
                     System.err.println("cant find enumerate block.");
                     writer.print(originalProblemText); 
                 }
            }

            // Epilogue
            if (model.getEpilogueText() != null && !model.getEpilogueText().isEmpty()) {
                 writer.println();
                writer.print(model.getEpilogueText());
            }
            writer.flush();
        }
    }
}