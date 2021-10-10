package leetcode;


import algs4.In;

import java.util.*;
import java.util.LinkedList;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Tree {
}


class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        while (!stack.isEmpty() || root != null) {
            pushForwardLeft(root, stack);
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    private void pushForwardLeft(TreeNode node, Stack<TreeNode> s) {
        while (node != null) {
            s.push(node);
            node = node.left;
        }
    }
}


class levelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            List<Integer> level = new LinkedList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                level.add(node.val);
                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
            }
            res.add(level);
        }
        return res;
    }

    public List<List<Integer>> levelOrder_2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        retrieve(root, 0, res);
        return res;
    }

    private void retrieve(TreeNode node, int depth, List<List<Integer>> res) {
        if (node == null) return;
        if (depth == res.size()) res.add(new ArrayList<>());
        res.get(depth).add(node.val);
        retrieve(node.left, depth + 1, res);
        retrieve(node.right, depth + 1, res);
    }
}

class zigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean changeOrder = false;

        while (!q.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (changeOrder)
                    level.addFirst(node.val);
                else
                    level.addLast(node.val);
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
            }
            res.add(level);
            changeOrder = !changeOrder;
        }
        return res;
    }
}

class lowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val == p.val || root.val == q.val) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) return root;
        if (left == null) return right;
        if (right == null) return left;
        return null;
    }
}


class generateTrees {
    public List<TreeNode> generateTrees(int n) {
        if (n < 1) return new LinkedList<>();
        return generate(1, n);
    }

    private List<TreeNode> generate(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftSubTrees = generate(start, i - 1);
            List<TreeNode> rightSubTrees = generate(i + 1, end);

            for (TreeNode left : leftSubTrees) {
                for (TreeNode right : rightSubTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    allTrees.add(root);
                }
            }
        }

        return allTrees;
    }
}

class BSTIterator {
    private Stack<TreeNode> s;
    private TreeNode curr;

    public BSTIterator(TreeNode root) {
        s = new Stack<>();
        curr = root;
    }

    public int next() {
        while (curr != null) {
            s.push(curr);
            curr = curr.left;
        }
        curr = s.pop();
        int val = curr.val;
        curr = curr.right;
        return val;
    }

    public boolean hasNext() {
        return curr != null || !s.isEmpty();
    }
}

class isValidBST {
    private int pre = Integer.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        if (root.val <= pre) return false;
        pre = root.val;
        return isValidBST(root.right);
    }

}


class isSymmetric {
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            list.clear();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                list.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }

            int i = 0;
            int j = list.size() - 1;
            while (i < j) {
                if (list.get(i++) != list.get(j--)) return false;
            }
        }
        return true;
    }
}

class rightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        q.addLast(root);
        List<Integer> res = new ArrayList<>();

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.pollFirst();
                if (i == size - 1) res.add(node.val);
                if (node.left != null) q.addLast(node.left);
                if (node.right != null) q.addLast(node.right);
            }
        }
        return res;
    }
}

class connect {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) return root;
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (i < size - 1) node.next = queue.peek();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return root;
    }

    public Node connect2(Node root) {
        if (root == null) return root;

        if (root.left != null) {
            root.left.next = root.right;
            root.right.next = root.next != null ? root.next.left : null;
            connect(root.left);
            connect(root.right);
        }
        return root;
    }
}

class hasPathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == targetSum;
        return hasPathSum(root.left, targetSum - root.val)
                || hasPathSum(root.right, targetSum - root.val);
    }
}

class sumNumbers {
    public int sumNumbers(TreeNode root) {
        return retrieve(root, 0);
    }

    private int retrieve(TreeNode node, int ans) {
        if (node == null) return 0;
        ans = ans * 10 + node.val;
        if (node.left == null && node.right == null)
            return ans;
        return retrieve(node.left, ans) + retrieve(node.right, ans);
    }
}

class longestUnivaluePath {
    int ans;

    public int longestUnivaluePath(TreeNode root) {
        ans = 0;
        arrowLength(root);
        return ans;
    }

    public int arrowLength(TreeNode node) {
        if (node == null) return 0;
        int left = arrowLength(node.left);
        int right = arrowLength(node.right);
        int arrowLeft = 0, arrowRight = 0;
        if (node.left != null && node.left.val == node.val) {
            arrowLeft += left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            arrowRight += right + 1;
        }
        ans = Math.max(ans, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }
}

class maxPathSum {
    public int maxPathSum(TreeNode root) {
        ans = Integer.MIN_VALUE;
        retrieve(root);
        return ans;
    }

    private int ans;

    private int retrieve(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, retrieve(node.left));
        int right = Math.max(0, retrieve(node.right));
        int sum = left + right + node.val;
        ans = Math.max(ans, sum);
        return Math.max(left, right) + node.val;
    }
}