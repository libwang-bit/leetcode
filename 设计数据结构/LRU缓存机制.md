## LRU 缓存机制
[https://leetcode-cn.com/problems/lru-cache](https://leetcode-cn.com/problems/lru-cache)

描述:
```
运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。

进阶:
你是否可以在 O(1) 时间复杂度内完成这两种操作？

示例:

LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // 返回  1
cache.put(3, 3);    // 该操作会使得关键字 2 作废
cache.get(2);       // 返回 -1 (未找到)
cache.put(4, 4);    // 该操作会使得关键字 1 作废
cache.get(1);       // 返回 -1 (未找到)
cache.get(3);       // 返回  3
cache.get(4);       // 返回  4
```

Java 代码:

```
class LRUCache {

    class Node {
        int key;
        int value;
        Node next;
        Node pre;
        Node() {}
        Node(int key, int val) {
            this.value = val;
            this.key = key;
        }
    }

    Node head;
    Node tail;
    int size;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        size = capacity;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.pre.next = node.next;
            node.next.pre = node.pre;

            node.next = head.next;
            head.next.pre = node;
            head.next = node;
            node.pre = head;
            return node.value;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;

            node.pre.next = node.next;
            node.next.pre = node.pre;

            node.next = head.next;
            head.next.pre = node;
            head.next = node;
            node.pre = head;
        } else {
            if (map.size() == size) {
                Node del = tail.pre;
                del.pre.next = del.next;
                del.next.pre = del.pre;
                
                map.remove(del.key);
                del = null;
            }

            Node node = new Node(key, value);

            node.next = head.next;
            head.next.pre = node;
            head.next = node;
            node.pre = head;
            map.put(key, node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```
