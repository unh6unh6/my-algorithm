package boj2589;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static boolean[][] map;
    static int maxShortDistance = 0;

    public static void main(String[] args) throws IOException {
        getInput();
        processAllPair(0, 0, new ArrayList<>(2));
        System.out.println(maxShortDistance);
    }

    private static void processAllPair(
            int cnt,
            int start,
            List<Pos> posList
    ) {
        if (cnt == 2) {
            calculateShortDistance(posList.get(0), posList.get(1));
            return;
        }

        for (int i = start; i < n * m; i++) {
            int y = i / m;
            int x = i % m;
            if (map[y][x]) {
                posList.add(new Pos(x, y));
                processAllPair(cnt + 1, i + 1, posList);
                posList.remove(posList.size() - 1);
            }
        }
    }

    private static void calculateShortDistance(
            Pos pos1,
            Pos pos2
    ) {
        boolean[][] visit = new boolean[n][m];
        visit[pos1.y][pos1.x] = true;
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(pos1);
        while (!queue.isEmpty()) {
            Pos thisPos = queue.poll();
            if (thisPos.equals(pos2)) {
                maxShortDistance = Math.max(maxShortDistance, thisPos.getDistance());
                break;
            }

            for (int dir = 0; dir < 4; dir++) {
                int newX = thisPos.x + offset[dir][0];
                int newY = thisPos.y + offset[dir][1];
                if (newY < n && newY >= 0 &&
                    newX < m && newX >=0 &&
                    map[newY][newX] &&
                    !visit[newY][newX]
                ) {
                    visit[newY][newX] = true;
                    Pos newPos = new Pos(newX, newY);
                    newPos.setDistance(thisPos.distance + 1);
                    queue.add(newPos);
                }
            }
        }
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
