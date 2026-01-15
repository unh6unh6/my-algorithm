package boj15654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] numbers;
    static boolean[] visit;
    static int[] seq;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        numbers = new int[n];
        visit = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);

        seq = new int[m];
        recursion(0);
        System.out.println(sb);
    }

    private static void recursion(int cnt) {
        if (cnt == m) {
            for (int i = 0; i < m; i++) {
                sb.append(seq[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visit[i]) {
                continue;
            }
            int number = numbers[i];
            seq[cnt] = number;
            visit[i] = true;
            recursion(cnt + 1);
            visit[i] = false;
        }
    }
}
