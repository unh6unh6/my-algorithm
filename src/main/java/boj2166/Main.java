package boj2166;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, s;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        numbers = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int sum = 0;

        int result = Integer.MAX_VALUE;
        while (end < n + 1) {
            int length = end - start;
            if (sum >= s) {
                result = Math.min(result, length);
            }

            if (sum > s && start < end) {
                sum -= numbers[start++];
            } else {
                sum += numbers[end++];
            }
        }

        if (result == Integer.MAX_VALUE) {
            result = 0;
        }
        System.out.println(result);
    }
}
