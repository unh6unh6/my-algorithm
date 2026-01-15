package prac.boj2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] map;
    static boolean[][] visit;

    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visit = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            char[] lines = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                map[i][j] = lines[j] - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] > 0 && !visit[i][j]) {
                    bfs(new Pos(i, j));
                }
            }
        }

        Collections.sort(result);
        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append("\n");
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i)).append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs(final Pos pos) {
        Queue<Pos> queue = new LinkedList<>();

        queue.offer(pos);
        int cnt = 1;
        visit[pos.y][pos.x] = true;

        while(!queue.isEmpty()) {
            Pos thisPos = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nextY = thisPos.y + offset[dir][0];
                int nextX = thisPos.x + offset[dir][1];
                if (nextY < 0 || nextY >= n || nextX < 0 || nextX >= n ||
                    map[nextY][nextX] < 1 || visit[nextY][nextX]) {
                    continue;
                }
                Pos nextPos = new Pos(nextY, nextX);
                queue.offer(nextPos);
                visit[nextY][nextX] = true;
                cnt ++;
            }
        }
        result.add(cnt);
    }

    private static int[][] offset = {
        {1, 0}, {0, 1}, {-1, 0}, {0, -1}
    };
}

class Pos {
    public Pos(
            final int y,
            final int x
    ) {
        this.y = y;
        this.x = x;
    }

    int y, x;
}
