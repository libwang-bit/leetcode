## 二叉搜索树的第k大节点
[https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof](https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof)

### 问题描述
给定一棵二叉搜索树，请找出其中第k大的节点。
```
示例 1:
输入: root = [5,3,6,2,4,null,null,1], k = 3

       5
      / \
     3   6
    / \
   2   4
  /
 1
输出: 4
```

### 利用栈实现 右-中-左的遍历顺序. k递减
两点需要注意：
1. java语言中尽量不适用stack类，性能很差。可以用LinkedList 代替.
addFirst -> push
removeFirst -> pop
2. 类比于树的中序遍历，按照 右-中-左的顺序遍历.
3. 
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int kthLargest(TreeNode root, int k) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        while(root != null || !queue.isEmpty()) {
            if (root != null) {
                queue.addFirst(root);
                root = root.right;
            } else {
                TreeNode node = queue.removeFirst();
                if (--k == 0) {
                    return node.val;
                }
                root = node.left;
            }
        }
        return -1;
    }
}
```
