## 复原IP地址
[https://leetcode-cn.com/problems/restore-ip-addresses](https://leetcode-cn.com/problems/restore-ip-addresses)
描述:
```
给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
示例:
输入: "25525511135"
输出: ["255.255.11.135", "255.255.111.35"]
```
Java 代码:
```
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 4) {
            return result;
        }

        dfs(s, "", 0, 4, result);
        return result;
    }

    private void dfs(String origin, String dest, int index, int cnt, List<String> result) {
        if (cnt == 0) {
            if (origin.length() == index) {
                result.add(dest.substring(0, dest.length() - 1));
                return;
            } else {
                return;
            }
        }

        for (int i = 1; i < 4 && index + i <= origin.length(); i++) {
            String temp = origin.substring(index, index + i); // 截取从index下标开始, 长度为i（ 1<=i<= 3）的子串.
            // 如果超过 255, 不满足条件
            if (Integer.valueOf(temp) > 255) {
                continue;
            }
            // 保存子串, 下标加i, 次数消耗一次
            dfs(origin, dest + temp + ".", index + i, cnt - 1, result);
            // 0打头的只能出现在第一个ip段内. 其他段要终止.
            if (origin.charAt(index) == '0') {
                break;
            }
        }

    }
}
```
