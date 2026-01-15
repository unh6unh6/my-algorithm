package boj15685;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static DragonCurve[] dragonCurves;
    static boolean[][] map = new boolean[100][100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dragonCurves = new DragonCurve[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            dragonCurves[i] = new DragonCurve(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        for (final DragonCurve dragonCurve : dragonCurves) {

            map[dragonCurve.y][dragonCurve.x] = true;
            final List<Integer> directions = new ArrayList<>();
            directions.add(dragonCurve.d);

            buildDragonCurveCourse(
                    dragonCurve.x,
                    dragonCurve.y,
                    directions,
                    dragonCurve.g,
                    0
            );
        }

        int result = 0;

        for (int i = 0; i < 100 - 1; i++) {
            for (int j = 0; j < 100 - 1; j++) {
                if (map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) {
                    result ++;
                }
            }
        }

        System.out.println(result);
    }

    private static void buildDragonCurveCourse(
            int x,
            int y,
            List<Integer> directions,
            int generation,
            int thisGeneration
    ) {
        /**
         * 0세대 -> 1
         * 1세대 -> 2
         * 2세대 -> 4
         * 3세대 -> 8
         */
        if (thisGeneration > generation) {
            return;
        }

        int lastY = 0;
        int lastX = 0;
        int offsetY = 0;
        int offsetX = 0;

        for (int i = 0; i < Math.pow(2, thisGeneration); i++) {
            int direction = directions.get(i);

            int newY = y + offset[direction][0];
            int newX = x + offset[direction][1];

            map[newY][newX] = true;

            lastY = newY;
            lastX = newX;

            offsetY = newY - y;
            offsetX = newX - x;

            directions.add(getClockwiseDirection(offsetX, offsetY));
        }

        // 시계방향으로 모든 direction 변경
        for (final int direction : directions) {

        }

        buildDragonCurveCourse(
                lastX, lastY,
                directions,
                generation,
                thisGeneration + 1
        );
    }

    private static int getClockwiseDirection(
            int offsetX,
            int offsetY
    ) {
        /**
         * 1, 0 -> 0, -1
         * -1, 0 -> 0, 1
         * 0, 1 -> -1, 0
         * 0, -1 -> 1, 0
         */
        // offset = after - before
        if (offsetX > 0 && offsetY == 0) {
            return 3;
        } else if (offsetX < 0 && offsetY == 0) {
            return 1;
        } else if (offsetX == 0 && offsetY > 0) {
            return 2;
        } else {
            return 0;
        }
    }


    private static int[][] offset = {
            {0, 1}, {-1, 0}, {0, -1}, {1, 0}
            // (y, x)
    };
}

class DragonCurve {
    final int x, y, d, g;

    DragonCurve(
            final int x,
            final int y,
            final int d,
            final int g
    ) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.g = g;
    }
}
