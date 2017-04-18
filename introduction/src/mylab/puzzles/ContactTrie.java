package mylab.puzzles;

import java.util.Scanner;

/**
 * Contact Puzzle
 *
 * Best description can be found here : https://www.hackerrank.com/challenges/contacts
 *
 * Indra Gunawan - April 8, 2017
 */

public class ContactTrie {

    class Node {
        private static final int NUMBER_OF_CHARACTERS = 26;
        Node[] slots = new Node[NUMBER_OF_CHARACTERS];
        int size = 0;

        private int getIndex(char c) {
            return c - 'a';
        }

        public void add(String s, int level) {
            size++;
            if (level == s.length()) return;
            int idx = getIndex(s.charAt(level));
            if (slots[idx] == null) {
                slots[idx] = new Node();
            }
            slots[idx].add(s,level + 1);
        }

        public int count(String s, int level) {
            if (level == s.length()) return size;
            Node child = slots[getIndex(s.charAt(level))];
            if (child == null) return 0;
            return child.count(s,level+1);
        }
    }

	public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();

        ContactTrie problem = new ContactTrie();
        ContactTrie.Node node = problem.new Node();
        for (int i = 0;i < N;i++) {
            String opr = in.next();
            if ("".equals(opr)) break;
            String str = in.next();

            if ("add".equals(opr)) {
                node.add(str,0);
            } else if ("find".equals(opr)) {
                System.out.println(node.count(str,0));
            }
        }
	}

}