package boj7573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 15:30 ~ 17:10
    // 해설 확인

    static int n, l, m;
    static Pos[] fishesPos;

    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        fishesPos = new Pos[m];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            final int x = Integer.parseInt(st.nextToken());
            final int y = Integer.parseInt(st.nextToken());
            fishesPos[i] = new Pos(x, y);
        }

        int maximumWidth = (l / 2) - 1;
        for (int width = 1; width <= maximumWidth; width++) {
            int height = (l / 2) - width;

            scanBy(width, height);
        }

        System.out.println(max);
    }

    static void scanBy(final int width, final int height) {
        for (int fishIndex1 = 0; fishIndex1 < fishesPos.length; fishIndex1++) {
            for (int fishIndex2 = 0; fishIndex2 < fishesPos.length; fishIndex2++) {

                Pos fishPos1 = fishesPos[fishIndex1];
                Pos fishPos2 = fishesPos[fishIndex2];

                int i = fishPos1.x;
                int j = fishPos2.y;

                Pos[][] boundaries = calculateBoundaries(i, width, j, height);

                for (int pos = 0; pos < 2; pos++) {
                    if (boundaries[pos][0] == null) {
                        continue;
                    }

                    int catchFishCount = calculateCatchFishCount(boundaries[pos]);
                    max = Math.max(max, catchFishCount);
                }
            }
        }
    }

    static Pos[][] calculateBoundaries(final int i, final int width, final int j, final int height) {
        Pos[][] boundaries = new Pos[2][2];

        boundaries[0][0] = new Pos(i, j);
        boundaries[0][1] = new Pos(i + width, j + height);

        boundaries[1][0] = new Pos(i - width, j);
        boundaries[1][1] = new Pos(i, j + height);

        return boundaries;
    }

    static int calculateCatchFishCount(final Pos[] boundaries) {
        Pos startPos = boundaries[0];
        Pos endPos = boundaries[1];

        int catchFishCount = 0;
        for (final Pos fishPos : fishesPos) {
            if (fishPos.x >= startPos.x &&
                fishPos.x <= endPos.x &&
                fishPos.y >= startPos.y &&
                fishPos.y <= endPos.y
            ) {
                catchFishCount++;
            }
        }

        return catchFishCount;
    }
}

class Pos {
    int x, y;

    public Pos(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
}
