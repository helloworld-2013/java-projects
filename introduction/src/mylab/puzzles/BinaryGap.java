package mylab.puzzles;

/**
 * Binary Gap Puzzle
 *
 * Best description can be found here : https://codility.com/programmers/lessons/1-iterations/binary_gap
 *
 * Indra Gunawan - March 3, 2017
 */

import java.util.stream.Stream;

public class BinaryGap {

    public int solve(int N) {
        return Stream.of(Integer.toBinaryString(N)
                .replaceAll("0+$", "") // to remove trailing 0 if occurs at the end
                .split("1+")) // to split based on 1 or trailing 1
                .filter(a -> a != null)
                .max((a, b) -> Integer.compare(a.length(), b.length()))
                .map(String::length)
                .orElse(0);
    }

    public static void main(String args[]) throws Exception {
        BinaryGap problem = new BinaryGap();
        System.out.println(problem.solve(9));
    }
}
