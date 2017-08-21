package zhongqiu.common.test;

import java.util.*;

/**
 * Created by wangzhongqiu on 2017/8/12.
 */
public class test_two {
    public static void main(String[] args) {

    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length <= 0 || in.length <= 0) {
            return null;
        }
        return ConstructCore(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public TreeNode ConstructCore(int[] preOrder, int startPreIndex, int endPreIndex,
                                  int[] inOrder, int startInIndex, int endInIndex) {
        int rootValue = preOrder[startPreIndex];
        TreeNode root = new TreeNode(rootValue);
        // 只有一个元素
        if (startPreIndex == endPreIndex) {
            if (startInIndex == endInIndex && preOrder[startPreIndex] == inOrder[startInIndex]) {
                return root;
            }
        }
        // 在中序遍历中找到根结点的索引
        int rootInIndex = startInIndex;
        while (rootInIndex <= endInIndex && inOrder[rootInIndex] != rootValue) {
            rootInIndex++;
        }
        int leftLength = rootInIndex - startInIndex;
        int leftPreOrderEndIndex = startPreIndex + leftLength;
        if (leftLength > 0) {
            // 构建左子树
            root.left = ConstructCore(preOrder, startPreIndex + 1,
                    leftPreOrderEndIndex, inOrder, startInIndex,
                    rootInIndex - 1);
        }
        if (leftLength < endPreIndex - startPreIndex) {
            // 右子树有元素,构建右子树
            root.right = ConstructCore(preOrder, leftPreOrderEndIndex + 1,
                    endPreIndex, inOrder, rootInIndex + 1, endInIndex);
        }
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

