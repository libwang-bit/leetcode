/**
 * CountOneSolution
 * 统计十进制数字 1-n 共 n 个数字里 1 出现的个数
 * @author likaiqiang@focusmedia.cn
 */
public class CountOneSolution {
    public static void main(String[] args) {
        int n = 10;
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += calculateOneCount(i);
        }
        System.out.println(sum);
    }

    private static int calculateOneCount(int n) {
        int count = 0;
        while (n > 0) {
            if (n % 10 == 1) {
                count++;
            }

            n = n / 10;
        }
        return count;
    }
}
