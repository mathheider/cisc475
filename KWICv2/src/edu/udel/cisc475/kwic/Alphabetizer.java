package edu.udel.cisc475.kwic;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;

public class Alphabetizer {

	private LineStorage ls;

	private Integer perm[];

	public Alphabetizer(LineStorage ls) {
		this.ls = ls;
	}

	public void alph() {
		int n = ls.numLines();
		perm = new Integer[n];
		for (int i = 0; i < n; i++)
			perm[i] = i;
		Comparator<Integer> comp = (Integer o1, Integer o2) -> ls
				.getLineAsString(o1).compareTo(ls.getLineAsString(o2));
		Arrays.sort(perm, comp);
	}

	public int ith(int i) {
		return perm[i];
	}

	public void print(PrintStream out) {
		out.println(Arrays.toString(perm));
	}
}
