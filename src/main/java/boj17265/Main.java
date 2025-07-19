package boj17265;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Main {

    static int n;
    static char[][] map;
    static boolean[][] visit;

    static int max =Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        visit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < n * 2 - 1; j += 2) {
                map[i][j / 2] = line[j];
            }
        }

        final Pos homePos = new Pos(0, 0);
        final Pos schoolPos = new Pos(n - 1, n - 1);
        visit[0][0] = true;
        dfs(homePos, schoolPos, 0, 0, true);
        /*
        operate mode
        -1 : none
        0 : +
        1 : -
        2 : *
         */
        System.out.println(max + " " + min);
    }

    private static void dfs(
            final Pos thisPos,
            final Pos destPos,
            int sum,
            int operateMode,
            boolean doOperate
    ) {
        operateMode = thisPos.getOperateMode(map, operateMode);
        if (thisPos.isValueNumber(map)) {
            sum = operateByMode(
                    sum,
                    thisPos.getMapValue(map),
                    operateMode
            );
        }

        if (thisPos.equals(destPos)) {
            max = Math.max(sum, max);
            min = Math.min(sum, min);
            return;
        }

        for (int dir = 0; dir < 2; dir++) {
            final Pos nextPos = thisPos.getNextPosByDir(dir);
            if (nextPos.canGo(n) && !visit[nextPos.y][nextPos.x]) {
                visit[nextPos.y][nextPos.x] = true;
                dfs(nextPos, destPos, sum, operateMode, !doOperate);
                visit[nextPos.y][nextPos.x] = false;
            }
        }
    }

    private static int operateByMode(
            final int sum,
            final int number,
            final int operateMode
    ) {
        if (operateMode == -1) {
            return sum;
        } else if (operateMode == 0) {
            return sum + number;
        } else if (operateMode == 1) {
            return sum - number;
        } else if (operateMode == 2) {
            return sum * number;
        }
        throw new IllegalStateException();
    }

}

class Pos {
    int x, y;

    public Pos(
            final int x,
            final int y
    ) {
        this.x = x;
        this.y = y;
    }

    private final int[][] offset = {
            {0, 1},
            {1, 0}
    };

    public boolean canGo(int n) {
        return (y >= 0 && y < n && x >= 0 && x < n);
    }

    public Pos getNextPosByDir(int dir) {
        return new Pos(
                this.x + offset[dir][0],
                this.y + offset[dir][1]
        );
    }

    public int getMapValue(char[][] map) {
        return map[y][x] - '0';
    }

    public int getOperateMode(
            char[][] map,
            int operateMode
    ) {
        if (map[y][x] == '+') {
            return 0;
        } else if (map[y][x] == '-') {
            return 1;
        } else if (map[y][x] == '*') {
            return 2;
        }
        return operateMode;
    }

    public boolean isValueNumber(char[][] map) {
        if (map[y][x] == '+' ||
            map[y][x] == '-' ||
            map[y][x] == '*'
        ) {
            return false;
        }
        return true;
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
}
