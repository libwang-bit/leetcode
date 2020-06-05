## 删除单向链表的倒数第 n 个节点
[https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/)

```
描述：
给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：

给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
说明：

给定的 n 保证是有效的。

进阶：你能尝试使用一趟扫描实现吗？

```


Java 代码:
```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode newHead = new ListNode(0); 
        newHead.next = head;
        ListNode p1 = newHead;
        ListNode p2 = newHead;

        for (int i = 1; i <= n+1; i++) {
            if (p2 == null) { // 防止实际上删除的是第一个节点
                return newHead.next;
            } else {
                p2 = p2.next;
            }
        }

        while (p2 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }

        p1.next = p1.next.next;
        return newHead.next;
    }
}
```
