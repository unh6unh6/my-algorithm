package boj1764;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>(n + m);
        Set<String> result = new TreeSet<>();

        for (int i = 0; i < n + m; i++) {
            final String word = br.readLine();
            if (!set.add(word)) {
                result.add(word);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append("\n");
        for (final String s : result) {
            sb.append(s).append("\n");
        }

        System.out.println(sb);
    }
}
