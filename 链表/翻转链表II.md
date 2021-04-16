## 翻转链表II
[https://leetcode-cn.com/problems/reverse-linked-list-ii](https://leetcode-cn.com/problems/reverse-linked-list-ii)
### 问题描述
给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
### 代码
```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode v = new ListNode(-1);
        v.next = head;

        ListNode lpre = v; // 指向翻转链表的前一个节点
        int i = 0;
        ListNode in = v;

        while(++i < left) {
            lpre = lpre.next;
        }
        ListNode rend = lpre.next; // 指向翻转链表的最后一个节点
        while(i++ < right) {
            rend = rend.next;
        }
        
        // 拆分链表
        ListNode new_reverse_head = lpre.next;
        lpre.next = rend.next;
        rend.next = null;
        ListNode nh = reverse(new_reverse_head);
        
        // 找到翻转链表的最后一个节点
        ListNode nend = nh;
        while(nend.next != null) {
            nend = nend.next;
        }
        
        // 连接
        nend.next = lpre.next;
        lpre.next = nh;
        return v.next;
    }

    private ListNode reverse(ListNode new_reverse_head) {
        ListNode pre = null;
        ListNode cur = new_reverse_head;
        ListNode t = cur;
        while(cur != null) {
            t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        return pre;
    }
}
```
