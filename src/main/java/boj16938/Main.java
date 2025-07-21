package boj16938;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n; // 문제수
    static long l; // 최소 문제 난이도 합
    static long r; // 최대 문제 난이도 합
    static int x; // 최소 (가장 어려운 문제 난이도) - (가장 쉬운 문제 난이도)
    static int[] a; // 모든 문제 난이도
    static boolean[] visit;

    static int result = 0;

    // 12:50 ~
    public static void main(String[] args) throws IOException {
        getInput();

        Arrays.sort(a);
        for (int maximumLength = 2; maximumLength <= n; maximumLength++) {
            recursion(maximumLength, 0, 0, 0, 0, -1);
        }

        System.out.println(result);
    }

    private static void recursion(
            final int maxLength,
            final int thisLength,
            final int minProb,
            final int maxProb,
            final int sumProb,
            final int beforeIndex
    ) {
        if (thisLength == maxLength) {
            // 현재까지의 집합 검증

            if (sumProb < l ||
                sumProb > r ||
                maxProb - minProb < x
            ) {
                return;
            }

            result++;
            return;
        }
        for (int i = beforeIndex + 1; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;

                int tempMinProb = minProb;
                if (minProb == 0) {
                    tempMinProb = a[i];
                }
                recursion(
                        maxLength,
                        thisLength + 1,
                        tempMinProb,
                        a[i],
                        sumProb + a[i],
                        i
                );

                visit[i] = false;
            }
        }
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        a = new int[n];
        visit = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
    }
}
