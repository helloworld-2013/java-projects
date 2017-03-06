package mylab.puzzles;

/**
 * Common Prime Divisors Puzzle
 *
 * Best description can be found here : https://codility.com/programmers/lessons/12-euclidean_algorithm/common_prime_divisors
 *
 * Indra Gunawan - March 6, 2017
 */

import java.util.Arrays;

public class CommonPrimeDivisors {

    public int solve(int A[], int B[]) {
        int count = 0;
        for (int i = 0;i < A.length;i++) {
            int a = A[i];
            int b = B[i];

            int c = gcd(a,b);
            int _c = 0;
            while (_c != 1) {
                _c = gcd(a,c);
                a /= _c;
            }

            _c = 0;
            while (_c != 1) {
                _c = gcd(b,c);
                b /= _c;
            }

            if (a == 1 && b == 1) {
                count++;
            }
        }
        return count;
    }

    private int gcd(int A, int B) {
        if (A % B == 0){
            return B;
        } else {
            return gcd(B, A % B);
        }
    }

    public static void main(String[] args) {
        CommonPrimeDivisors problem = new CommonPrimeDivisors();
        System.out.println(problem.solve(new int[]{15, 10, 3}, new int[]{75, 30, 5}));
    }

}
