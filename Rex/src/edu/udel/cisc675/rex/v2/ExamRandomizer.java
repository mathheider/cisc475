// File: src/edu/udel/cisc675/rex/v2/ExamRandomizer.java
package edu.udel.cisc675.rex.v2; 

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

/**
 * Randomizes the order of problems and answers within an ExamModel.
 * Hides the randomization algorithm (Fisher-Yates).
 */
public class ExamRandomizer {

    public void randomizeExam(ExamModel model, long seed) {
        RandomGenerator rand = new Random(seed);
        randomizeProblems(model, rand);
        randomizeAnswers(model, rand);
    }

    private void randomizeProblems(ExamModel model, RandomGenerator rand) {
        List<ExamModel.Problem> problems = model.getProblems();
        int n = problems.size();
        for (int i = n - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Collections.swap(problems, i, j);
        }
    }

    private void randomizeAnswers(ExamModel model, RandomGenerator rand) {
        for (ExamModel.Problem problem : model.getProblems()) {
            List<ExamModel.Answer> answers = problem.getAnswers();
            int n = answers.size();
            for (int i = n - 1; i > 0; i--) {
                int j = rand.nextInt(i + 1);
                 Collections.swap(answers, i, j);
            }
        }
    }
}