package boj15663;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] numbers;
    static boolean[] visit;
    static int[] seq;
    static List<int[]> seqList = new ArrayList<>();
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
            if (checkDup()) {
                return;
            }

            seqList.add(Arrays.copyOf(seq, m));
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

    private static boolean checkDup() {
        for (int[] compSeq : seqList) {
            boolean flag = true;
            for (int i = 0; i < m; i++) {
                if (seq[i] != compSeq[i]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }
}
