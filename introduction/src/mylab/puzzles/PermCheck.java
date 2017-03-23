package mylab.puzzles;

/**
 * PermCheck Puzzle
 *
 * Best description can be found here : https://codility.com/programmers/lessons/4-counting_elements/perm_check
 * 
 * Indra Gunawan - March 6, 2017
 */

import java.util.HashSet;
import java.util.Set;

public class PermCheck {

    public int solve(int A[]) {
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            s.add(A[i]);
        }
        if (s.size() != A.length || A.length == 1) {
            return 0;
        }
        for (int i = 0; i < A.length; i++) {
            if (!s.contains(i + 1)) {
                return 0;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        PermCheck problem = new PermCheck();
        System.out.println(problem.solve(new int[]{4, 1, 3, 2}));
        System.out.println(problem.solve(new int[]{4, 1, 3}));
        System.out.println(problem.solve(new int[]{1, 3}));
    }

}
