package edu.udel.cisc475.debug;

public class BubbleSorter {

	void bubbleSort(int arr[]) {
		int n = arr.length, i = 0, j = 0;
		while (i < n - 1) {
			j = 0;
			while (j < n - i - 1) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					j++;
				}
			}
			i++;
		}
	}

	public static void main(String args[]) {
		BubbleSorter bs = new BubbleSorter();
		int a[] = {64, 34, 25, 12};
		bs.bubbleSort(a);
		int n = a.length;
		for (int i = 0; i < n; ++i) {
			if (i > 0)
				System.out.print(" ");
			System.out.print(a[i]);
		}
		System.out.println();
	}
}
