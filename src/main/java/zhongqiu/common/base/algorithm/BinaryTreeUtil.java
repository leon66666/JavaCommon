package zhongqiu.common.base.algorithm;

import java.util.Stack;

/**
 * Created by wangzhongqiu on 2017/8/21.
 */
public class BinaryTreeUtil {
    public static void main(String[] args) {
//        BinaryTree G = new BinaryTree(null, null, 'G');
//        BinaryTree H = new BinaryTree(null, null, 'H');
//        BinaryTree F = new BinaryTree(G, H, 'F');
//        BinaryTree D = new BinaryTree(null, F, 'D');
//        BinaryTree E = new BinaryTree(null, null, 'E');
//        BinaryTree B = new BinaryTree(D, E, 'B');
//        BinaryTree C = new BinaryTree(null, null, 'C');
//        BinaryTree A = new BinaryTree(B, C, 'A');
//        System.out.println("先序遍历。。。。。。。");
//        pre(A);
//        System.out.println();
//        System.out.println("中序遍历。。。。。。。");
//        in(A);
//        System.out.println();
//        System.out.println("后序遍历。。。。。。。");
//        post(A);
//        System.out.println();
//        System.out.println("非递归先序遍历。。。。。。。");
//        preTraverse(A);
//        System.out.println();
//        System.out.println("非递归中序遍历。。。。。。。");
//        inTraverse(A);
//        System.out.println();
//        System.out.println("非递归后序遍历。。。。。。。");
//        postTraverse(A);
//        System.out.println();
//        System.out.println("非递归后序遍历2。。。。。。。");
//        postTraverse2(A);
//        System.out.println();

        int[] preOrder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inOrder = {4, 7, 2, 1, 5, 3, 8, 6};
        int[] postOrder = {7, 4, 2, 5, 8, 6, 3, 1};
        post(ConstructByPreAndIn(preOrder, inOrder));
        System.out.println();
        pre(ConstructByInAndPost(inOrder, postOrder));
    }

    //递归先序遍历
    public static void pre(BinaryTree root) {
        if (root == null) return;
        visit(root);
        if (root.getLchild() != null) pre(root.getLchild());
        if (root.getRchild() != null) pre(root.getRchild());
    }

    //递归中序遍历
    public static void in(BinaryTree root) {
        if (root == null) return;
        if (root.getLchild() != null) in(root.getLchild());
        visit(root);
        if (root.getRchild() != null) in(root.getRchild());
    }

    //递归后序遍历
    public static void post(BinaryTree root) {
        if (root == null) return;
        if (root.getLchild() != null) post(root.getLchild());
        if (root.getRchild() != null) post(root.getRchild());
        visit(root);
    }

    //非递归先序遍历
    public static void preTraverse(BinaryTree root) {
        Stack s = new Stack();
        s.push(root);
        while (!s.isEmpty()) {
            BinaryTree bt = (BinaryTree) s.pop();
            visit(bt);
            if (bt.getRchild() != null) s.push(bt.getRchild());
            if (bt.getLchild() != null) s.push(bt.getLchild());
        }
    }

    //非递归中序遍历
    public static void inTraverse(BinaryTree root) {
        Stack s = new Stack();
        BinaryTree p = root;
        while (p != null || !s.isEmpty()) {
            if (p != null) {
                s.push(p);
                p = p.getLchild();
            } else {
                p = (BinaryTree) s.pop();
                visit(p);
                p = p.getRchild();
            }
        }
    }

    //非递归后序遍历
    public static void postTraverse(BinaryTree root) {
        Stack s = new Stack();
        BinaryTree p = root;
        //pre标记最近出栈的节点，用于判断是否是p节点的右孩子，如果是的话，就可以访问p节点
        BinaryTree pre = p;
        //flag标记是出栈还是继续将左孩子进栈
        boolean flag = true;
        while (p != null || !s.isEmpty()) {
            if (p != null && flag) {
                s.push(p);
                p = p.getLchild();
            } else {
                if (s.isEmpty()) return;
                p = (BinaryTree) s.peek();
                if (p.getRchild() != null && p.getRchild() != pre) {
                    p = p.getRchild();
                    flag = true;
                } else {
                    p = (BinaryTree) s.pop();
                    visit(p);
                    flag = false;
                    pre = p;
                }
            }
        }
    }

    //非递归后序遍历2
    public static void postTraverse2(BinaryTree root) {
        Stack s = new Stack();
        BinaryTree p = root;
        //pre标记最近出栈的节点，用于判断是否是p节点的右孩子，如果是的话，就可以访问p节点
        BinaryTree pre = p;
        while (p != null || !s.isEmpty()) {
            if (p != null) {
                s.push(p);
                p = p.getLchild();
            } else {
                if (s.isEmpty()) return;
                p = (BinaryTree) s.peek();
                if (p.getRchild() != null && p.getRchild() != pre) {
                    p = p.getRchild();
                } else {
                    p = (BinaryTree) s.pop();
                    visit(p);
                    pre = p;
                    p = null;
                }
            }
        }
    }

