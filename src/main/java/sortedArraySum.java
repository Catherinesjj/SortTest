package com.core;


public class sortedArraySum {
	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 5, 7, 8, 9, 9 };
		arraySum(a, 10);

	}

	public static void arraySum(int[] a, final int sum) {
		for (int i = 0; i <= a.length / 2 - 1; i++) {
			for (int j = a.length - 1; j > a.length / 2 - 1; j--) {
				if (a[i] + a[j] == sum) {
					System.out.print("[" + a[i] + "," + a[j] + "]");
				}
			}
		}
	}

}
