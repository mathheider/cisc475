package edu.udel.cisc475.debug;

/**
 * Command Line Utility. Try: java CLU sort -1 -2 -3 java CLU sort -1 -2 -3 0
 */
public class CLU {

	/**
	 * Sorts the slice of the array a that starts at index start and has length
	 * size. Uses shell sort.
	 */
	private static void shellSort(int[] a, int start, int size) {
		int i, j, h = 1;
		do {
			h = h * 3 + 1;
		} while (h <= size);
		do {
			h /= 3;
			for (i = h; i < size; i++) {
				int v = a[start + i];
				for (j = i; j >= h && a[start + j - h] > v; j -= h)
					a[start + j] = a[start + j - h];
				if (i != j)
					a[start + j] = v;
			}
		} while (h != 1);
	}

	/**
	 * Command line arguments: sort <list of int>.
	 */
	public static void main(String[] args) {
		int[] a;
		int i;
		int n = args.length;
		a = new int[n];
		for (i = 0; i < n - 1; i++)
			a[i] = Integer.valueOf(args[i + 1]);
		if (args[0].equals("sort")) {
			shellSort(a, 0, n);
			for (i = 0; i < n - 1; i++) {
				if (i > 0)
					System.out.print(" ");
				System.out.print(a[i]);
			}
			System.out.println();
		} else {
			System.out.println("Unrecognized command");
			System.exit(1);
		}
	}
}
