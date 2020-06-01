## N叉树的最大深度
(https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree)

描述:
```
给定一个 N 叉树，找到其最大深度。

最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。

例如，给定一个 3叉树 :
        1
    3   2    4
  5   6
我们应返回其最大深度，3。

说明:

树的深度不会超过 1000。
树的节点总不会超过 5000。

```

Java 代码:

利用队列层序遍历
```
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public int maxDepth(Node root) {
        int result = 0;
        if (root == null) {
            return result;
        }

        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while(q.size() != 0) {
            result ++;
            int size = q.size();
            for(int i = 0; i < size; i++) {
                Node t = q.poll();
                for (Node p : t.children) {
                    if (p != null) {
                        q.offer(p);
                    }
                }
            }
        }
        return result;
    }
}
```

递归求解:
递归公式
当前节点为空 , return 0;
当前节点的孩子为空, return 1;
return 孩子中的最大深度 + 1;

```
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public int maxDepth(Node root) {
        int result = 0;
        if (root == null) {
            return 0;
        } else if(root.children == null) {
            return 1;
        } else {
            for (Node t : root.children) {
                result = Math.max(result, maxDepth(t));
            }
        }
        return result + 1;

    }
}
```
