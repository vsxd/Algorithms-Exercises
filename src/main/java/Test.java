import java.util.Arrays;
import java.util.Scanner;

public class Test {

}


class Main {
    public static void main(String[] args) {
        int[] input = new int[10];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < input.length; i++) {
            input[i] = sc.nextInt();
        }
        Arrays.toString(input);
    }
}