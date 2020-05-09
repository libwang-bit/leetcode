/**
 * MaximalSquare
 *
 * @author likaiqiang@focusmedia.cn
 */
public class MaximalSquare {
    public static int maximalSquare(char[][] matrix) {
        int maxSuare = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSuare;
        }
        int row = matrix.length;
        int column = matrix[0].length;
        int bianchang = 0;
        int [][] dp = new int [row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp [i-1][j - 1]) + 1;
                    }
                    bianchang = Math.max(bianchang, dp[i][j]);
                }
            }
        }
        return bianchang * bianchang;
    }

//    输入:
//
//            1 0 1 0 0
//            1 0 1 1 1
//            1 1 1 1 1
//            1 0 0 1 0
//
//    输出: 4
    public static void main(String[] args) {
        char[][] matrix = new char[][] {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        System.out.println("maximal square is " + maximalSquare(matrix));
    }
}
