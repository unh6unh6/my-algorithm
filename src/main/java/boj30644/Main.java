package boj30644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    // 07:36 ~

    static int n;
    static TreeMap<Integer, Integer> treeMap = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        getInput();

        int cutting = 0;
        int beforeIndex = -1;
        int mode = 0;
        // mode 0 : 처음, 1 : 커지는중, 2 : 작아지는중

        for (int index : treeMap.values()) {
            if (beforeIndex == -1) {
                beforeIndex = index;
                continue;
            }

            if (mode == 0 && Math.abs(index - beforeIndex) == 1) {
                if (index > beforeIndex) {
                    mode = 1;
                }
                if (index < beforeIndex) {
                    mode = 2;
                }
                beforeIndex = index;
                continue;
            }

            else if (mode == 1 && (index - beforeIndex) == 1) {
                beforeIndex = index;
                continue;
            }

            else if (mode == 2 && (index - beforeIndex) == -1) {
                beforeIndex = index;
                continue;
            }

            mode = 0;
            beforeIndex = index;
            cutting ++;
        }

        System.out.println(cutting);
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(st.nextToken());
            treeMap.put(value, i);
        }
    }
}
