package boj4179;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[][] fireMap = new int[r][c];
        int[][] jihunMap = new int[r][c];

        Queue<Pair> queue = new ArrayDeque<>();
        Pair jihun = null;

        for (int i = 0; i < r; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < c; j++) {
                String s = split[j];
                if (s.equals("#")) {
                    fireMap[i][j] = -1;
                    jihunMap[i][j] = -1;
                }
                if (s.equals("F")) {
                    queue.offer(new Pair(i, j));
                    fireMap[i][j] = 1;
                }
                if (s.equals("J")) {
                    jihun = new Pair(i, j);
                    jihunMap[i][j] = 1;
                }
            }
        }
        int[][] offset = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            Pair thisFire = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int newY = thisFire.y + offset[dir][0];
                int newX = thisFire.x + offset[dir][1];
                if (newY >= 0 && newY < r && newX >= 0 && newX < c
                    && fireMap[newY][newX] == 0
                ) {
                    queue.offer(new Pair(newY, newX));
                    fireMap[newY][newX] = fireMap[thisFire.y][thisFire.x] + 1;
                }
            }
        }


        Queue<Pair> queue2 = new ArrayDeque<>();
        queue2.offer(jihun);
        while (!queue2.isEmpty()) {
            Pair thisJihun = queue2.poll();
            for (int dir = 0; dir < 4; dir++) {
                int newY = thisJihun.y + offset[dir][0];
                int newX = thisJihun.x + offset[dir][1];
                if (newY >= 0 && newY < r && newX >= 0 && newX < c
                    && jihunMap[newY][newX] == 0
                    && (fireMap[newY][newX] > jihunMap[thisJihun.y][thisJihun.x] + 1 || fireMap[newY][newX] == 0)
                ) {
                    queue2.offer(new Pair(newY, newX));
                    jihunMap[newY][newX] = jihunMap[thisJihun.y][thisJihun.x] + 1;
                }
            }
        }

        int time = Integer.MAX_VALUE;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (jihunMap[i][j] <= 0) {
                    continue;
                }
                if (i == 0 || i == r-1 || j == 0 || j == c - 1) {
                    time = Integer.min(time, jihunMap[i][j]);
                }
            }
        }

        if (time == Integer.MAX_VALUE) {
            System.out.println("IMPOSSIBLE");
            return;
        }
        System.out.println(time);
    }

}

class Pair {
    final int y, x;

    Pair(
            final int y,
            final int x
    ) {
        this.y = y;
        this.x = x;
    }
}
