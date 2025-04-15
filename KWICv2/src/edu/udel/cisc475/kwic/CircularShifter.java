package edu.udel.cisc475.kwic;

public class CircularShifter {

	private LineStorage lsin;

	public CircularShifter(LineStorage lsin) {
		this.lsin = lsin;
	}

	public LineStorage csSetup() {
		LineStorage lsout = new CommonLineStorage();
		int nline = lsin.numLines();
		for (int i = 0; i < nline; i++) {
			int nword = lsin.numWordsInLine(i);
			for (int j = 0; j < nword; j++) {
				int lineout = lsout.addLine();
				for (int k = 0; k < nword; k++)
					lsout.addWord(lineout, lsin.getWord(i, (j + k) % nword));
			}
		}
		return lsout;
	}
}
