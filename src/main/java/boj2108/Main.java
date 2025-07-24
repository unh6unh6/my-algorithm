package boj2108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] cnt = new int[8002];
        Arrays.fill(cnt, 0);

        int sum = 0;
        int manyNumber = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(br.readLine());
            sum += number;
            cnt[number + 4000]++;
            if (cnt[manyNumber + 4000] < cnt[number + 4000]) {
                manyNumber = number;
            }
            max = Math.max(max, number);
            min = Math.min(min, number);
        }
        int range = max - min;

        int center = -1;
        int index = 0;
        for (int i = min + 4000; i <= max + 4000; i++) {
            if (cnt[i] > 0) {
                index += cnt[i];
            }
            if (index >= n / 2 + 1) {
                center = i - 4000;
                break;
            }
        }

        boolean flag = false;
        for (int i = min + 4000; i <= max + 4000; i++) {
            if (cnt[i] == cnt[manyNumber + 4000]) {
                if (!flag) {
                    flag = true;
                    continue;
                }
                manyNumber = i - 4000;
                break;
            }
        }

        double avgDouble = (double) sum / n;
        int avg = (int) Math.round(avgDouble);
        StringBuilder sb = new StringBuilder();
        sb.append(avg).append("\n")
                .append(center).append("\n")
                .append(manyNumber).append("\n")
                .append(range).append("\n");

        System.out.println(sb);
    }
}
