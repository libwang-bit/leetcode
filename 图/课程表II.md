## 课程表II
[https://leetcode-cn.com/problems/course-schedule-ii](https://leetcode-cn.com/problems/course-schedule-ii)

### 问题描述
现在你总共有 n 门课需要选，记为 0 到 n-1。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]

给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。

可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。

示例 1:

输入: 4, [[1,0],[2,0],[3,1],[3,2]]
输出: [0,1,2,3] or [0,2,1,3]
解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。

### 图的深度搜索 + 按照完成时间逆序输出拓扑排序图, 并判断是否有环

```
class Solution {
    boolean valid = true;
    int[] visit;
    List<List<Integer>> edages;
    int[] result;
    int index;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        edages = new ArrayList<>();
        for(int i = 0; i < numCourses; i++) {
            edages.add(new ArrayList<>());
        }
        visit = new int[numCourses];
        index = numCourses - 1;
        // 构造邻接表
        for(int[] info : prerequisites) {
            edages.get(info[1]).add(info[0]);
        }

        result = new int[numCourses];
        for(int i = 0; i < numCourses && valid; i++) {
            if (visit[i] == 0) {
                dfs(i);
            }
        }

        if (!valid) {
            return new int[0];
        }

        return result;
    }

    public void dfs(int u) {
        // 搜索中
        visit[u] = 1;
        for(Integer v : edages.get(u)) {
            // 未搜索
            if (visit[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visit[v] == 1){
                valid = false;
                return;
            }
        }
        // 搜索完成
        visit[u] = 2;
        result[index--] = u;
    }
}
```
