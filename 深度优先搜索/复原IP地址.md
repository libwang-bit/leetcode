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
        if (s == null || s.length() == 0) {
            return result;
        }

        dfs(s, "", 0, 4, result);
        return result;
    }

    private void dfs(String s, String temp, int index, int cnt, List<String> result) {
        if(cnt == 0) {
            if (index == s.length()) {
                result.add(temp.substring(0, temp.length() - 1));
                return;
            }
            return;
        }

        for (int i = index; i < index + 3 && i < s.length(); i++) {
            String subStr = s.substring(index, i + 1);
            int num = Integer.parseInt(subStr);
            if (num > 255) continue;
            dfs(s, temp + s.substring(index, i + 1) + ".", i + 1, cnt - 1, result);
            if (s.charAt(index) == '0') break;
        }
    }
}
```
