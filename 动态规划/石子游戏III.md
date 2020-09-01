## 石子游戏III
[https://leetcode-cn.com/problems/stone-game-iii](https://leetcode-cn.com/problems/stone-game-iii)

### 问题描述
Alice 和 Bob 用几堆石子在做游戏。几堆石子排成一行，每堆石子都对应一个得分，由数组 stoneValue 给出。

Alice 和 Bob 轮流取石子，Alice 总是先开始。在每个玩家的回合中，该玩家可以拿走剩下石子中的的前 1、2 或 3 堆石子 。比赛一直持续到所有石头都被拿走。

每个玩家的最终得分为他所拿到的每堆石子的对应得分之和。每个玩家的初始分数都是 0 。比赛的目标是决出最高分，得分最高的选手将会赢得比赛，比赛也可能会出现平局。

假设 Alice 和 Bob 都采取 最优策略 。如果 Alice 赢了就返回 "Alice" ，Bob 赢了就返回 "Bob"，平局（分数相同）返回 "Tie" 。

 

示例 1：

输入：values = [1,2,3,7]
输出："Bob"
解释：Alice 总是会输，她的最佳选择是拿走前三堆，得分变成 6 。但是 Bob 的得分为 7，Bob 获胜。
### 动态规划
```
class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int len = stoneValue.length;
        
        int[] sum = new int[len]; 
        sum[len-1] = stoneValue[len-1];
        for(int i = len - 2; i >= 0; --i) {
            sum[i] = sum[i+1] + stoneValue[i];
        }

        // dp[i] 表示剩余[i……len-1] 能拿走的最多的数量。
        int[] dp = new int[len+1];
        for(int i = len - 1; i >= 0; --i) {
            int min = dp[i+1];
            for(int j = i + 2; j < (i + 4) && j <= len; j++) {
                min = Math.min(min, dp[j]);
            }
            dp[i] = sum[i] - min;
        }

        if (dp[0] * 2 == sum[0]) {
            return "Tie";
        }

        return dp[0] * 2 > sum[0] ? "Alice" : "Bob";
    }
}
```
