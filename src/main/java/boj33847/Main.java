package boj33847;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n, c;
    static List<Fish> fishes = new ArrayList<>();

    static int maxT = 0;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        c = Integer.parseInt(br.readLine());
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            maxT += x;

            fishes.add(new Fish(x, s, w, c));
        }

        fishes.sort(Collections.reverseOrder());

        int result = 0;
        for (int i = 0; i <= maxT; i++) {
            int t = i;
            int sum = 0;
            for (final Fish fish : fishes) {
                // 크기 순
                if (t == 0) {
                    break;
                }

                if (fish.x <= t) {
                    t -= fish.x;
                    sum += fish.finalProfit;
                }
            }
            result = Math.max(sum, result);
        }

        System.out.println(result);
    }
}

class Fish implements Comparable<Fish> {

    int x, s, w;
    int cost;
    int finalProfit;

    public Fish(final int x, final int s, final int w, final int c) {
        this.x = x;
        this.s = s;
        this.w = w;
        cost = x * c;
        finalProfit = w - cost;
    }

    @Override
    public int compareTo(final Fish o) {
        return Integer.compare(this.s, o.s);
    }
}