    /**
     * @param preOrder 前序遍历数组
     * @param inOrder  中序遍历数组
     * @return 根结点
     */
    public static BinaryTree ConstructByPreAndIn(int[] preOrder, int[] inOrder) {
        if (preOrder == null || inOrder == null || preOrder.length <= 0 || inOrder.length <= 0) {
            return null;
        }
        try {
            return ConstructByPreAndInCore(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1);
        } catch (InvalidPutException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param inOrder   中序遍历数组
     * @param postOrder 后序遍历数组
     * @return 根结点
     */
    public static BinaryTree ConstructByInAndPost(int[] inOrder, int[] postOrder) {
        if (inOrder == null || postOrder == null || inOrder.length <= 0 || postOrder.length <= 0) {
            return null;
        }
        try {
            return ConstructByInAndPostCore(inOrder, 0, inOrder.length - 1, postOrder, 0, postOrder.length - 1);
        } catch (InvalidPutException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param preOrder      前序遍历序列
     * @param startPreIndex 前序序列开始位置
     * @param endPreIndex   前序序列结束位置
     * @param inOrder       中序遍历序列
     * @param startInIndex  中序序列开始位置
     * @param endInIndex    中序序列结束位置
     * @return 根结点
     * @throws InvalidPutException
     */
    public static BinaryTree ConstructByPreAndInCore(int[] preOrder, int startPreIndex, int endPreIndex,
                                                     int[] inOrder, int startInIndex, int endInIndex) throws InvalidPutException {
        int rootValue = preOrder[startPreIndex];
        BinaryTree root = new BinaryTree(rootValue);
        // 只有一个元素
        if (startPreIndex == endPreIndex) {
            if (startInIndex == endInIndex && preOrder[startPreIndex] == inOrder[startInIndex]) {
                return root;
            } else {
                throw new InvalidPutException();
            }
        }
        // 在中序遍历中找到根结点的索引
        int rootInIndex = startInIndex;
        while (rootInIndex <= endInIndex && inOrder[rootInIndex] != rootValue) {
            rootInIndex++;
        }
        if (rootInIndex == endInIndex && inOrder[rootInIndex] != rootValue) {
            throw new InvalidPutException();
        }
        int leftLength = rootInIndex - startInIndex;
        int leftPreOrderEndIndex = startPreIndex + leftLength;
        if (leftLength > 0) {
            // 构建左子树
            root.setLchild(ConstructByPreAndInCore(preOrder, startPreIndex + 1,
                    leftPreOrderEndIndex, inOrder, startInIndex,
                    rootInIndex - 1));
        }
        if (leftLength < endPreIndex - startPreIndex) {
            // 右子树有元素,构建右子树
            root.setRchild(ConstructByPreAndInCore(preOrder, leftPreOrderEndIndex + 1,
                    endPreIndex, inOrder, rootInIndex + 1, endInIndex));
        }
        return root;
    }

    /**
     * @param inOrder        中序遍历序列
     * @param startInIndex   中序序列开始位置
     * @param endInIndex     中序序列结束位置
     * @param postOrder      后序遍历序列
     * @param startPostIndex 后序序列开始位置
     * @param endPostIndex   后序序列结束位置
     * @return 根结点
     * @throws InvalidPutException
     */
    public static BinaryTree ConstructByInAndPostCore(int[] inOrder, int startInIndex, int endInIndex, int[] postOrder, int startPostIndex, int endPostIndex) throws InvalidPutException {
        int rootValue = postOrder[endPostIndex];
        BinaryTree root = new BinaryTree(rootValue);
        // 只有一个元素
        if (startPostIndex == endPostIndex) {
            if (startInIndex == endInIndex && postOrder[endPostIndex] == inOrder[startInIndex]) {
                return root;
            } else {
                throw new InvalidPutException();
            }
        }
        // 在中序遍历中找到根结点的索引
        int rootInIndex = startInIndex;
        while (rootInIndex <= endInIndex && inOrder[rootInIndex] != rootValue) {
            rootInIndex++;
        }
        if (rootInIndex == endInIndex && inOrder[rootInIndex] != rootValue) {
            throw new InvalidPutException();
        }
        int leftLength = rootInIndex - startInIndex;
        if (leftLength > 0) {
            // 构建左子树
            root.setLchild(ConstructByInAndPostCore(inOrder, startInIndex,
                    rootInIndex - 1, postOrder, startPostIndex,
                    startPostIndex + leftLength - 1));
        }
        if (leftLength < endPostIndex - startPostIndex) {
            // 右子树有元素,构建右子树
            root.setRchild(ConstructByInAndPostCore(inOrder, rootInIndex + 1,
                    endInIndex, postOrder, startPostIndex + leftLength, endPostIndex - 1));
        }
        return root;
    }

    static class InvalidPutException extends Exception {
        private static final long serialVersionUID = 1L;
    }

    public static void visit(BinaryTree bt) {
        System.out.print(bt.getData() + " ");
    }

    static class BinaryTree {
        private BinaryTree lchild;
        private BinaryTree rchild;
        private Object data;

        public BinaryTree getLchild() {
            return lchild;
        }

        public void setLchild(BinaryTree lchild) {
            this.lchild = lchild;
        }

        public BinaryTree getRchild() {
            return rchild;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public void setRchild(BinaryTree rchild) {
            this.rchild = rchild;
        }

        public BinaryTree(BinaryTree l, BinaryTree r, Object data) {
            lchild = l;
            rchild = r;
            this.data = data;
        }

        public BinaryTree(Object data) {
            lchild = null;
            rchild = null;
            this.data = data;
        }
    }
}


