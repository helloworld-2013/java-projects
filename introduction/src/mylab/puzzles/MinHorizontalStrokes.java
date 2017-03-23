package mylab.puzzles;

/**
 * Minimum Horizontal Strokes
 *
 * Sample input:
 * A[] = new int[]{1,1,1,1}
 * Output:
 * 1
 *
 * Sample input:
 * A[] = new int[]{1,3,2,1,2,1,5,3,3,4,2}
 * Output:
 * 9
 *
 * If strokes > 1,000,000,000 then return -1
 *
 * http://stackoverflow.com/questions/33399211/how-to-find-least-strokes-required-to-paint-a-skyline-with-time-and-storage-comp
 */

public class MinHorizontalStrokes {

    public int solve(int A[]) {
        int level = 0;
        int strokes = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] > level) {
                strokes += A[i] - level;
                level = A[i];
            } else if (A[i] < level) {
                level = A[i];
            }

            if (strokes > 1000000000)
                return -1;
        }
        return strokes;
    }

    public static void main(String[] args) {
        MinHorizontalStrokes problem = new MinHorizontalStrokes();
        System.out.println(problem.solve(new int[]{1, 1, 1, 1}));
        System.out.println(problem.solve(new int[]{1, 3, 2, 1, 2, 1, 5, 3, 3, 4, 2}));
    }

}
