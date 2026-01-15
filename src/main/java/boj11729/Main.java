package boj11729;

import java.util.Scanner;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int counter = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        recursion(1, 3, n);
        System.out.println(counter);
        System.out.println(sb);
    }

    static void recursion(int a, int b, int n) {
        counter++;
        if (n == 1) {
            sb.append(a).append(" ").append(b).append("\n");
            return;
        }
        recursion(a, 6-a-b, n-1);
        sb.append(a).append(" ").append(b).append("\n");
        recursion(6-a-b, b, n-1);
    }
}
