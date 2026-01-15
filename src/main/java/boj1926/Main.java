package boj1926;

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
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int imageCnt = 0;
        int maxImageSize = 0;

        boolean[][] visit = new boolean[n][m];
        int[][] offset = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visit[i][j] || map[i][j] == 0) {
                    continue;
                }
                imageCnt++;
                int imageSize = 1;
                Queue<Pair> queue = new ArrayDeque<>();
                queue.offer(new Pair(i, j));
                visit[i][j] = true;
                while (!queue.isEmpty()) {
                    Pair thisPair = queue.poll();
                    for (int dir = 0; dir < 4; dir++) {
                        int nextY = thisPair.first + offset[dir][0];
                        int nextX = thisPair.second + offset[dir][1];
                        if (nextY >= 0 && nextY < n && nextX >= 0 && nextX < m &&
                            !visit[nextY][nextX] && map[nextY][nextX] == 1) {
                            queue.offer(new Pair(nextY, nextX));
                            visit[nextY][nextX] = true;
                            imageSize ++;
                        }
                    }
                }
                maxImageSize = Integer.max(maxImageSize, imageSize);
            }
        }

        System.out.println(imageCnt);
        System.out.println(maxImageSize);
    }
}

class Pair {
    final int first, second;

    Pair(
            final int first,
            final int second
    ) {
        this.first = first;
        this.second = second;
    }
}
