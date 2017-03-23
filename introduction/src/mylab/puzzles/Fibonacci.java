package mylab.puzzles;

/**
 * Fibonacci
 *
 * Fibonacci calculation using Map<Integer,Integer> as cache
 *
 * Indra Gunawan - March 23, 2017
 */

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    public int solve(int N) {
        Map<Integer,Integer> cache = new HashMap<>();
        cache.put(0,0);
        cache.put(1,1);
        return _solve(N, cache);
    }

    public int _solve(int N, Map<Integer,Integer> cache) {
        if (!cache.containsKey(N)) {
            int val = _solve(N-1, cache) + _solve(N - 2, cache);
            cache.put(N, val);
        }
        return cache.get(N);
    }

    public static void main(String args[]) throws Exception {
        Fibonacci problem = new Fibonacci();
        System.out.println(problem.solve(2));
        System.out.println(problem.solve(3));
        System.out.println(problem.solve(4));
    }

}
