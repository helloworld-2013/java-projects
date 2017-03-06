package mylab.puzzles;

/**
 * MinMaxDivision Puzzle
 *
 * Best description can be found here : https://codility.com/programmers/lessons/14-binary_search_algorithm/min_max_division
 *
 * Indra Gunawan - March 6, 2017
 */

import java.util.stream.IntStream;

public class MinMaxDivision {

  	public int solve(int K, int M, int A[]) {
    		int resLowerBound = IntStream.of(A).max().orElse(0);
    		int resUpperBound = IntStream.of(A).sum();

    		if (K == 1) return resUpperBound;
    		if (K >= A.length) return resLowerBound;

    		while (resLowerBound < resUpperBound) {
      			int middle = (resLowerBound + resUpperBound) / 2;
      			int sum = 0, count = 0;
      			for (int i = 0;(i < A.length) && (count < K);i++) {
        				sum += A[i];
        				if (sum > middle) {
          					count++;
          					sum = A[i];
        				}
      			}

      			if (count == K) {
      				  resLowerBound = middle + 1;
      			} else {
        				resUpperBound = middle;
      			}
    		}

    		return resUpperBound;
  	}

  	public static void main(String[] args) {
    		MinMaxDivision problem = new MinMaxDivision();
    		System.out.println(problem.solve(3, 5, new int[]{ 2,1,5,1,2,2,2 }));
  	}

}
