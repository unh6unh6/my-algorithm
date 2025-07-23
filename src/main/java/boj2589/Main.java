package boj2589;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static boolean[][] map;
    static int maxShortDistance = 0;

    public static void main(String[] args) throws IOException {
        getInput();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j]) {
                    bfs(i, j);
                }
            }
        }
        System.out.println(maxShortDistance);
    }

    private static void bfs(
            int y,
            int x
    ) {
        int maxDistance = 0;
        boolean[][] visit = new boolean[n][m];
        visit[y][x] = true;
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(new Pos(x, y));
        while (!queue.isEmpty()) {
            Pos thisPos = queue.poll();
            maxDistance = Math.max(maxDistance, thisPos.getDistance());
            for (int dir = 0; dir < 4; dir++) {
                int nextY = thisPos.y + offset[dir][0];
                int nextX = thisPos.x + offset[dir][1];
                if (nextY >= 0 && nextY <  n && nextX >= 0 && nextX < m &&
                    map[nextY][nextX] && !visit[nextY][nextX]
                ) {
                    visit[nextY][nextX] = true;
                    Pos nextPos = new Pos(nextX, nextY);
                    nextPos.setDistance(thisPos.getDistance() + 1);
                    queue.offer(nextPos);
                }
            }
        }

        maxShortDistance = Math.max(maxDistance, maxShortDistance);
    }


    private static int[][] offset = {
            {0, 1}, {1, 0}, {-1, 0}, {0, -1}
    };

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            final String[] split = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                String s = split[j];
                if (s.equals("L")) {
                    map[i][j] = true;
                }
            }
        }
    }
}

class Pos {

    int x;
    int y;
    int distance = 0;

    public Pos(
            final int x,
            final int y
    ) {
        this.x = x;
        this.y = y;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(final int distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Pos pos = (Pos) o;
        return x == pos.x && y == pos.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Pos{" +
               "x=" + x +
               ", y=" + y +
               '}';
    }
}
