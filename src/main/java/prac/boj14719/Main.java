package prac.boj14719;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int h, w;
    static int[][] map;

    // ~ 20:40
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        map = new int[h][w];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            int value = Integer.parseInt(st.nextToken());
            for (int j = h - 1; j > h - 1 - value; j--) {
                map[j][i] = 2;
            }
        }

        for (int i = h - 1; i >= 0; i--) {
            boolean isLeftBlocked = false;
            for (int j = 0; j < w - 1; j++) {
                int value = map[i][j];
                if (!isLeftBlocked) {
                    if (value == 2) {
                        isLeftBlocked = true;
                    }
                    continue;
                }
                if (value == 2) {
                    continue;
                }
                map[i][j] = 1;
            }

            if (map[i][w - 1] != 2) {
                for (int j = w-1; j >= 0; j--) {
                    int value = map[i][j];
                    if (value == 2) {
                        break;
                    }
                    map[i][j] = 0;
                }
            }
        }
        int result = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == 2) {
                    continue;
                }
                result += map[i][j];
            }
        }

        System.out.println(result);
    }
}
