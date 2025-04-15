# Makefile for Rex.  Type "make" to compile all Java classes.
# Type "make test1" to run a test involving exam1.tex.  Ditto
# for test2, test3.

SRCFILES = Rex.java Input.java FindProblems.java FindAnswers.java \
  RandomizeProblems.java RandomizeAnswers.java Output.java
SRC = $(addprefix src/edu/udel/cisc675/rex/,$(SRCFILES))
MAINBIN = bin/edu/udel/cisc675/rex/Rex.class

all: $(MAINBIN)

$(MAINBIN): $(SRC)
	javac -d bin $(SRC)

test1: $(MAINBIN)
	java -cp bin edu.udel.cisc675.rex.Rex examples/exam1.tex 1

test2: $(MAINBIN)
	java -cp bin edu.udel.cisc675.rex.Rex examples/exam2.tex 2

test3: $(MAINBIN)
	java -cp bin edu.udel.cisc675.rex.Rex examples/exam3.tex 3


clean:
	rm -rf bin/* *~ *.tmp

.PHONY: clean all
