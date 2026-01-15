package boj17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int r;
    static int c;
    static int t;
    static int[][] map;
    static Pos airPos1;
    static Pos airPos2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        map = new int[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    if (airPos1 == null) {
                        airPos1 = new Pos(i, j);
                        continue;
                    }
                    airPos2 = new Pos(i, j);
                }
            }
        }

        for (int i = 0; i < t; i++) {
            moveDust();
            moveAir();
        }

        show();

        int sum = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == -1) {
                    continue;
                }
                sum += map[i][j];
            }
        }
        System.out.println(sum);
    }

    private static void show() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void moveDust() {
        int[][] offset = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] newMap = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] <= 0) {
                    continue;
                }
                int moveCnt = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int newI = i + offset[dir][0];
                    int newJ = j + offset[dir][1];
                    if (newI >= 0 && newI < r && newJ >= 0 && newJ < c && map[newI][newJ] != -1) {
                        moveCnt++;
                        newMap[newI][newJ] += map[i][j] / 5;
                    }
                }
                newMap[i][j] -= (map[i][j] / 5 * moveCnt);
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] += newMap[i][j];
            }
        }
    }

    private static void moveAir() {
        int temp = map[airPos1.y][airPos1.x + 1];
        for (int j = airPos1.x + 2; j < c; j++) {
            temp = map[airPos1.y][j];
            map[airPos1.y][j] = temp;
        }
        for (int i = airPos1.y - 1; i >= 0; i--) {
            map[i][c - 1] = temp;

        }
    }
}

class Pos {
    final int y;
    final int x;

    Pos(
            final int y,
            final int x
    ) {
        this.y = y;
        this.x = x;
    }

    @Override
    public String toString() {
        return "Pos{" +
               "y=" + y +
               ", x=" + x +
               '}';
    }
}
