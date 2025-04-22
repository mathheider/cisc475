// File: src/edu/udel/cisc675/rex/v2/RexApp.java
package edu.udel.cisc675.rex.v2; 

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Main  for Rex v2
 */
public class RexApp {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("error");
            System.exit(1);
        }

        String filename = args[0];
        long seed = 0;
        try {
            if (args[1].toLowerCase().startsWith("0x")) {
                 seed = Long.parseLong(args[1].substring(2), 16);
            } else {
                 seed = Long.parseLong(args[1]);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: seed error).");
            System.exit(1);
        }

        // uses modules
        LatexExamParser parser = new LatexExamParser();
        ExamRandomizer randomizer = new ExamRandomizer();
        LatexExamWriter writer = new LatexExamWriter();

        try (InputStream inputStream = new FileInputStream(filename);
             OutputStream outputStream = System.out) {

            // 1. Parse 
            ExamModel exam = parser.parse(inputStream);

            // 2. Randomize 
            randomizer.randomizeExam(exam, seed);

            // 3. Write
            writer.write(exam, outputStream);

              } catch (Exception e) {
             System.err.println("An unexpected error occurred: " + e.getMessage());
             e.printStackTrace();
             System.exit(1);
        }
    }
}