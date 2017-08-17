package zhongqiu.common.base.algorithm.programming;

import java.util.*;

/**
 * Created by wangzhongqiu on 2017/7/11.
 * 阿里2018校招编程题。
 */
/*现有一大数据处理平台可以启动一系列并发的作业，每个作业中存在一系列存在父子关系的任务。每个任务用一个三元组表示--（任务id，父任务id，执行开销），其中任务id是一个正整数（>0）；父任务id为0表示根任务，每个作业存在一个唯一的根任务，并且，所有的任务，如果其父任务id不为0，那么必然是一个已经存在的根任务id；执行开销是一个正整数（>0）。系统中至少存在一个作业。
        举例如下：
        (1, 0, 2) (2, 0, 3) (3, 1, 2) (4, 1, 3)
        对应的任务关系是：
        1(2)          2(3)
        /   \
        3(2)   4(3)
        注：括号内的数字表示任务开销。
        很容易看出来，这些任务树构成了一个森林。每颗任务树有若干个叶子节点，从根节点到叶子结点的路径上所有的执行开销，称作叶子节点对应任务的总开销。例如上面一共有3个叶子节点任务，分别是任务3, 4和2，对应的总开销分别是4, 5和3。总开销最大的叶子节点任务是4，对应的总开销是5。
        现在需要编写程序，针对输入的任务数据，找到总开销最大的叶子节点任务对应的总开销，比如针对上面例子，输出5。*/
public class Tree {
    public static void main(String[] args) {

        ArrayList<Integer> _ids = new ArrayList<Integer>();
        ArrayList<Integer> _parents = new ArrayList<Integer>();
        ArrayList<Integer> _costs = new ArrayList<Integer>();

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (line != null && !line.isEmpty()) {
            if (line.trim().equals("0")) break;
            String[] values = line.trim().split(" ");
            if (values.length != 3) {
                break;
            }
            _ids.add(Integer.parseInt(values[0]));
            _parents.add(Integer.parseInt(values[1]));
            _costs.add(Integer.parseInt(values[2]));
            line = in.nextLine();
        }
        int res = resolve(_ids, _parents, _costs);

        System.out.println(String.valueOf(res));
    }

    public static int resolve(ArrayList<Integer> ids, ArrayList<Integer> parents, ArrayList<Integer> costs) {
        TreeNode root = creatTree(ids, parents, costs);
        IteratorTree(root, 0);

        //获取ArrayList中最大值
        int max = pathValueList.get(0);
        for (int i = 1; i < pathValueList.size(); i++) {
            if (pathValueList.get(i) > max) {
                max = pathValueList.get(i);
            }
        }
        return max;
    }

    //构建一棵多叉树
    public static TreeNode creatTree(ArrayList<Integer> ids, ArrayList<Integer> parents, ArrayList<Integer> costs) {
        //创建根节点
        TreeNode root = new TreeNode();
        Map<Integer, TreeNode> maps = new HashMap<Integer, TreeNode>();
        //遍历所有结点信息，将所有结点相关信息放入maps中
        for (int i = 0; i < ids.size(); i++) {
            TreeNode node = new TreeNode();
            node.setId(ids.get(i));
            node.setParentId(parents.get(i));
            node.setCost(costs.get(i));
            maps.put(ids.get(i), node);
        }
        //遍历map，将父结点为0的结点放到根结点下，否则在相应父节点下添加子结点
        for (Map.Entry<Integer, TreeNode> entry : maps.entrySet()) {
            TreeNode node = entry.getValue();
            Integer partentId = node.getParentId();
            if (partentId == 0) {
                root.getChilds().put(node.getId(), node);//node.getId()等价于node.getKey()
            } else {
                TreeNode pNode = maps.get(partentId);
                pNode.getChilds().put(node.getId(), node);
            }
        }
        return root;
    }

    //遍历多叉树，寻找最优解
    private static List<Integer> pathValueList = new ArrayList<Integer>();

    public static void IteratorTree(TreeNode root, int sum) {

        int tmp = sum;
        if (root != null) {
            for (Map.Entry<Integer, TreeNode> entry : root.getChilds().entrySet()) {

                sum = tmp;
                sum += entry.getValue().getCost();

                if (entry.getValue().getChilds() != null && entry.getValue().getChilds().size() > 0) {
                    IteratorTree(entry.getValue(), sum);
                } else {
                    pathValueList.add(sum);//遍历完一条路径，将这条路径和记录下来
                }
            }
        }
    }
}

class TreeNode {
    private Integer id;
    private Integer parentId;
    private int cost;
    private Map<Integer, TreeNode> childs = new HashMap<Integer, TreeNode>();//存放所有子结点 <子结点Id，子结点>

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Map<Integer, TreeNode> getChilds() {
        return childs;
    }

    public void setChilds(Map<Integer, TreeNode> childs) {
        this.childs = childs;
    }
}
