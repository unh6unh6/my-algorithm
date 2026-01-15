package boj1697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int su = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        int[] map = new int[100_001];
        Arrays.fill(map, -1);
        map[su] = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(su);
        while(!queue.isEmpty()) {
            int thisSu = queue.poll();

            if (thisSu == target) {
                System.out.println(map[thisSu]);
                return;
            }

            int nextSu1 = thisSu - 1;
            if (nextSu1 >= 0 && nextSu1 <= 100_000 && map[nextSu1] == -1) {
                map[nextSu1] = map[thisSu] + 1;
                queue.offer(nextSu1);
            }

            int nextSu2 = thisSu + 1;
            if (nextSu2 >= 0 && nextSu2 <= 100_000 && map[nextSu2] == -1) {
                map[nextSu2] = map[thisSu] + 1;
                queue.offer(nextSu2);
            }

            int nextSu3 = thisSu * 2;
            if (nextSu3 >= 0 && nextSu3 <= 100_000 && map[nextSu3] == -1) {
                map[nextSu3] = map[thisSu] + 1;
                queue.offer(nextSu3);
            }
        }
    }
}
