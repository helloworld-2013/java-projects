package mylab.puzzles;

/**
 * Triangle Puzzle
 *
 * Best description can be found here : https://codility.com/programmers/lessons/6-sorting/triangle
 *
 * Indra Gunawan - March 6, 2017
 */

import java.util.Arrays;

public class Triangle {

		public int solve(int A[]) {
				if (A.length < 3) return 0;
				Arrays.sort(A);
				for (int i = 0; i<A.length - 2;i++) {
						if (A[i] + A[i+1] > A[i+2]) {
								return 1;
						}
				}
				return 0;
		}

		public static void main(String[] args) throws Exception {
				Triangle problem = new Triangle();
				System.out.println(problem.solve(new int[] {10, 2, 5, 1, 8, 20}));
				System.out.println(problem.solve(new int[] {10, 50, 5, 1}));
		}

}
