package boj14712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int result = 0; // 격자판에 넴모가 하나도 없는 경우 넣고 시작
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        getInput();
        recursion(0, 0);
        System.out.println(result);
    }

    // 같은 경우의수를 여러번 탐색하는 문제 해결하면댐
    private static void recursion(
            int cnt,
            int start
    ) {
        if (cnt <= n * m) {
            if (ensure()) {
                result++;
            }
            if (cnt == n * m) {
                return;
            }
        }

        for (int i = start; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!map[r][c]) {
                map[r][c] = true;
                recursion(cnt + 1, i + 1);
                map[r][c] = false;
            }
        }
    }

    private static boolean ensure() {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m - 1; j++) {
                if (!map[i][j]) {
                    continue;
                }
                if (map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new boolean[n][m];
    }
}
