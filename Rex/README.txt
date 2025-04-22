
		     Rex: The Exam Randomizer

Rex is used to randomly shuffle the problems and answers of a
multiple choice exam.  The given exam is in LaTeX format and
consists of the following sections, in order:

 1. possible text not including "\begin{problem}" or "\end{problem}"
 2. a sequence of problems
 3. possible text not including "\begin{problem}" or "\end{problem}"

Each problem has the following form:

 1. \begin{problem}
 2. possible text not including "\begin{enumerate}"
 3. \begin{enumerate}
 4. a sequence of answers
 5. \end{enumerate}
 6. possible text
 7. \end{problem}

Each answer has the following form:
 1. \item
 2. text not including "\item" or "\end{enumerate}"

See exam1.tex for a typical example.

Rex is a command line tool which takes 2 arguments: the name of the
file containing the exam in the format described above, and a long
integer which is the seed to Java's random number generator.  It reads
the file, randomly permutes the problems and answers to each problem,
and then writes the output to stdout in the same format as the
original file.

Design: Rex was developed using a modular design.  The modules are:

  1. Input
  2. FindProblems
  3. FindAnswers
  4. RandomizeProblems
  5. RandomizeAnswers
  6. Output
  7. Rex (main class)

Each module is a Java class.  See the comments in the source files to
see what each module does.

Potential changes/additions: here are some ways the app may have to
change in the future:

  0. Better/more robust error reporting.

  1. Ignore text in LaTeX comments (comments start with % and extend
     to end of line)

  2. An exam may be divided into sections.  Randomize within each
     section but do not move a problem from one section to another.

  3. Provide a way for the user to indicate which answer is correct.
     Make Rex keep track of the correct answers as it permutes.
     Produce an answer key at the end, in either plain text or
     Scantron format.

  4. Allow the user to produce n random exams instead of just one.

  5. Tell Rex to select a random subset of problems, rather than
     all problems.  The subset must meet some user-specified
     constraints.  For example, the user might associate points to
     each problem and the constraint may be to select a set of
     problems for which the total number of points is as specified.

  6. Allow some "free form" (short essay) problems in addition to
     multiple choice.
 
To build from command line: type "make".  Type "make test1" to run a
test.  See the Makefile for further details.

This project is also configured as an Eclipse project.

Critique of Design v1
Looking at the original Rex design, its structure presents several different problems, which mirror what Parnas described as a "flowchart-based decomposition." Instead of organizing modules around hiding design decisions likely to change (like data structures or file formats), Rex  breaks the task down into sequential processing steps. As Parnas noted, "it is almost always incorrect to begin the decomposition of a system into modules on the basis of a flowchart." This critique applies directly here, as evidenced by modules passing large, exposed data structures between steps.
For instance, the Input module produces a raw char[] array, which is then directly used by FindProblems and FindAnswers to generate various integer index arrays (probStarts, probStops, answerStarts, answerStops). These low-level details are then passed along. The Output module exemplifies the resulting tight coupling; it requires intimate knowledge of all these internal data structures from Input, FindProblems, FindAnswers, RandomizeProblems, and RandomizeAnswers just to assemble the final document. This creates a web of dependencies, making the system difficult to modify.
Furthermore, knowledge about the specific input format (LaTeX tags like \begin{problem} or \item) is not contained within a single module. Instead, it's scattered, primarily across FindProblems and FindAnswers. Supporting a different input format would require hunting through multiple modules to make the necessary code changes.
A better approach, aligned with information hiding principles, would encapsulate design decisions. This would lead to a more flexible system, easier to maintain and adapt as requirements inevitably evolve over time.

1. ExamModel
    * Responsibilities: Represents the exam's structure abstractly, independent of file format. It holds components like introductory text, a list of problems, text between problems, and concluding text. Each problem contains its own text and a list of answers. It provides methods to access and reorder these components \
    * Secrets (Hidden Decisions): The specific internal data structures used to store the exam (e.g., using ArrayList<Problem>, defining Problem and Answer classes with String fields, how inter-problem text is linked). Hides how these components are connected and managed internally.
2. LatexExamParser
    * Responsibilities: Reads input (specifically in LaTeX format) and translates it into the abstract ExamModel structure. It identifies problems, answers, and other text segments based on LaTeX rules.
    * Secrets (Hidden Decisions): Hides the specific LaTeX tags (like \begin{problem}, \item) and the parsing logic required to interpret them.  The rest of the system doesn't know how the ExamModel was created from the LaTeX source.
3. ExamRandomizer
    * Responsibilities: Takes an ExamModel instance and modifies it by shuffling the order of problems and the order of answers within each problem, based on a given random seed or generator.
    * Secrets (Hidden Decisions): Hides the specific randomization algorithm (e.g., Fisher-Yates shuffle) and the details of how the random number generator is used. Other modules only know that the model gets randomized, but not how.
4. LatexExamWriter
    * Responsibilities: Takes an ExamModel instance (potentially randomized) and writes it out as a correctly formatted LaTeX document.
    * Secrets (Hidden Decisions): Hides the specific LaTeX commands and formatting rules needed to generate the output file from the abstract ExamModel. Hides the logic for assembling the text, problem, and answer components into the final LaTeX string or stream.
5. RexApp (Main application driver)
    * Responsibilities: This is the overall workflow. It handles command-line arguments, creates instances of the other modules, and calls them in the correct sequence.
    * Secrets (Hidden Decisions): Hides the specific order of operations and the logic between modules. Manages top-level concerns like argument parsing and stream handling.

USES Relation:
* RexApp USES LatexExamParser, ExamRandomizer, LatexExamWriter. (It also implicitly uses ExamModel as the data structure passed between other modules).   
* LatexExamParser USES ExamModel to create and populate it.   
* ExamRandomizer USES ExamModel to access structure and modify order   
* LatexExamWriter USES ExamModel to read structure for writing.   
* ExamModel is primarily used by other modules.

1. ExamModel
* Exported Methods:
    * public void setPreambleText(String preambleText)
    * public String getPreambleText()
    * public void addProblem(ExamModel.Problem problem)
    * public List<ExamModel.Problem> getProblems()
    * public void setProblemOrder(List<ExamModel.Problem> newOrder)
    * public void setEpilogueText(String epilogueText)
    * public String getEpilogueText()
2. LatexExamParser
* Exported Methods:
    * public ExamModel parse(InputStream inputStream)
3. ExamRandomizer
* Exported Methods:
    * public void randomizeExam(ExamModel model, long seed)
4. LatexExamWriter
* Exported Methods:
    * public void write(ExamModel model, OutputStream outputStream) 
Anticipation of Change

1. Add New Output Formats: not everyone does all their stuff in latex, maybe some in word,plain text etc.
Would need a new parser module (e.g., MarkdownExamParser) for v2. For v1 this would need significant rewrite as LaTeX is embedded and not easily changed.
2. Ignore text in LaTeX commentsThis change would be primarily localized within the LatexExamParser module.
In v2 the parsing logic would be updated to identify and discard comments before processing tags or storing text in the ExamModel
In v1 this would likely require changes in multiple modules. The Input module reads the raw character stream, but the logic for finding tags resides in FindProblems and FindAnswers etc. 
3. An exam may be divided into sections.  Randomize within each section but do not move a problem from one section to another
In v2, LatexExamParser would need updating to recognize section delimiters and structure ExamModel correctly. The ExamModel would need modification to store section information
In v1 This would require widespread changes. Just one example FindProblems would need new logic to identify section boundaries. Also, Randomize Problems and Output would need to be changed.