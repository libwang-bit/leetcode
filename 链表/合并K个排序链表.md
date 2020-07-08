## 合并K个排序链表
[https://leetcode-cn.com/problems/merge-k-sorted-lists](https://leetcode-cn.com/problems/merge-k-sorted-lists)

合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

示例:

输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6

===使用优先级队列，将k个链表的node放进去比较。每次pop出val最小的node，如果node.next != null ， 将node.next 加入队列. 直到队列为空,返回结果.===

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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(Comparator.comparing(o -> o.val));

        ListNode head = new ListNode(0);
        ListNode cur = head;

        for(ListNode list : lists) {
            if (list != null) {
                pq.add(list);
            }
        }

        while(pq.size() > 0) {
            ListNode node = pq.poll();
            cur.next = node;
            if (node.next != null) {
                pq.add(node.next);
            }
            cur = cur.next;
        }

        return head.next;
    }
}
```
