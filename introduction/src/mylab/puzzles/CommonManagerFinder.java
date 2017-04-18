package mylab.puzzles;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Find the common manager
 *
 * Best description can be found here : https://gist.github.com/thoroc/75a8555fca480345c5c4#file-question2-md
 *
 * Indra Gunawan - April 18, 2017
 */

public class CommonManagerFinder {

    class Node {
        String data;
        Node left, right;
    }

    Node root = null;

    public void add(String manager, String subordinate) {
        Node subNode = new Node();
        subNode.data = subordinate;
        if (root == null) {
            root = new Node();
            root.data = manager;
            root.left = subNode;
        } else {
            Node mgrNode = get(root, manager);
            if (mgrNode.left == null) {
                mgrNode.left = subNode;
            } else {
                mgrNode.right = subNode;
            }
        }
    }

    private Node get(Node n, String data) {
        if (n == null) return null;
        if (n.data.equals(data)) return n;

        Node left = get(n.left, data);
        Node right = get(n.right, data);

        return (left != null)?left : right;
    }

    public String query(String data1, String data2) {
        Node n = lca(root,data1,data2);
        return n.data;
    }

    private Node lca(Node n,String data1, String data2) {
        if (n == null) return null;
        if (n.data.equals(data1) || n.data.equals(data2)) return n;

        Node left = lca(n.left, data1, data2);
        Node right = lca(n.right, data1, data2);

        if (left != null && right != null) return n;

        return (left != null) ? left : right;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        String q1 = in.next();
        String q2 = in.next();

        Set<String> names = new HashSet<>();
        CommonManagerFinder lca = new CommonManagerFinder();
        while (names.size() < N) {
            String manager = in.next();
            String subordinate = in.next();

            names.add(manager);
            names.add(subordinate);

            lca.add(manager, subordinate);
        }
        System.out.println(lca.query(q1,q2));
    }

}


