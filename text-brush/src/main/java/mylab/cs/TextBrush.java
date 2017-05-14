package mylab.cs;

import java.util.Scanner;
import java.util.Stack;

public class TextBrush {

    private static final char LEFT_RIGHT = '|';
    private static final char TOP_BOTTOM = '-';
    private static final char LINE = 'x';
    private static final char BLANK = ' ';

    private int width, height;
    private char screen[][];

    private void init(int width, int height) {
        this.width = width;
        this.height = height;
        screen = new char[height+2][width+2];
        for (int i = 0;i < height+2;i++) {
            for (int j = 0;j < width+2;j++) {
                if (i == 0 || i == (height + 2 - 1)) {
                    screen[i][j] = TOP_BOTTOM;
                } else if (j == 0 || j == (width + 2 - 1)) {
                    screen[i][j] = LEFT_RIGHT;
                } else {
                    screen[i][j] = BLANK;
                }
            }
        }
    }

    private void line(int x1, int y1, int x2, int y2) {
        for (int i = y1;i <= y2;i++) {
            for (int j = x1;j <= x2;j++) {
                screen[i][j] = LINE;
            }
        }
    }

    private void rect(int x1, int y1, int x2, int y2) {
        for (int i = y1;i <= y2;i++) {
            for (int j = x1;j <= x2;j++) {
                if (i == y1 || i == y2) {
                    screen[i][j] = LINE;
                } else if (j == x1 || j == x2) {
                    screen[i][j] = LINE;
                }
            }
        }
    }

    private boolean valid(int x, int y) {
        return (x > 0) && (y > 0) && (x <= width) && (y <= height) && (screen[y][x] == BLANK);
    }

    private void fill(int x, int y, String c) {
        Stack<Integer> xStack = new Stack<>();
        Stack<Integer> yStack = new Stack<>();

        xStack.push(x);
        yStack.push(y);

        while (!yStack.isEmpty()) {
            int bucketX = xStack.pop();
            int bucketY = yStack.pop();

            screen[bucketY][bucketX] = c.charAt(0);

            // NORTH
            int tmpX = bucketX;
            int tmpY = bucketY - 1;
            if (valid(tmpX, tmpY)) {
                xStack.push(tmpX);
                yStack.push(tmpY);
            }

            //SOUTH
            tmpX = bucketX;
            tmpY = bucketY + 1;
            if (valid(tmpX, tmpY)) {
                xStack.push(tmpX);
                yStack.push(tmpY);
            }

            //EAST
            tmpX = bucketX + 1;
            tmpY = bucketY;
            if (valid(tmpX, tmpY)) {
                xStack.push(tmpX);
                yStack.push(tmpY);
            }

            //WEST
            tmpX = bucketX - 1;
            tmpY = bucketY;
            if (valid(tmpX, tmpY)) {
                xStack.push(tmpX);
                yStack.push(tmpY);
            }
        }
    }

    private StringBuilder draw() {
        StringBuilder str = new StringBuilder();

        for (int i = 0;i < height+2;i++) {
            for (int j = 0;j < width+2;j++) {
                str.append(screen[i][j]);
            }
            str.append(System.getProperty("line.separator"));
        }

        return str;
    }

    public StringBuilder execute(String cmd) {
        String params[];

        if (cmd.startsWith("C")) {
            params = cmd.split(" ");
            init(Integer.valueOf(params[1]), Integer.valueOf(params[2]));
        } else if (cmd.startsWith("L")) {
            params = cmd.split(" ");
            line(Integer.valueOf(params[1]), Integer.valueOf(params[2]), Integer.valueOf(params[3]), Integer.valueOf(params[4]));
        } else if (cmd.startsWith("R")) {
            params = cmd.split(" ");
            rect(Integer.valueOf(params[1]), Integer.valueOf(params[2]), Integer.valueOf(params[3]), Integer.valueOf(params[4]));
        } else if (cmd.startsWith("B")) {
            params = cmd.split(" ");
            fill(Integer.valueOf(params[1]), Integer.valueOf(params[2]), params[3]);
        }

        return draw();
    }

    public static void main(String args[]) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("enter command: ");
        String cmd = input.nextLine();

        TextBrush cli = new TextBrush();
        while (!"Q".equals(cmd)) {
            StringBuilder str = cli.execute(cmd);
            System.out.print(str.toString());

            System.out.print("enter command: ");
            cmd = input.nextLine();
        }
    }

}
