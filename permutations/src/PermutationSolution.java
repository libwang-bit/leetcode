/**
 * PermutationSolution
 * 打印全排列
 * @author likaiqiang@focusmedia.cn
 */
public class PermutationSolution {
    public static void main(String[] args) {
        int[] elements = new int[] {1, 2, 3};
        int n = elements.length;
        int[] indexes = new int[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = 0;
        }

        printArray(elements);

        int i = 0;
        while (i < n) {
            if (indexes[i] < i) {
                swap(elements, i % 2 == 0 ? 0 : indexes[i], i);
                printArray(elements);
                indexes[i]++;
                i = 0;
            } else {
                indexes[i] = 0;
                i++;
            }
        }
    }

    private static void printArray(int[] input) {
        System.out.print('\n');
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i]);
        }
    }

    private static void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }
}
